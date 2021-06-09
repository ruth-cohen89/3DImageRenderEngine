package geometries;

import primitives.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Arrays;


/**
 * collection of geometries treated as one geometry
 * by composite framework (geometries is a list of Intersectable)
 * @author Odelia & Ruth
 */
public class Geometries implements Intersectable {

    final List<Intersectable> _intersectables = new LinkedList<>();;

    /**
     *
     * @param _geometries non limited array of Geometry implementing Intersectable
     */
    public Geometries(Intersectable... _geometries){
        add(_geometries);

    }
    /**
     * method that adds the geometries to the list
     * @param geometries
     */
    public void add(Intersectable... geometries) {
        _intersectables.addAll(Arrays.asList(geometries));
    }



    /**
     *implements findGeoIntersections that was declares on Intersectable
     * @param ray
     * @return list of intersections of the geometry with the points
     */
    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
        List<GeoPoint> intersections = null;
        for (Intersectable geometry : _intersectables) { //for each Geometry find his GeoIntersections with ray
            List<GeoPoint> geoIntersections = geometry.findGeoIntersections(ray, maxDistance); //find intersections point to every single geometry
            // list of points which their distance from head of ray is not bigger than maxDistance(maximum)

            if (geoIntersections != null) {
                if (intersections == null) {
                    intersections = new LinkedList<>();//for first iteration create new list
                }
                intersections.addAll(geoIntersections);//otherwise, add them
            }
        }
        return intersections;
    }
}