package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/*
the power of direction light does not decrease from distance
*and the direction remains constant (not effected by other objects)
 */
public class DirectionalLight extends Light implements LightSource {

    /**
     * Direction of the light
     * like direction of the sun vector rays towards the object
     */
    private final Vector _direction; //the direction of DirectionalLight

    /**
     * constructor for DirectionalLight
     * @param intensity  of light source
     * @param direction the direction of DirectionalLight
     */
    public DirectionalLight(Color intensity, Vector direction) {
        super(intensity);
        _direction = direction.normalized();;
    }


    /**
     * @param po Point3D
     * @return the intensity of light source in point p
     */
    @Override
    public Color getIntensity(Point3D po) {
        return _intensity;
    }


    /**
     * Returns the value of the light direction
     * @param p Point3D
     * @return the Vector direction of light source in point p
     */
    @Override
    public Vector getL(Point3D p) {
        return _direction;
    }

    /**
     * Distance between point and DirectionalLight
     * @param point
     * @return //Infinite value
     */
    @Override
    public double getDistance(Point3D point) {
        return Double.POSITIVE_INFINITY; //Infinite value-very far
    }
}
