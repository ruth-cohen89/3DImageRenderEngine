package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;

public abstract class RayTracerBase {
    protected Scene _s1;

    RayTracerBase(Scene s1) { //constructor
        _s1=s1;
    }

    public abstract Color traceRay(Ray r1); //abstract function return the color of the ray instruct point
}
