package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/*a tube with a ray and a radius.*/
public class Tube implements Geometry{
    Ray _axisRay;
    double _radius;

    public Tube(Ray ray, double radius){
        _radius= radius;
        _axisRay= ray;

    }

    public Ray getRay() {
        return _axisRay;
    }

    public double getRadius() {
        return _radius;
    }

    @Override
    public String toString() {
        return "Tube{" +
                "_ray=" + _axisRay +
                ", _radius=" + _radius +
                '}';
    }

    /*normal consists of point and direction vector*/
    @Override
    public Vector getNormal(Point3D p) {
        Point3D P0=_axisRay.getP0();
        Vector v=_axisRay.getDirection();
        Vector P0_P=p.substract(P0);
        double t= alignZero(v.dotProduct(P0_P));
        //
        if(isZero(t)){
            return P0_P.normalize();
        }

        Point3D O=P0.add(v.scale(t));
        if(O.equals(p)){
            throw new IllegalArgumentException("point p cannot be on the tube axis");
        }
        Vector n=p.substract(o);
        return n.normalized();
    }
}
