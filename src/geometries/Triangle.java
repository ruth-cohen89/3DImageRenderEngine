package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import static primitives.Util.*;
import java.util.List;

//Triangle: Polygon with vertices//
public class Triangle extends Polygon   {  //An heir from the polygon//

    //The constructor gets three points and activates the master builder accordingly//
    public Triangle(Point3D p1, Point3D p2, Point3D p3){
        super(p1,p2,p3);
    }

    //toString...//
    @Override
    public String toString() {
        return "Triangle{" +
                "vertices=" + vertices +
                ", plane=" + plane +
                '}';
    }
    public Vector getNormal( Point3D point){
        return super.getNormal(point);
    }

    /**                                                                       fix ADD DISTANCE
     * Finding intersection points with the Sphere geometric Triangle
     * @param ray The ray that cuts the Triangle
     * @return List of points of intersection and if not then returns null
     */
  //  @Override
//    public List<GeoPoint> findGeoIntersections(Ray ray) {
//        List<GeoPoint> intersections = plane.findGeoIntersections(ray);
//        if (intersections == null) return null;
//
//        Point3D p0 = ray.getP0(); //point of the ray
//        Vector v = ray.getDir(); // vector of the ray
//
//        Vector v1 = vertices.get(0).subtract(p0); //v1= p1-p0
//        Vector v2 = vertices.get(1).subtract(p0); // v2=p2-p0
//        Vector v3 = vertices.get(2).subtract(p0); //v3= p3-p0
//
//        double s1 = v.dotProduct(v1.crossProduct(v2)); //s1= v*(v1Xv2)
//        if (isZero(s1)) return null;
//        double s2 = v.dotProduct(v2.crossProduct(v3));//s2= v*(v2Xv3)
//        if (isZero(s2)) return null;
//        double s3 = v.dotProduct(v3.crossProduct(v1));//s3= v*(v3Xv1)
//        if (isZero(s3)) return null;
//
//        return ((s1 > 0 && s2 > 0 && s3 > 0) || (s1 < 0 && s2 < 0 && s3 < 0))
//                ? List.of(new GeoPoint(this,intersections.get(0).point))
//                : null;
//    }
}
