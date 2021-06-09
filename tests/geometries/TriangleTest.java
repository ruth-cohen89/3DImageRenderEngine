package geometries;
/**
 * Unit tests for geometries.Triangle class
 *
 * @author Ruth & Odelia
 */
import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test method for {@link geometries.Sphere#findIntersections(primitives.Ray)}.
 */

class TriangleTest {

    @Test
    void testFindIntersections() {
        Point3D p1= new Point3D(-3, 2, 0);
        Point3D p2= new Point3D(1, 1, 0);
        Point3D p3= new Point3D(1, 4, 0);
        Triangle t1= new Triangle(p1, p2, p3);
        //==========Equivalence Partitions Tests==========
        //TC01: The ray intersects with the plane inside the triangle.
        Ray r1=new Ray(new Vector(0, -4, 1), new Point3D(2, 1, 0));
        assertEquals(t1.findIntersections(r1), null, "findIntersections() result is not inside the triangle");

        //TC02: The ray intersects with the plane outside against the edge.
        Ray r2=new Ray(new Vector(2, -1, 5), new Point3D(2, 1, 0));
        assertEquals(t1.findIntersections(r2), null, "findIntersections() result is not outside against the edge");

        //TC03: The ray intersects with the plane outside against the vertex.
        Ray r3=new Ray(new Vector(4, -2, -3.5), new Point3D(0, 2, 3));
        assertEquals(t1.findIntersections(r3), null, "findIntersections() result is not outside against the vertex");

        //==========Boundary Values Tests ==========

        //TC11:The ray intersects with the plane on the edge.
        Ray r4=new Ray(new Vector(4, -4, 0), new Point3D(2, 1, 0));
        assertEquals(t1.findIntersections(r4), null, "findIntersections() result is not on the edge");

        //TC12:The ray intersects with the plane in the vertex.
        Ray r5=new Ray(new Vector(0, -2, 0), new Point3D(2, 1, 0));
        assertEquals(t1.findIntersections(r5), null, "findIntersections() result is not on the vertex");

        //TC13:The ray intersects with the plane on the edge's continuation.
        Ray r6=new Ray(new Vector(6, -3, 0), new Point3D(2, 1, 0));
        assertEquals(t1.findIntersections(r6), null, "findIntersections() result is not on edge's continuation");

    }
    //test if it returns a true normal
    void testGetNormal() {
        // ========== Equivalence Partition Test ==========
        Triangle t1 = new Triangle(new Point3D(0, 0, 0), new Point3D(1, 0, 0), new Point3D(0, 0, 1));
        assertEquals( t1.getNormal(Point3D.zero), new Vector(0,-1,0),"getNormal() result is not a solution");
    }
}