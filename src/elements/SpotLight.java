package elements;

import primitives.*;


/**
 * class for Spot Light
 * @author Odelia & Ruth
 */
public class SpotLight extends PointLight{
    /**
     * @param _direction the direction of the light
     */
    private Vector _direction;

    /**
     * construtor
     * @param intensity
     * @param position
     * @param direction
     */
    public SpotLight(Color intensity, Point3D position, Vector direction) {
        super(intensity, position);
        _direction = direction.normalized();
    }

    /**
     * @param p point on object
     * @return intensity light of the point
     */
    @Override
    public Color getIntensity(Point3D p) {
        return super.getIntensity()
                .scale(Math.max(0, _direction.dotProduct(getL(p))));
    }

    /**
     * @param p point on object
     * @return the vector from the light source to the point
     */
    @Override
    public Vector getL(Point3D p) {
        return _direction;
    }
}
