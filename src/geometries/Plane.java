package geometries;

import primitives.Point3D;
import primitives.Vector;

/*this class represents a plane.*/
public class Plane implements Geometry {
    final Point3D _q0;
    final Vector _normal;

    /*ctor with 3 points*/
    public Plane(Point3D p1, Point3D p2, Point3D p3) {
        _q0 = p1;
        Vector v1=p2.substract(p1);
        Vector v2=p3.substract(p1);
        Vector n=v1.crossProduct(v2);
        _normal = n.normalize();

    }

    /**
     *constructor for Plane
     * @param point
     * @param normal vector for the normal (will be normalized automatically)
     */
    public Plane(Point3D point, Vector vector){
        _q0= point;

        _normal= vector.normalized();
    }

    /**
     * getter of Q0 field
     * @return reference to Q0 point of the plane
     */
    public Point3D getQ0() {
        return _q0;
    }

    /**
     * getter of normal field
     * Deprecated use {@link Plane#getNormal(Point3D)} with null values as parameters
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
     * @param point
     * @return
     */
    @Override
    public Vector getNormal(Point3D point) {
        return _normal;
        //
    }
}
