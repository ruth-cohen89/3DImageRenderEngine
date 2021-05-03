package renderer;

import primitives.*;
import scene.Scene;

/**
 * abstract class for ray tracing (finding colors of scene)
 *
 * @author Ruth & Odelia
 */
public abstract class RayTracerBase {
    protected Scene _scene;

    /**
     * constructor
     * @param scene to paint
     */
    public RayTracerBase(Scene scene) {
        _scene = scene;
    }

    /**
     * finding color of ray instruct point
     * @param ray of tracing
     * @return the color of point
     */
    public abstract Color traceRay(Ray ray);

}
