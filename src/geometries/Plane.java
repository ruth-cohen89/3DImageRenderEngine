package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * class for Plane
 * @author Odelia & Ruth
 */
public class Plane extends Geometry {
    final Point3D _point3D; //point on the plane
    final Vector _normal; // the normal vector to the plane

    /**
     * Constructor with 3 points should calculate the normal
     * @param p1 first Point3D
     * @param p2  second Point3D
     * @param p3 third Point3D
     */
    public Plane(Point3D p1, Point3D p2, Point3D p3) {
        _point3D = p1;
        Vector vec = p2.subtract(p1);
        Vector vec2 = p3.subtract(p1);
        Vector n = vec.crossProduct(vec2);
        _normal = n.normalized(); // Normalization of vector n
    }

    /**
     * Constructor with point and normal vector
     * @param point3D
     * @param vector
     */
    public Plane(Point3D point3D, Vector vector){
        _normal= vector.normalized();
        _point3D= point3D;
    }

    /**
     * getter for Point3D of the plane-  field
     * @return _point3D
     */
    public Point3D getPoint3D() {
        return _point3D;
    }

    /**
     * @deprecated use the Override version from geometries.
     * @link plane#getNormal(Point3D) with null for parametr value.
     * @return _normal
     */
    @Deprecated
    public Vector getNormal() {
        return _normal;
    }

    /**
     * to string
     * @return
     */
    @Override
    public String toString() {
        return "Plane{" +
                "_point3D=" + _point3D +
                ", _vector=" + _normal +
                '}';
    }

    /**
     * getNormal implementation of geometry interface
     * @param point one point-type parameter
     * @return vector normal to the plane
     */
    @Override
    public Vector getNormal(Point3D point) {
        return _normal;
    }

    /**
     * Finding intersection GeoPoints with the plane geometric shape
     * @param ray The ray that cuts the plane
     * @param maxDistance The maximum possible distance
     * @return List of GeoPoints of intersection and if not then returns null
     */
    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {

        Point3D P0 = ray.getP0();
        Vector v = ray.getDir();

        Vector n = _normal;
        if (_point3D.equals(P0)) {
            return null; //No intersection points
        }
        Vector P0_Q0 = _point3D.subtract(P0); //Q0- P0
        double mechane = alignZero(n.dotProduct(P0_Q0));
        if (isZero(mechane)) {
            return null; //No intersection points
        }
        //mone
        double nv = alignZero(n.dotProduct(v));
        //ray is lying in the plane axis
        if (isZero(nv)) {
            return null;
        }
        double t = alignZero(mechane / nv);
        if(t>0 &&alignZero(t-maxDistance) <=0){
            Point3D P = ray.getPoint(t);
            return List.of(new GeoPoint(this , P));
        }
        return null;

    }
}
