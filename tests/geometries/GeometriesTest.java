package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for geometries.Geometries class
 * @author Odelia and Ruth
 */

class GeometriesTest {

    /** Test method for {@link geometries.Geometries#findIntersections(primitives.Ray)}. */

    @Test
    void findIntersections() {
        Plane p1=new Plane(new Point3D(3, 0, 4), new Point3D(0, 0, 1), new Point3D(1, 0, 0));
        Triangle t1=new Triangle(new Point3D(3, -5, 0), new Point3D(1, -5, 0), new Point3D(-2, -5, 5));
        Sphere s1=new Sphere(new Point3D(1, 3, 0), 1d);

        // ============ Equivalence Partitions Tests ==============

        //TC01: Not all the geometries intersects, but some of them are.
        Geometries g1=new Geometries();
        g1.add(p1, t1, s1);
        Ray r1=new Ray(new Point3D(2, 1,0.5), new Vector(0, -8, 0));
        assertEquals(g1.findIntersections(r1).size(), 1, "findIntersections() result is not empty");

        //============Boundary Values Tests==========

        //TC11: The collection is empty.
        Geometries g2=new Geometries();
        Ray r2=new Ray(new Point3D(-2, -2,-2), new Vector(3, 3, 2));
        assertNull(g2.findIntersections(r2), "findIntersects() result is not empty");

        //TC12: The collection is not empty but none of the geometries intersects.
        Geometries g3=new Geometries();
        g3.add(p1, t1, s1);
        Ray r3=new Ray(new Point3D(0,2, 0), new Vector(-3,0, 5));
        assertEquals(g3.findIntersections(r3), null, "findIntersections() result can't be with intersects");

        //TC13: Only one geometry from the collection is intersecting.
        Geometries g4=new Geometries();
        g4.add(p1, t1, s1);
        Ray r4=new Ray(new Point3D(4, 3.5, 0), new Vector(-8, 0, 0));
        assertEquals(g4.findIntersections(r4).size(), 2, "findIntersections() result is not intersecting one geometry");

        //TC14: All the geometries from the collection are intersecting.
        Geometries g5=new Geometries();
        g5.add(p1,t1, s1);
        Ray r5=new Ray(new Point3D(1, 5, 0.5), new Vector(0, -12, 0));
        assertEquals(g5.findIntersections(r5).size(), 4,"findIntersections() result is no geometry is intersecting");
    }
}