package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * class for Directional Light
 * @author Odelia & Ruth
 */
public class DirectionalLight extends Light implements LightSource{
    /**
     * @param _direction the direction of the light
     */
    private Vector _direction;

    /**
     * constructor
     * @param intensity intensity of the light
     * @param direction direction of the light
     */
    protected DirectionalLight(Color intensity, Vector direction) {
        super(intensity);
        this._direction=direction;
    }

    @Override
    public Color getIntensity(Point3D p) {
        return _intensity;
    }

    @Override
    public Vector getL(Point3D p) {
        return _direction;
    }
}
