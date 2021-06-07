package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;

//Sphere containing point and radius//
public class Sphere extends Geometry {
    private final Point3D _center;
    private final double _radius;

    /**
     * constructor
     * @param point3D
     * @param radius
     */
    public Sphere(Point3D point3D, double radius) {
        _radius = radius;
        _center = point3D;
    }

    /**
     *
     * @return _center
     */
    public Point3D getPoint3D() {
        return _center;
    }

    /**
     *
     * @return _radius
     */
    public double getRadius() {
        return _radius;
    }

    //toString...//
    @Override
    public String toString() {
        return "Sphere{" +
                "_point3D=" + _center +
                ", _radius=" + _radius +
                '}';
    }

    /**
     * The normal to the sphere
     *
     * @param p the Point3D
     * @return vector normal
     */
    @Override
    public Vector getNormal(Point3D p) {
        if (p.equals(_center)) {
            throw new IllegalArgumentException("point cannot be equals to the center of the Sphere ");
        }
        Vector O_P = p.subtract(_center);
        return O_P.normalize();

    }

    /**
     * Finding intersection GeoPoints with the Sphere geometric shape
     *
     * @param ray The ray that cuts the Sphere
     * @return List of GeoPoints of intersection and if not then returns null
     */
    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
        Point3D P0 = ray.getP0();
        Vector v = ray.getDir();

        if (P0.equals(_center)) {
            return List.of(new GeoPoint(this, _center.add(v.scale(_radius))));
        }

        Vector U = _center.subtract(P0); // the vector from the center of the sphere - the ray dont touch the sphere

        double tm = alignZero(v.dotProduct(U)); //the projection of U on V
        double d = alignZero(Math.sqrt(U.lengthSquared() - tm * tm));

        // no intersections : the ray direction is above the sphere
        if (d >= _radius) { //d is bigger than the radius of the sphere -the ray dont touch the sphere

            return null;
        }

        double th = alignZero(Math.sqrt(_radius * _radius - d * d));
        double t1 = alignZero(tm - th);
        double t2 = alignZero(tm + th);

        boolean validT1 = alignZero(t1 - maxDistance) <= 0;
        boolean validT2 = alignZero(t2 - maxDistance) <= 0;

        /**
         * take only t1,t2>0
         */
        if (t1 > 0 && t2 > 0 && validT1 && validT2) {
            Point3D P1 = ray.getPoint(t1);
            Point3D P2 = ray.getPoint(t2);

            return List.of(new GeoPoint(this, P1), new GeoPoint(this, P2));
        }

        //t1>0
        if (t1 > 0 && validT1) {
            Point3D P1 = ray.getPoint(t1);
            return List.of(new GeoPoint(this, P1));
        }

        //t2>0
        if (t2 > 0 && validT2) {
            Point3D P2 = ray.getPoint(t2);
            return List.of(new GeoPoint(this, P2));
        }
        return null;
    }
}

