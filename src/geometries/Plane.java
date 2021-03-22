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
        _normal = null;

    }

    /*ctor with a point and a normal*/
    public Plane(Point3D point, Vector vector){
        _q0= point;
        _normal= vector.normalized();
    }

    /**
     * getter for normal vector of plane
     * duplicated use the overriden version of the plane
     * {link plane#getNormal(Point3D) } with null for parameter value.
     * @return
     */
    public Point3D getQ0() {
        return _q0;
    }

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

    @Override
    public Vector getNormal(Point3D point) {
        return null;
        //
    }
}
