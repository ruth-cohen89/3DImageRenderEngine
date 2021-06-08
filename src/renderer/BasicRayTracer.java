package renderer;
import elements.LightSource;
import primitives.*;
import scene.*;
import java.util.List;
import geometries.Intersectable.*;

public class BasicRayTracer extends RayTracerBase{
    private static final double INITIAL_K = 1.0;      //
    private static final int MAX_CALC_COLOR_LEVEL = 10; //
    private static final double MIN_CALC_COLOR_K = 0.001; //
    private static final double DELTA = 0.1; //



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
     * @param ray
     * @return
     */
    private GeoPoint findClosestIntersection(Ray ray) {
        var points =_scene.geometries.findGeoIntersections(ray);
        if (points==null)//if there are no intersections
            return null;
        return ray.findClosestGeoPoint(points);
    }


    /**
     *calculate color
     * @param geoPoint
     * @param ray
     * @return
     */
    private Color calcColor(GeoPoint geoPoint, Ray ray) {
        return calcColor(geoPoint, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K)
                .add(_scene.ambientLight.getIntensity());
    }

    /**
     *
     * @param intersection
     * @param ray
     * @param level
     * @param k
     * @return
     */
    private Color calcColor(GeoPoint intersection, Ray ray, int level, double k) {
        Color color = intersection.geometry.getEmission(); //color of geometry itself
        color = color.add(calcLocalEffects(intersection, ray));// plus color
        return 1 == level ? color : color.add(calcGlobalEffects(intersection, ray.getDir(), level, k));
    }


    /**
     * calculate the effects of local lights
     * @param intersection
     * @param ray
     * @return
     */
    private Color calcLocalEffects(GeoPoint intersection, Ray ray) {
        Vector v = ray.getDir();
        Vector n = intersection.geometry.getNormal(intersection.point);
        double nv = Util.alignZero(n.dotProduct(v));
        if (Util.isZero(nv))
            return Color.BLACK;
        Material material = intersection.geometry.getMaterial();
        int nShininess = material._Shininess;
        double kd = material._kD;
        double ks = material._kS;
        Color color = Color.BLACK;
        for (LightSource lightSource : _scene.lights) { //go through all light sources which are inside the scene
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
     * If no geometry meets the lightRay - means unshaded
     * @param light
     * @param l
     * @param n
     * @param geopoint
     * @return
     */
    private boolean unshaded(LightSource light, Vector l, Vector n, GeoPoint geopoint) {
        Vector lightDirection = l.scale(-1); // from point to light source
        //ray from point towrds light direction
        Ray lightRay = new Ray(geopoint.point,n,lightDirection);

        //distance from point to the source of light= light
        double distance=light.getDistance(geopoint.point);

        List<GeoPoint> intersections = _scene.geometries
                .findGeoIntersections(lightRay,distance);

        //If no geometry meets the lightRay - means unshaded - return true- good
        //otherwise return false - bad
        return intersections ==null ; //return true if null

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

        Vector r = l.substract(n.scale(nl * 2)).normalized();
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

    /**
     *calculate all the global effects
     * @param gp
     * @param v
     * @param level
     * @param k
     * @return
     */
    private Color calcGlobalEffects(GeoPoint gp, Vector v, int level, double k) {
        Color color = Color.BLACK;
        Vector n = gp.geometry.getNormal(gp.point);
        Material material = gp.geometry.getMaterial();
        double kkr = k * material._kR;
        if (kkr > MIN_CALC_COLOR_K)
            color = calcGlobalEffect(constructReflectedRay(gp, v, n), level, material._kR, kkr);
        double kkt = k * material._kT;
        if (kkt > MIN_CALC_COLOR_K)
            color = color.add(
                    calcGlobalEffect(constructRefractedRay(gp, v, n), level, material._kT, kkt));
        return color;
    }

    /**
     *
     * @param ray
     * @param level
     * @param kx
     * @param kkx
     * @return
     */
    private Color calcGlobalEffect(Ray ray, int level, double kx, double kkx) {
        GeoPoint gp = findClosestIntersection (ray);
        return (gp == null ? _scene.background : calcColor(gp, ray, level-1, kkx)
        ).scale(kx);
    }

    /**
     *
     * @param geopoint
     * @param v
     * @param n
     * @return
     */
    private Ray constructRefractedRay(GeoPoint geopoint, Vector v,Vector n){
        return new Ray(geopoint.point,v,n);

    }

    /**
     *
     * @param geopoint
     * @param v
     * @param n
     * @return
     */
    private Ray constructReflectedRay(GeoPoint geopoint, Vector v,Vector n){
        Vector r=v.substract(n.scale(2*v.dotProduct(n))).normalize();
        return new Ray(geopoint.point,r,n);
    }
}
    
    
    
        


        