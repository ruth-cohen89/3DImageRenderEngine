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

    private List<Intersectable> _intersectables = null;

    /**
     *
     * @param _geometries non limited array of Geometry implementing Intersectable
     */
    public Geometries(Intersectable... _geometries){
        _intersectables=new LinkedList<Intersectable>();
        add(_geometries);

    }

    public Geometries() {
        _intersectables = new LinkedList<Intersectable>();
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
        LinkedList<GeoPoint> intersections = null;
        for (Intersectable geometry : _intersectables) { //for each Geometry find his GeoIntersections with ray
            List<GeoPoint> geoIntersections = geometry.findGeoIntersections(ray, maxDistance); //find intersections point to every single geometry
            // list of points which their distance from head of ray is not bigger than maxDistance(maximum)

            if (geoIntersections != null) {
                if (intersections == null) {
                    intersections = new LinkedList<GeoPoint>();//for first iteration create new list
                }
                intersections.addAll(geoIntersections);//otherwise, add them
            }
        }
        return intersections;
    }
}