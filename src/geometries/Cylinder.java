package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/*represents a Cylinder.*/
public class Cylinder extends Tube implements Geometry{

    double _height;

/*constructor with radius, ray and height*/
    public Cylinder(double radius, Ray ray ,double height) {
        super(ray,radius);
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
}
