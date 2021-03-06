package geometries;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import primitives.*;
import static primitives.Util.*;

/**
 * Polygon class represents two-dimensional polygon in 3D Cartesian coordinate
 * system
 *
 * @author Dan
 */
public class Polygon extends Geometry { //מצולע
    /**
     * List of polygon's vertices
     */
    protected List<Point3D> vertices;
    /**
     * Associated plane in which the polygon lays
     */
    protected final Plane plane;

    /**
     * Polygon constructor based on vertices list. The list must be ordered by edge
     * path. The polygon must be convex.
     *
     * @param vertices list of vertices according to their order by edge path
     * @throws IllegalArgumentException in any case of illegal combination of
     *                                  vertices:
     *                                  <ul>
     *                                  <li>Less than 3 vertices</li>
     *                                  <li>Consequent vertices are in the same
     *                                  point
     *                                  <li>The vertices are not in the same
     *                                  plane</li>
     *                                  <li>The order of vertices is not according
     *                                  to edge path</li>
     *                                  <li>Three consequent vertices lay in the
     *                                  same line (180&#176; angle between two
     *                                  consequent edges)
     *                                  <li>The polygon is concave (not convex)</li>
     *                                  </ul>
     */
    public Polygon(Point3D... vertices) {
        if (vertices.length < 3)
            throw new IllegalArgumentException("A polygon can't have less than 3 vertices");
        this.vertices = List.of(vertices);
        // Generate the plane according to the first three vertices and associate the
        // polygon with this plane.
        // The plane holds the invariant normal (orthogonal unit) vector to the polygon
        plane = new Plane(vertices[0], vertices[1], vertices[2]);
        if (vertices.length == 3)
            return; // no need for more tests for a Triangle

        Vector n = plane.getNormal(null);

        // Subtracting any subsequent points will throw an IllegalArgumentException
        // because of Zero Vector if they are in the same point
        Vector edge1 = vertices[vertices.length - 1].subtract(vertices[vertices.length - 2]);
        Vector edge2 = vertices[0].subtract(vertices[vertices.length - 1]);

        // Cross Product of any subsequent edges will throw an IllegalArgumentException
        // because of Zero Vector if they connect three vertices that lay in the same
        // line.
        // Generate the direction of the polygon according to the angle between last and
        // first edge being less than 180 deg. It is hold by the sign of its dot product
        // with
        // the normal. If all the rest consequent edges will generate the same sign -
        // the
        // polygon is convex ("kamur" in Hebrew).
        boolean positive = edge1.crossProduct(edge2).dotProduct(n) > 0;
        for (int i = 1; i < vertices.length; ++i) {
            // Test that the point is in the same plane as calculated originally
            if (!isZero(vertices[i].subtract(vertices[0]).dotProduct(n)))
                throw new IllegalArgumentException("All vertices of a polygon must lay in the same plane");
            // Test the consequent edges have
            edge1 = edge2;
            edge2 = vertices[i].subtract(vertices[i - 1]);
            if (positive != (edge1.crossProduct(edge2).dotProduct(n) > 0))
                throw new IllegalArgumentException("All vertices must be ordered and the polygon must be convex");
        }
    }

    @Override
    public Vector getNormal(Point3D point) {
        return plane.getNormal(null);
    }

    /**
     * Finding intersection GeoPoints with the Polygon geometric shape
     * @param ray The ray that cuts the Polygon
     * @param maxDistance The maximum possible distance
     * @return List of GeoPoints of intersection and if not then returns null
     */
    @Override
//    public List<GeoPoint> findGeoIntersections(Ray ray,  double maxDistance) {
//        List<GeoPoint> result = plane.findGeoIntersections(ray, maxDistance);
//
//        if (result == null) {
//            return null;
//        }
//
//        Point3D P0 = ray.getP0();
//        Vector v = ray.getDir();
//        result.get(0).geometry=this;
//        Point3D P1 = vertices.get(1);
//        Point3D P2 = vertices.get(0);
//
//        Vector v1 = P1.subtract(P0).normalized();
//        Vector v2 = P2.subtract(P0).normalized();
//
//        double sign = alignZero(v.dotProduct(v1.crossProduct(v2)));
//
//        if (isZero(sign)) {
//            return null;
//        }
//
//        boolean positive = sign > 0;
//
//        //iterate through all vertices of the polygon
//        for (int i = vertices.size() - 1; i > 0; --i) {
//            v1 = v2;
//            v2 = vertices.get(i).subtract(P0);
//
//            sign = alignZero(v.dotProduct(v1.crossProduct(v2)));
//            if (isZero(sign)) {
//                return null;
//            }
//
//            if (positive != (sign > 0)) {
//                return null;
//            }
//        }
//
//        return List.of(new GeoPoint(this, result.get(0).point));
//    }

    public List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {

        // First of all, check if there is a point of intersection with the plane
        if (plane.findGeoIntersections(ray, maxDistance) == null)
            return null;

        Point3D p0 = ray.getP0();
        Vector v = ray.getDir();

        List<Vector> vectors = new LinkedList<>();
        List<Vector> normalVectors = new LinkedList<>();


        for (Point3D pt : vertices) {
            Vector Vi = pt.subtract(p0);
            vectors.add(Vi);
        }

        int n = vectors.size() - 1;

        for (int i = 0; i < n; i++) {

            Vector Vi = vectors.get(i);
            Vector Vii = vectors.get(i + 1);

            Vector Ni = (Vi.crossProduct(Vii)).normalize();

            normalVectors.add(Ni);
        }

        Vector Vn = vectors.get(n);
        Vector V1 = vectors.get(0);

        Vector Nn = (Vn.crossProduct(V1)).normalize();

        normalVectors.add(Nn);

        List<Double> Vns = new LinkedList<>();

        for (Vector N : normalVectors) {
            double Vni = alignZero(N.dotProduct(v));
            // one Vni equals to zero is enough to determine that we have no intersection points
            if (isZero(Vni)) {
                return null;
            } else Vns.add(Vni);
        }

        // check that all of the elements
        if (!Vns.stream().allMatch(i -> i > 0) && !Vns.stream().allMatch(i -> i < 0)) {
            return null;
        } else {
            Plane plane = new Plane(vertices.get(0), vertices.get(1), vertices.get(2));
            return List.of(new GeoPoint(this, plane.findGeoIntersections(ray, maxDistance).get(0).point));
        }
    }

    /**
     * perform full comparison between a given object and this
     *
     * @param o - object
     * @return - whether the object equals to this or not
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Polygon polygon = (Polygon) o;
        return vertices.equals(polygon.vertices) && plane.equals(polygon.plane);
    }


}


