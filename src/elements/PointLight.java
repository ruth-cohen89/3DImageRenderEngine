package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 *  * the power of PointLight decreases from distance
 *  * but constant all the way
 */
public class PointLight extends Light implements LightSource{
    private final Point3D _position; //PL
    //factors for attenuation on light
    private double _kC=1; //Fixed coefficient מקדם קבוע
    private double _kL=0; //Linear coefficient
    private double _kQ=0; //Quadratic coefficient מקדם ריבועי

    /**
     * constructor
     *light with position
     * @param intensity (I0)
     * @param position
     */
    public PointLight(Color intensity, Point3D position) {
        super(intensity);
        _position = position;
    }

    /**
     * @param p Point3D
     * @return the intensity of light source in some point p
     */
    @Override
    public Color getIntensity(Point3D p) {
        double dsquared = p.distanceSquared(_position);//distance between point and position squared
        double d = p.distance(_position);//distance between point and position
        return (_intensity.reduce(_kC + _kL * d + _kQ * dsquared));

    }

    /**
     * the distance between point and _position of PointLight
     * @param point
     * @return
     */
    @Override
    public double getDistance(Point3D point) {
        return point.distance(_position);
    }
    /**
     * @param p Point3D
     * @return Point p - _position normalized
     */
    @Override
    public Vector getL(Point3D p) {
        if (p.equals(_position))//if point p and point position are at the same place - there is no distance
        {
            return null;
        }
        else
            return p.subtract(_position).normalize();//return distance between them normalized
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
