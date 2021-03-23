package geometries;

import primitives.Point3D;
import primitives.Vector;

/*represents a sphere*/
public class Sphere implements Geometry{
    final Point3D _center;
    final double _radius;

/*constructor with a point and a radius.*/
    public Sphere(Point3D point3D, double radius){
        _radius= radius;
        _center = point3D;
    }

    public Point3D getCenter() {
        return _center;
    }

    public double getRadius() {
        return _radius;
    }

    @Override
    public String toString() {
        return "Sphere{" +
                "_point3D=" + _center +
                ", _radius=" + _radius +
                '}';
    }

    @Override
    public Vector getNormal(Point3D p) {
        Vector o_p=p.substract(_center);
        return o_p.normalize();

    }
}
