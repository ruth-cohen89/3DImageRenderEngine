package primitives;

import geometries.Geometries;
import geometries.Intersectable;

import java.util.List;
import java.util.Objects;
import geometries.Intersectable.GeoPoint;

//**ray- straight that extends to infinity in only one direction**//
public class  Ray {
    final Point3D _pOrigin; //point..
    final Vector _direction; //direction vector.

    public Ray(Point3D p0, Vector direction) { //constructor.
        _pOrigin = p0;
        _direction = direction.normalize();//a vector in the same direction but with length 1
    }

    /**
     * getter for origin of the ray
     * @return p0
     */
    public primitives.Point3D getP0() {
        return _pOrigin;
    } //return the value of the point.

    /**
     * getter for direction vector of the ray
     * @return _dir
     */
    public primitives.Vector getDirection() {
        return _direction;
    } //return the value of the vector.

    @Override
    public boolean equals(Object o) { //compare between two objects.
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ray ray = (Ray) o;
        return Objects.equals(_pOrigin, ray._pOrigin) && Objects.equals(_direction, ray._direction);
    }

    @Override
    public String toString() { //return the values of the ray.
        return "Ray:" +
                "p0=" + _pOrigin +
                ", dir=" + _direction;
    }

    public Point3D getPoint(double t) {
        return _pOrigin.add(_direction.scale(t));
    }

    /**
     *findind the closest point to the ray
     * @param lst list of intersection points
     * @return the closest point
     */    public Point3D findClosestPoint(List<Point3D> lst) {
        Point3D p=null; //the closest point.
        if(lst==null)
            return p;
        double minDis=Double.POSITIVE_INFINITY; //the minimum distance is set to the biggest number for the comparison.
        for(Point3D p1:lst) { //for every point in the list of points.
            double dis=_pOrigin.distance(p1); //the distance of the point from the beginning of the ray.
            if(dis<minDis) {
                minDis = dis;
                p = p1;
            }
        }
        return p;
    }

    /**
     *findind the closest geometric point to the ray
     * @param lst list of intersection geometric points
     * @return the closest geometric point
     */
    public GeoPoint findClosestGeoPoint(List<Intersectable.GeoPoint> lst) {
        GeoPoint p=lst.get(0); //the closest point.
        if(lst==null)
            return null;
        double minDis=Double.POSITIVE_INFINITY; //the minimum distance is set to the biggest number for the comparison.
        for(GeoPoint geo:lst) { //for every point in the list of points.
            double dis=geo.point.distance(_pOrigin); //the distance of the point from the beginning of the ray.
            if(dis<minDis)
                p=geo;
        }
        return p;
    }
}
