package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;


/**
 * class for Plane
 * @author Odelia & Ruth
 */
public class Plane extends Geometry {

    final Point3D _q0;
    final Vector _normal;

    /**
     * constructor with 3 points
     * @param p1
     * @param p2
     * @param p3
     */
    public Plane(Point3D p1, Point3D p2, Point3D p3) {
        _q0 = p1;
        Vector v1 = p2.subtract(p1);
        Vector v2 = p3.subtract(p1);
        Vector n = v1.crossProduct(v2);
        _normal = n.normalize();

    }

    /**
     * constructor for Plane
     *
     * @param point
     * @param vector for the normal (will be normalized automatically)
     */
    public Plane(Point3D point, Vector vector) {
        _q0 = point;

        _normal = vector.normalized();
    }

    /**
     * getter of Q0 field
     *
     * @return reference to Q0 point of the plane
     */
    public Point3D getQ0() {
        return _q0;
    }

    /**
     * getter of normal field
     * Deprecated use {@link Plane#getNormal(Point3D)} with null values as parameters
     *
     * @return reference to normal vector of the plane
     */
    @Deprecated
    public Vector getNormal() {
        return _normal;
    }

    @Override
    public String toString() {
        return "Plane{" +
                "_point3D=" + _q0 +
                ", _vector=" + _normal +
                '}';
    }

    /**
     * getNormal implementation of Geometry interface
     *
     * @param point
     * @return
     */
    @Override
    public Vector getNormal(Point3D point) {
        return _normal;
        //
    }


    /**
     *
     * @param ray to the geometry
     * @return list of geometries points that intersects with the geometry
     */
    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        Point3D P0 = ray.getP0();
        Vector v = ray.getDirection();

        Vector n = _normal;

        if (_q0.equals(P0)) {
            return null;
        }
        Vector P0_Q0 = _q0.subtract(P0);

        double mechane = alignZero(n.dotProduct(P0_Q0));

        //
        if (isZero(mechane)) {
            return null;
        }
        //mone
        double nv = alignZero(n.dotProduct(v));

        //ray is lying in the plane axis
        if (isZero(nv)) {
            return null;
        }
        double t = alignZero(mechane / nv);

        Point3D P = P0.add(v.scale(t));

        return List.of(new GeoPoint(this, P));

    }
}
