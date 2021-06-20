package geometries;

import primitives.Point3D;
import primitives.Ray;
import java.util.List;
import java.util.stream.Collectors;


/**
 * interface for Intersectable
 * represents intersectable
 * @author Odelia & Ruth
 */
public interface Intersectable {

    /**
     *
     * @param ray to the geometry
     * @return list of points that intersects with the geometry
     * (the cutting points of a ray in a geometry- נקודות חיתוך של הקרן עם הגוף)
     *find all intersection points from the ray
     */
    default List<Point3D> findIntersections(Ray ray) {
        var geoList = findGeoIntersections(ray);
        return geoList == null ? null
                : geoList.stream().map(gp -> gp.point)
                .collect(Collectors.toList());

    }


    /**
     *recieves a ray
     * @param ray to the geometry
     * @return list of GeoPoint-geometries and their intersection points
     * (bonus)
     */
    default List<GeoPoint> findGeoIntersections(Ray ray){
        return findGeoIntersections(ray, Double.POSITIVE_INFINITY);
    }

    /**
     *recieves ray and maxDistance
     * @param ray to the geometry
     * @return list of GeoPoint-geometries and their intersection points
     */
    public  List<GeoPoint> findGeoIntersections (Ray ray, double maxDistance );

    /**
     * static class for Geo Point
     * @author Odelia & Ruth
     */
    public static class GeoPoint {
        public Geometry geometry;
        public Point3D point;

        /**
         * constructor
         * @param geometry the geometry form we are using
         * @param point the intersection point
         */
        public GeoPoint(Geometry geometry, Point3D point) {
            this.geometry = geometry;
            this.point = point;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            GeoPoint geoPoint = (GeoPoint) o;
            return geometry.equals(geoPoint.geometry) && point.equals(geoPoint.point);
        }
    }


}
