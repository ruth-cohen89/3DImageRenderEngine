package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * class for Point Light
 * @author Odelia & Ruth
 */
public class PointLight extends Light implements LightSource{
    /**
     * @param _position the location of the light
     * @param _kC attenuation with distance
     * @param _kL attenuation with distance
     * @param _kQ attenuation with distance
     */
    private Point3D _position;
    private double _kC=1;
    private double _kL=0;
    private double _kQ=0;

    /**
     * constructor
     * @param intensity
     * @param position
     */
    protected PointLight(Color intensity, Point3D position)
    {
        super(intensity);
        this._position=position;
    }

    /**
     * @param p point on object
     * @return intensity light of the point
     */
    @Override
    public Color getIntensity(Point3D p)
    {
        double denominator, distance;
        distance=_position.distance(p);
        denominator=_kC+_kL*distance+_kQ*distance*distance;
        return _intensity.reduce(denominator);
    }

    /**
     * @param p point on object
     * @return the vector from the light source to the point
     */
    @Override
    public Vector getL(Point3D p) {
        return _position.subtract(p);
    }

    public PointLight setkC(double kC) {
        _kC = kC;
        return this;
    }

    public PointLight setkL(double kL) {
        _kL = kL;
        return this;
    }

    public PointLight setkQ(double kQ) {
        _kQ = kQ;
        return this;
    }
}
