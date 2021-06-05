package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import static primitives.Util.*;
import java.util.List;

import static primitives.Util.isZero;

//Tube containing radius and ray//
public class Tube extends  Geometry{
    final Ray _ray;
    double _radius;

    public Tube(Ray ray, double radius){
        _radius= radius;
        _ray= ray;

    }

    //Get...//
    public Ray getRay() {
        return _ray;
    }

    public double getRadius() {
        return _radius;
    }

    //toString...//
    @Override
    public String toString() {
        return "Tube{" +
                "_ray=" + _ray +
                ", _radius=" + _radius +
                '}';
    }

    @Override
    public Vector getNormal(Point3D point) {
        Point3D P0= _ray.getP0();
        Vector v= _ray.getDirection();
        Vector P0_P= point.subtract(P0);

        double t= v.dotProduct(P0_P);
        //We check if t is almost equal to 0 and then return the normalized P0_P
        if (isZero(t)) {
            return  P0_P.normalize();
        }
        Point3D O= P0.add(v.scale(t));
        if(O.equals(point)){
            throw new IllegalArgumentException("point p cannot be on the tube's axis");
        }
        return point.subtract(O).normalize();

    }
    /**
     * Finding intersection GeoPoints with the Sphere geometric Tube
     * @param ray The ray that cuts the Tube
     * @return List of GeoPoints of intersection and if not then returns null
     */
    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
        Vector v_ray = _ray.getDirection();
        Vector v = ray.getDirection();
        Point3D p0 = ray.getP0();

        // At^2+Bt+C=0
        double a = 0;
        double b = 0;
        double c = 0;

        double vVa = alignZero(v.dotProduct(v_ray));
        Vector vVaVa;
        Vector vMinusVVaVa;
        if (vVa == 0) // the ray is orthogonal to the axis
            vMinusVVaVa = v;
        else {
            vVaVa = v_ray.scale(vVa);
            try {
                vMinusVVaVa = v.subtract(vVaVa);
            } catch (IllegalArgumentException e1) { // the rays is parallel to axis
                return null;
            }
        }
        // A = (v-(v*va)*va)^2
        a = vMinusVVaVa.lengthSquared();

        Vector deltaP = null;
        try {
            deltaP = p0.subtract(_ray.getP0());
        } catch (IllegalArgumentException e1) { // the ray begins at axis P0
            if (vVa == 0 ) // the ray is orthogonal to Axis
                return List.of(new GeoPoint(this, ray.getPoint(_radius)));

            double t = alignZero(Math.sqrt(_radius * _radius / vMinusVVaVa.lengthSquared()));
            return t == 0 ? null : List.of(new GeoPoint(this, ray.getPoint(t)));
        }

        double dPVAxis = alignZero(deltaP.dotProduct(v_ray));
        Vector dPVaVa;
        Vector dPMinusdPVaVa;
        if (dPVAxis == 0)
            dPMinusdPVaVa = deltaP;
        else {
            dPVaVa = v_ray.scale(dPVAxis);
            try {
                dPMinusdPVaVa = deltaP.subtract(dPVaVa);
            } catch (IllegalArgumentException e1) {
                double t = alignZero(Math.sqrt(_radius * _radius / a));
                return t == 0 ? null : List.of(new GeoPoint(this, ray.getPoint(t)));
            }
        }

        // B = 2(v - (v*va)*va) * (dp - (dp*va)*va))
        b = 2 * alignZero(vMinusVVaVa.dotProduct(dPMinusdPVaVa));
        c = dPMinusdPVaVa.lengthSquared() - _radius * _radius;

        // A*t^2 + B*t + C = 0 - lets resolve it
        double discr = alignZero(b * b - 4 * a * c);
        if (discr <= 0) return null; // the ray is outside or tangent to the tube

        double doubleA = 2 * a;
        double tm = alignZero(-b / doubleA);
        double th = Math.sqrt(discr) / doubleA;
        if (isZero(th)) return null; // the ray is tangent to the tube

        double t1 = alignZero(tm + th);
        if (t1 <= 0) // t1 is behind the head
            return null; // since th must be positive (sqrt), t2 must be non-positive as t1

        double t2 = alignZero(tm - th);

        // if both t1 and t2 are positive
        if (t2 > 0)
            return List.of(new GeoPoint(this, ray.getPoint(t1)), new GeoPoint(this, ray.getPoint(t2)));
        else // t2 is behind the head
            return List.of(new GeoPoint(this, ray.getPoint(t1)));
    }


}
