package elements;

import primitives.*;
import geometries.*;
/**
 * Interface for common actions of light sources
 */
public interface LightSource {
    /**
     * @param p Point3D
     * @return the intensity of light source in point p
     */
    public Color getIntensity(Point3D p);

    /**
     * the distance between point to LightSource
     * @param point
     * @return
     */
    double getDistance(Point3D point);

    /**
     * @param p Point3D
     * @return the Vector of light source in point p
     */
    public Vector getL(Point3D p);

}
