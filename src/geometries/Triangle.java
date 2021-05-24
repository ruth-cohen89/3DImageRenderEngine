package geometries;

import primitives.*;
import primitives.Util.*;
import java.util.List;

import static primitives.Util.isZero;


/**
 * class for Triangle
 * @author Odelia & Ruth
 */
public class Triangle extends Polygon { //implements Geometry

    /**
     * constructor
     * @param p1 point of the triangle
     * @param p2 point of the triangle
     * @param p3 point of the triangle
     */
    public Triangle(Point3D p1, Point3D p2, Point3D p3){
        super(p1,p2,p3);
    }

    /**
     *
     * @param ray to the geometry
     * @return list of geometries points that intersects with the geometry
     */
    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        //return super.findGeoIntersections(ray);
        List<GeoPoint>intersections=_plane.findGeoIntersections(ray);
        if (intersections==null)
            return  null;
        Point3D p0 = ray.getP0();
        Vector v = ray.getDirection();

        Vector v1 = _vertices.get(0).subtract(p0);
        Vector v2 = _vertices.get(1).subtract(p0);
        Vector v3 = _vertices.get(2).subtract(p0);

        double s1=v.dotProduct(v1.crossProduct(v2));
        if (isZero(s1))
            return  null;
        double s2 = v.dotProduct(v2.crossProduct(v3));
        if (isZero(s2))
            return null;
        double s3 = v.dotProduct(v3.crossProduct(v1));
        if (isZero(s3))
            return null;
        if ((s1 > 0 && s2 > 0 && s3 > 0) || (s1 < 0 && s2 < 0 && s3 < 0))
            return List.of(new GeoPoint(this, p0));
        return null;
    }
}
