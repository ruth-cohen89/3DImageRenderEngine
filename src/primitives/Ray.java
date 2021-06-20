package primitives;

import geometries.*;
import geometries.Intersectable.*;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/* Ray:This class represents the set of points on a straight line
 that are on one relative side
     To a given point on the line called the beginning of the foundation.
     Defined by point and direction (unit vector) ...*/
public class Ray {
    private static final double DELTA = 0.1;
    Point3D _p0;
    final Vector _dir;

    public Ray(Vector dir, Point3D p0) {
        _p0 = p0;
        _dir = dir.normalized();
    }

    //    /**
//     * Constructor with parameters.
//     * Move the ray's origin by DELTA or -DELTA in function of the sign of the dot product
//     * between the ray's direction and the normal.
//     * @param point
//     * @param n normal vector
//     * @param direction
//     */
//    public Ray(Point3D point, Vector n, Vector direction) {
//        //To deflect the Point a little above or below because of mathematical calculations
//        // so that we do not accidentally discover that it cuts itself
//        Vector delta = n.scale(n.dotProduct(direction) > 0 ? DELTA : - DELTA); //We want value of the calculation DELTA= n*n*direction this is DELTA or - DELTA
//        _p0= point.add(delta); //_p0= point + delta
//        _dir= direction;
//    }
    public Ray(Point3D p0, Vector dir, Vector normal) {
        this(dir, p0); // activate the current instance constructor

        // make sure the normal and the direction are not orthogonal
        double nv = alignZero(normal.dotProduct(_dir));

        // if not orthogonal
        if (!isZero(nv)) {
            // create new vector to help move the head of
            // the vector to the correct position
            Vector fixVector = normal.scale(nv > 0 ? DELTA : -DELTA);
            // move the head of the vector in the right direction
            _p0 = p0.add(fixVector);
        }
    }

    /**
     * getter for origin of the ray
     *
     * @return _p0
     */
    public Point3D getP0() {
        return _p0;
    }

    /**
     * getter for direction vector of the ray
     *
     * @return _dir
     */
    public Vector getDir() {
        return _dir;
    }

    public Point3D getPoint(double t) {
        Point3D p = _p0.add(_dir.scale(t));
        return p;
    }

    /**
     * Comparison between two objects of ray
     *
     * @param o Of type Object to be converted to type Ray
     * @return true if they equals,  return false if unequal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ray)) return false;
        Ray ray = (Ray) o;
        return _p0.equals(ray._p0) && _dir.equals(ray._dir);
    }

    /**
     * The function checks from a list of points which point is closest to the beginning of the ray
     *
     * @param listPoints List of points
     * @return The closest point to the beginning of the ray
     */

    public Point3D findClosestPoint(List<Point3D> listPoints) {
        Point3D max = null;
        if (listPoints != null) {
            double d = Double.POSITIVE_INFINITY;
            for (Point3D points : listPoints) {
                if (d > _p0.distance(points)) {
                    d = _p0.distance(points);
                    max = points;
                }
            }
        }
        return max;
    }

    /**
     * The function checks from a list of GeoPoint which point is closest to the beginning of the ray
     *
     * @param listGeoPoints List of listGeoPoints
     * @return The closest point to the beginning of the ray
     */
    public GeoPoint findClosestGeoPoint(List<GeoPoint> listGeoPoints) {
        if (listGeoPoints == null) {
            return null;
        }
        double d = Double.POSITIVE_INFINITY;
        GeoPoint max = null;
        for (GeoPoint geo : listGeoPoints) {
            if (d > _p0.distance(geo.point)) {
                d = _p0.distance(geo.point);
                max = geo;
            }
        }
        return max;
    }

}

