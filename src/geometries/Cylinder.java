package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/*.*/
public class Cylinder extends Tube {//implements Geometry{
    final double _height;

/*constructor with radius, ray and height*/
    public Cylinder(Ray axisRay , double radius ,double height) {
        super(axisRay,radius);
        _height= height;
    }

    public double getHeight() {
        return _height;
    }

    @Override
    public String toString() {
        return "Cylinder{" +
                "_height=" + _height +
                '}';
    }

    @Override
    public Vector getNormal(Point3D point) {
        return null;
    }

    @Override
    public List<Point3D> findIntersections(Ray ray) {
        return super.findIntersections(ray);
    }
}
