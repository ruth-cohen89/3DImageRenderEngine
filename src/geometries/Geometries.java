package geometries;

import primitives.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Geometries implements Intersectable {
    private List<Intersectable> _geometries=null;
    public Geometries() { //default constructor
        _geometries=new ArrayList<Intersectable>();
    }

    public Geometries(Intersectable... geometries) { //constructor
        _geometries=new ArrayList<Intersectable>();
        add(geometries);
    }

    public void add(Intersectable... geometries) { //adding to the collection
        for(Intersectable geo:geometries) {
            _geometries.add(geo);
        }
    }

    @Override
    public List<Point3D> findIntersections(Ray ray) {
        List<Point3D> intersections=null;
        for(Intersectable geo:_geometries) {
            List<Point3D> temp=geo.findIntersections(ray);
            if(temp!=null) {
                if(intersections==null) {
                    intersections=new ArrayList<Point3D>();
                    intersections.addAll(temp);
                }
            }
        }
        return intersections;
    }
}
