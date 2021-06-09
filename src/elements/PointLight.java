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
        double dsquared = p.distanceSquared(_position);//distance between the point and the position of the spot light squared
        double d = p.distance(_position);//distance between the point and the position of the spot light
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

    /**
     * @param kC factor of material
     * @return
     */

    public PointLight setKC(double kC) {
        _kC = kC;
        return this;
    }

    /**
     * @param kL factor of material
     * @return
     */
    public PointLight setKl(double kL) {
        _kL = kL;
        return this;
    }

    /**
     * @param kQ factor of material
     * @return
     */
    public PointLight setKq(double kQ) {
        _kQ = kQ;
        return this;
    }
}
