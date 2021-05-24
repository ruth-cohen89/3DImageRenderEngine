package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * interface for Light Source
 * @author Odelia & Ruth
 */
public interface LightSource {
    public Color getIntensity(Point3D p);
    public Vector getL(Point3D p);

}
