package renderer;

import primitives.*;
import scene.Scene;

import java.util.List;

import geometries.Intersectable.GeoPoint;

import static primitives.Util.alignZero;


/**
 * class for Ray Tracer Base (the son)
 * @author Odelia & Ruth
 */
public class RayTracerBasic extends RayTracerBase{

    //*constructor
    // scene we want paint*//
    public RayTracerBasic(Scene s2) { super(s2); }

    //* finds the intersection points of the ray with the scene's 3D model
    //and returns the color of the closest one
    // Params:
    // ray – from the camera to the scene
    // Returns:
    //the color of the instruct point between scene amd ray*//
    public Color traceRay(Ray r1) {
        List<GeoPoint> intersections = _scene._geometries.findGeoIntersections(r1);
        if(intersections==null){ //if there are not intersection points return to background
            return _scene._background;
        }

        //finds the closest intersection point to head 0f ray
        GeoPoint closestPoint = r1.findClosestGeoPoint(intersections);
        return calcColor(closestPoint);
    }

    /**
     * calculate color of point
     * @param point
     * @return the color of the point (intensity of ambient light)
     */
    private Color calcColor(GeoPoint point) {
        Color color= _scene._ambientLight.getIntensity();
        color=color.add(point.geometry.getEmission());//Meanwhile return the color of the ambient lighting
        return color;
    }
}
