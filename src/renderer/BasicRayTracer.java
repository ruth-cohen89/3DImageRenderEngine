package renderer;

import elements.LightSource;
import primitives.*;
import scene.*;

import java.util.List;

import geometries.Intersectable.*;

import static primitives.Util.alignZero;

public class BasicRayTracer extends RayTracerBase {
    private static final double INITIAL_K = 1.0;      //
    private static final int MAX_CALC_COLOR_LEVEL = 10; //
    private static final double MIN_CALC_COLOR_K = 0.001; //


    //*constructor
    // scene we want to paint*//
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

        GeoPoint closestPoint = findClosestIntersection(ray);
        return closestPoint == null ? _scene.background : calcColor(closestPoint, ray);
    }

    /**
     * finds intersections points of ray with geometries and return the closest to head of ray
     *
     * @param ray
     * @return
     */
    private GeoPoint findClosestIntersection(Ray ray) {
        List<GeoPoint> points = _scene.geometries.findGeoIntersections(ray);
        if (points == null)//if there are no intersections
            return null;
        return ray.findClosestGeoPoint(points);
    }


    /**
     * calculate color
     *
     * @param geoPoint
     * @param ray
     * @return
     */
    private Color calcColor(GeoPoint geoPoint, Ray ray) {
        return calcColor(geoPoint, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K)
                .add(_scene.ambientLight.getIntensity());
    }

    /**
     * @param intersection
     * @param ray
     * @param level
     * @param k
     * @return
     */
    private Color calcColor(GeoPoint intersection, Ray ray, int level, double k) {
        Color color = intersection.geometry.getEmission(); //color of geometry itself
        color = color.add(calcLocalEffects(intersection, ray, k));// plus color
        return 1 == level ? color : color.add(calcGlobalEffects(intersection, ray, level, k));
    }


    /**
     * calculate the effects of local lights
     *
     * @param intersection
     * @param ray
     * @return
     */
    private Color calcLocalEffects(GeoPoint intersection, Ray ray, double k) {
        Color color = intersection.geometry.getEmission();
        Vector v = ray.getDir();
        Vector n = intersection.geometry.getNormal(intersection.point);
        double nv = Util.alignZero(n.dotProduct(v));
        if (nv == 0)
            return Color.BLACK;
        Material material = intersection.geometry.getMaterial();
        int nShininess = material._Shininess;
        double kd = material._kD;
        double ks = material._kS;
        for (LightSource lightSource : _scene.lights) { //go through all light sources which are inside the scene
            Vector l = lightSource.getL(intersection.point);
            double nl = Util.alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // sign(nl) = sign(nv)
                double ktr = transparency(lightSource, l, n, intersection);
                if (ktr * k > MIN_CALC_COLOR_K) {
                    Color lightlntensity = lightSource.getIntensity(intersection.point).scale(ktr);
                    color = color.add(calcDiffusive(kd, nl, lightlntensity),
                            calcSpecular(ks, l, n, v, nl, nShininess, lightlntensity));
                }
            }
        }
        return color;

    }


    /**
     * Check if there are geometries that intersect lightRay- means they block lightRay
     * If no geometry meets the lightRay - means unshaded
     * @param light
     * @param l
     * @param n
     * @param geopoint
     * @return
     */
    private boolean unshaded(LightSource light, Vector l, Vector n, GeoPoint geopoint) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Ray lightRay = new Ray(geopoint.point, n, lightDirection); // refactored ray head move
        List<GeoPoint> intersections = _scene.geometries
                .findGeoIntersections(lightRay, light.getDistance(geopoint.point));
        if (intersections == null)
            return true;
        double lightDistance = light.getDistance(geopoint.point);
        for (GeoPoint gp : intersections) {
            if (alignZero(gp.point.distance(geopoint.point)-lightDistance) <= 0 &&
                    gp.geometry.getMaterial()._kT == 0)
                return false;
        }
        return true;
    }

    private double transparency(LightSource light, Vector l, Vector n, GeoPoint geopoint) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Ray lightRay = new Ray(geopoint.point, lightDirection, n);
        List<GeoPoint> intersections = _scene.geometries
                .findGeoIntersections(lightRay, light.getDistance(geopoint.point));
        if (intersections == null)
            return 1.0;
        double ktr = 1.0;
        double lightDistance = light.getDistance(geopoint.point);
        for (GeoPoint gp : intersections) {
            if (alignZero(gp.point.distance(geopoint.point) - lightDistance) <= 0)//&& gp.geometry.getMaterial()._kT == 0)
                ktr *= gp.geometry.getMaterial()._kT;
            if (ktr < MIN_CALC_COLOR_K)
                return 0.0;
        }
        return ktr;
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
    private Color calcSpecular(double ks, Vector l, Vector n, Vector v, double nl, int nShininess, Color ip) {
/*
         Vector R = l.add(n.scale(2 * nl)); // nl cannot be zero double
        double max=Math.max(0, v.scale(-1).dotProduct(R));
        var p=Math.pow(max, nShininess);
        return ip.scale(ks*p);



        Vector r = l.substract(n.scale(nl * 2)).normalized();
        double factor = Math.max(0, (v.scale(-1)).dotProduct(r));
        return ip.scale(ks * Math.pow(factor, nShininess));*/

        Vector r = l.substract(n.scale(2 * nl));
        Vector minusV = v.scale(-1);
        double shininess = Math.pow(r.dotProduct(minusV), nShininess);
        return ip.scale(ks * shininess);

    }


    /**
     * Calculate Diffusive component of light reflection.
     *
     * @param kd diffusive component
     * @param nl
     * @param ip light intensity at the point
     * @return diffusive component of light reflection
     */

    private primitives.Color calcDiffusive(double kd, double nl, Color ip) {
        Color scaled = ip.scale(Math.abs(nl) * kd);// scales nl with the diffuse component
        return scaled;
    }

    /**
     * calculate all the global effects
     *
     * @param gp
     * @param ray
     * @param level
     * @param k
     * @return
     */

    private Color calcGlobalEffects(GeoPoint gp, Ray ray, int level, double k) {
        Color color = Color.BLACK;
        Vector n = gp.geometry.getNormal(gp.point);
        Material material = gp.geometry.getMaterial();
        double kkr = k * material._kR;
        if (kkr > MIN_CALC_COLOR_K)
            color = calcGlobalEffect(constructReflectedRay(gp.point, ray.getDir(), n), level, material._kR, kkr);
        double kkt = k * material._kT;
        if (kkt > MIN_CALC_COLOR_K)
            color = color.add(
                    calcGlobalEffect(constructRefractedRay(gp.point, ray.getDir(), n), level, material._kT, kkt));
        return color;
    }

    /**
     * @param ray
     * @param level
     * @param kx
     * @param kkx
     * @return
     */
    private Color calcGlobalEffect(Ray ray, int level, double kx, double kkx) {
        GeoPoint gp = findClosestIntersection(ray);
        return ((gp == null ? _scene.background : calcColor(gp, ray, level - 1, kkx))
                .scale(kx));
    }

    /**
     * @param point
     * @param v
     * @param n
     * @return
     */
    private Ray constructRefractedRay(Point3D point, Vector v, Vector n) {
        return new Ray(point, v, n);

    }

    /**
     * @param point
     * @param v
     * @param n
     * @return
     */

  private Ray constructReflectedRay(Point3D point, Vector v,Vector n){
        double vn = v.dotProduct(n);
        Vector projection = n.scale(vn);
        Vector r = v.substract(projection.scale(2));
        return new Ray(point, r, n);
    }

//
//    private Ray constructReflectedRay(Vector n, Point3D point, Ray ray) {
//        Vector v = ray.getDir();
//        double vn = v.dotProduct(n);
//        Vector vnn = n.scale(-2 * vn);
//        Vector r = v.add(vnn);
//        // use the constructor with 3 arguments to move the head if needed
//        return new Ray(point, n, r);
//    }
}






