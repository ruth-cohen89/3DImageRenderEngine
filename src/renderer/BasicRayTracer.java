package renderer;
import elements.LightSource;
import primitives.*;
import primitives.*;
import scene.*;
import java.util.List;
import geometries.Intersectable.*;

import static primitives.Util.alignZero;

public class BasicRayTracer extends RayTracerBase{
    private static final double INITIAL_K = 1.0;      //@TODO
    private static final int MAX_CALC_COLOR_LEVEL = 10; //@TODO
    private static final double MIN_CALC_COLOR_K = 0.001; //@TODO
    private static final double DELTA = 0.1; //@TODO


    //*constructor
    // scene we want paint*//
    public BasicRayTracer(Scene scene) {
        super(scene);
    }

    //* finds the intersection points of the ray with the scene's 3D model
    //and returns the color of the closest one
    // Params:
    // ray – from the camera to the scene
    // Returns:
    //the color of the instruct point between scene amd ray*//
    @Override
    public Color traceRay(Ray ray) {

        List<GeoPoint> intersections = _scene._geometries.findGeoIntersections(ray);//intersections points of the ray with scene's 3D model
        if (intersections == null) {// if ray did not intersect any geometrical object

            return _scene._background; //return background
        }
        GeoPoint closestPoint = ray.findClosestGeoPoint(intersections);//find the closest intersection point to head of ray
        return calcColor(closestPoint, ray);//calculate the color the point
    }

    /**
     * Gets a point and ray
     * @param geoPoint The geoPoint closest to the ray and its color should be checked
     * @return The original color of the dot in the scene
     */
    private Color calcColor(GeoPoint geoPoint, Ray ray) {
        Color emissionColor= geoPoint.geometry.getEmission();//the color of the object itself (by its point)
        return _scene._ambientLight.getIntensity()
                .add(emissionColor) //add emission to color of the ambient light
                .add(calcLocalEffects(geoPoint,ray));//add effects from all light sources
    }

    /**
     * Calculates all the local effects in scene caused by light
     * @param intersection
     * @param ray The ray that intersects geometry
     * @return returns the effect of color
     */

    private Color calcLocalEffects(GeoPoint intersection, Ray ray) {
        Vector v = ray.getDirection();
        Vector n = intersection.geometry.getNormal(intersection.point);
        double nv = Util.alignZero(n.dotProduct(v));
        if (Util.isZero(nv))
            return Color.BLACK;
        Material material = intersection.geometry.getMaterial();
        int nShininess = material._nShininess;
        double kd = material._kD;
        double ks = material._kS;
        Color color = Color.BLACK;
        for (LightSource lightSource : _scene._lights) { //go through all light sources which are inside the scene
            Vector l = lightSource.getL(intersection.point);
            double nl = Util.alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // sign(nl) = sign(nv)
                if (unshaded(lightSource, l, n, intersection)) { //if appropriate lighting is not blocked by geometry
                    Color lightlntensity = lightSource.getIntensity(intersection.point);
                    color = color.add(calcDiffusive(kd, nl, lightlntensity),
                            calcSpecular(ks, l, n, nl, v, nShininess, lightlntensity));
                }
            }
        }
        return color;

    }

    /**
     * Check if there are geometries that intersect lightRay- means they block lightRay
     * @param light
     * @param v- vector
     * @param n
     * @param intersection
     * @return If no geometry cut the lightRay return true- good
     *         If there are geometry cut the lightRay return false- not good
     */
    private boolean unshaded(LightSource light, Vector v, Vector n, GeoPoint intersection) {
        Vector lightDirection = v.scale(-1); // vector from point to light source
        Ray lightRay = new Ray(intersection.point, n, lightDirection);

        List<GeoPoint> intersections = _scene._geometries//Check if there that geometries intersect lightRay
                .findGeoIntersections(lightRay, light.getDistance(intersection.point));
        //If no geometry intersects lightRay return true- good
        //otherwise return false- bad
        return intersections == null ;
    }

    /**
     * Calculate Specular component of light reflection.
     *
     * @param ks         specular component
     * @param l          direction from light
     * @param n          normal to surface
     * @param nl         dot product n*l
     * @param v          direction from point of view
     * @param nShininess shininess level
     * @param ip         light intensity
     * @return specular light color
     */
    private Color calcSpecular(double ks, Vector l, Vector n, double nl, Vector v, int nShininess, Color ip) {
        /*
         * Vector R = l.add(n.scale(-2 * nl)); // nl cannot be zero double dotResult =
         * R.dotProduct(v); double minusVR = -alignZero(dotResult); if (minusVR <= 0) {
         * return primitives.Color.BLACK; // view from direction opposite to r vector }
         * Color scaled = ip.scale(ks * Math.pow(minusVR, nShininess));// scales the
         * light intensity with the // specular component times minusVR to // the power
         * of nShininess return scaled;
         */

        Vector r = l.subtract(n.scale(nl * 2)).normalized();
        double factor = Math.max(0, (v.scale(-1)).dotProduct(r));
        return ip.scale(ks * Math.pow(factor, nShininess));

    }


    /**
     * Calculate Diffusive component of light reflection.
     *
     * @param kd diffusive component
     * @param nl dot-product n*l
     * @param ip light intensity at the point
     * @return diffusive component of light reflection
     */

    private primitives.Color calcDiffusive(double kd, double nl, Color ip) {
        if (nl < 0) // if nl is negative we make it positive
            nl = Math.abs(nl);
        Color scaled = ip.scale(nl * kd);// scales nl with the diffuse component
        return scaled;
    }
}
    
    
    
        


        