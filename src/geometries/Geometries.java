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
     *
     * @param ray
     * @return list of intersections of the geometry with the points
     */
    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
        List<GeoPoint> intersections = null;
        for (Intersectable geometry : _intersectables) {
            List<GeoPoint> geoIntersections = geometry.findGeoIntersections(ray, maxDistance); //find intersections point to every single geometry
            //  if there are elements in geoIntersections – add them to intersections
            if (geoIntersections != null) {
                if (intersections == null) {
                    intersections = new LinkedList<>();
                }
                intersections.addAll(geoIntersections);
            }
        }
        return intersections;
    }
}