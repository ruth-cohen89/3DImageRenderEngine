package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

public class DirectionalLight extends Light implements LightSource {

    /**
     * Direction of the light
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
        return super.getIntensity();
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
        return Double.POSITIVE_INFINITY; //Infinite value
    }
}
