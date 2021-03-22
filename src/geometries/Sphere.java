package geometries;

import primitives.Point3D;
import primitives.Vector;

/*represents a sphere*/
public class Sphere implements Geometry{
    Point3D _point3D;
    double _radius;

/*constructor with a point and a radius.*/
    public Sphere(Point3D point3D, double radius){
        _radius= radius;
        _point3D= point3D;
    }

    public Point3D getPoint3D() {
        return _point3D;
    }

    public double getRadius() {
        return _radius;
    }

    @Override
    public String toString() {
        return "Sphere{" +
                "_point3D=" + _point3D +
                ", _radius=" + _radius +
                '}';
    }

    @Override
    public Vector getNormal(Point3D point) {
        return null;
    }
}
