package geometries;

import primitives.Point3D;
import primitives.Vector;

/*this class represents a plane*/
public class Plane implements Geometry {
    Point3D _point3D;
    Vector _vector;

    /*ctor with 3 points*/
    public Plane(Point3D p1, Point3D p2, Point3D p3) {

        _point3D = p1;
        Vector vec = p1.subtract(p2);
        Vector vec2 = p1.subtract(p3);
        Vector ray = vec.crossProduct(vec2);
        _vector = null;

    }

    /*ctor with a point and a normal*/
    public Plane(Point3D point3D, Vector vector){
        _vector= vector;
        _point3D= point3D;
    }

    public Point3D getPoint3D() {
        return _point3D;
    }

    public Vector getVector() {
        return _vector;
    }

    @Override
    public String toString() {
        return "Plane{" +
                "_point3D=" + _point3D +
                ", _vector=" + _vector +
                '}';
    }

    @Override
    public Vector getNormal(Point3D point) {
        return null;
        //
    }
}
