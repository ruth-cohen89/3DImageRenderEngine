package renderer;

import primitives.*;
import scene.Scene;

import java.util.List;

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
        List<Point3D> intersections = _scene._geometries.findIntersections(r1);
        if(intersections==null){ //if there are not intersection points return to background
            return _scene._background;
        }

        //finds the closest intersection point to head 0f ray
        Point3D closestPoint = r1.findClosestPoint(intersections);
        return calcColor(closestPoint);

    }

    /**
     * calculate color of point
     * @param point
     * @return the color of the point (intensity of ambient light)
     */
    private Color calcColor(Point3D point) {
        return _scene._ambientLight.getIntensity();//Meanwhile return the color of the ambient lighting
    }
}
