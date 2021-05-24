package geometries;

import primitives.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * class for Geometries
 * @author Odelia & Ruth
 */
public class Geometries implements Intersectable {
    private List<Intersectable> _geometries = null;

    public Geometries() { //default constructor
        _geometries = new ArrayList<Intersectable>();
    }

    public Geometries(Intersectable... geometries) { //constructor
        _geometries = new ArrayList<Intersectable>();
        add(geometries);
    }

    public void add(Intersectable... geometries) { //adding to the collection
        for (Intersectable geo : geometries) {
            _geometries.add(geo);
        }
    }

    /**
     *
     * @param ray
     * @return list of intersections of the geometry with the points
     */
    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        List<GeoPoint> intersections = null;
        for (Intersectable geometry : _geometries) {
            List<GeoPoint> geoIntersections = geometry.findGeoIntersections(ray);
            //  if there are elements in geoIntersections – add them to intersections
            if (geoIntersections != null) {
                if (intersections == null) {
                    intersections = new LinkedList<GeoPoint>();
                }
                intersections.addAll(geoIntersections);
            }
        }
        return intersections;
    }
}
