package renderer;

import primitives.*;
import scene.Scene;

import java.util.List;

public class RayTracerBasic extends RayTracerBase{

    //*constructor
    // scene we want paint*//
    public RayTracerBasic(Scene s2) { super(s2); }

    //* painting point in image
    // Params:
    // ray – from the camera to the scene
    // Returns:
    //the color of the instruct point between scene amd ray*//
    public Color traceRay(Ray r1) {
        //find the instruction points of the ray with the scene's 3D model
        List<Point3D> intersections = _scene._geometries.findIntersections(r1);
        if(intersections==null){//if there are not instruction points return the background
            return _scene._background;
        }
        //calculate the color in the instruction point which closest to head 0f ray
        Point3D closestPoint = r1.findClosestPoint(intersections);
        return calcColor(closestPoint);

    }

    /**
     * calculate the color in a point
     * @param point
     * @return the color of the point
     */
    private Color calcColor(Point3D point) {
        return _scene._ambientLight.getIntensity();//Meanwhile return the color of the ambient lighting
    }
}
