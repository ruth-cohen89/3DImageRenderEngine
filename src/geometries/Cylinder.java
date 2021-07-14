package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;


/**
 * class for Cylinder
 * @author Odelia & Ruth
 */
public class Cylinder extends Tube {//tube extends Geometry{
    final double _height;

    /**
     * constructor with radius, ray and height
     */
    public Cylinder(Ray axisRay , double radius ,double height) {
        super(axisRay,radius);
        _height= height;
    }

    public double getHeight() {
        return _height;
    }

    @Override
    public String toString() {
        return "Cylinder" +
                "height=" + _height;
    }

    /**
     * find the intersections
     * @param ray ray pointing toward a Geometry
     * @return a list of GeoPoints
     */
    @Override
   public List<GeoPoint> findGeoIntersections(Ray ray) {
        List<GeoPoint> intersections = super.findGeoIntersections(ray);
        if (intersections != null) {
            for (GeoPoint geoPoint : intersections) {
                geoPoint.geometry = this;
            }
        }
        return intersections;
    }

    /**
     * @param point point to calculate the normal
     * @return normal
     */
    @Override
    public Vector getNormal(Point3D point) {
        Point3D o = _ray.getP0();
        Vector v = _ray.getDir();

        // projection of P-O on the ray:
        double t;
        try {
            t = alignZero(point.subtract(o).dotProduct(v));
        } catch (IllegalArgumentException e) { // P = O
            return v;
        }

        // if the point is at a base
        if (t == 0 || isZero(_height - t)) // if it's close to 0, we'll get ZERO vector exception
            return v;

        o = o.add(v.scale(t));
        return point.subtract(o).normalize();
    }

}
