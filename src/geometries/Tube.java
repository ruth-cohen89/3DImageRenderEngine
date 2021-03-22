package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/*a tube with a ray and a radius*/
public class Tube implements Geometry{
    Ray _ray;
    double _radius;

    public Tube(Ray ray, double radius){
        _radius= radius;
        _ray= ray;

    }

    public Ray getRay() {
        return _ray;
    }

    public double getRadius() {
        return _radius;
    }

    @Override
    public String toString() {
        return "Tube{" +
                "_ray=" + _ray +
                ", _radius=" + _radius +
                '}';
    }

    @Override
    public Vector getNormal(Point3D point) {
        return null;
    }
}
