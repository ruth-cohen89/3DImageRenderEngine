package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.*;
/**
 * Unit tests for geometries.Plane class
 * @author Odelia and Ruth
 */

class PlaneTest {

    @Test
    public void testConstructor() {

        // =============== Boundary Values Tests ==================

        // TC01: first and second points merge
        try {
            Plane plane = new Plane(new Point3D(1,0,0), new Point3D(1,0,0), new Point3D(2,9,8));
            fail("ERROR: can not build plane with two equal points.");
        } catch (IllegalArgumentException e) {}

        // TC02: Points on the same line
        try {
            Plane plane = new Plane(new Point3D(1,0,0), new Point3D(2,0,0), new Point3D(3,0,0));
            fail("ERROR: can not build plane with three points that are found on the same line.");
        } catch (IllegalArgumentException e) {}

    }


    /**
     * Test method for {@link Plane#getNormal(Point3D)} }.
     */
    @Test
    void getNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: the calculation of the normal to a plane
        Plane plane= new Plane(new Point3D( 0,1,1),new Point3D(0,0,2), new Point3D(0,0,3));
        Vector v1= new Vector(1,0,0);
        Vector v3= new Vector(-1,0,0);
        Vector v2=plane.getNormal(new Point3D(0,1,1));
        if (!v1.equals(v2)&& !v3.equals(v2))
            fail("ERROR: getNormal result is incorrect");
    }

    /**
     * Test method for {@link Plane#findIntersections(Ray)} }.
     */
    @Test
    void testFindIntersections() {
        // ============ Equivalence Partitions Tests ==============
        Plane p1= new Plane(new Point3D(3, 0, 4), new Point3D(0, 0, 1), new Point3D(1, 0, 0));
        // TC01: The ray intersects the plane
        Ray r1=new Ray(new Point3D(-2, -2, -2), new Vector(3, 3, 2));
        assertEquals(1, p1.findIntersections(r1).size(), "findIntersections() result does not intersect the plane");
        assertEquals( p1.findIntersections(r1).size(), 1, "findIntersections() result does not intersects the plane");

        //TC02:The ray does not intersect the plane.
        Ray r2= new Ray(new Point3D(2, 2, 2), new Vector(3,3, 2));
        assertEquals(1, p1.findIntersections(r2).size(), "findIntersections() result intersects the plane");


        //============Boundary Values Tests==========
        //TC11: The ray is parallel to the plane- the ray is included in the plane.
        Ray r3= new Ray(new Point3D(2, 0, 2), new Vector(3, 0,3).normalize());
        assertEquals(null, p1.findIntersections(r3), "findIntersections() result does not include in the plane");

        //TC12: The ray is parallel to the plane- the ray is not included in the plane.
        Ray r4=new Ray(new Point3D(2, 4, 2), new Vector(3, 0, 3).normalize());
        assertEquals(null, p1.findIntersections(r4), "findIntersections() result does not include in the plane");

        //TC13: The ray is orthogonal to the plane- the ray starts before the plane.
        Ray r5=new Ray(new Point3D(1, 5, 3), new Vector(0, -5, 0));
        assertEquals(p1.findIntersections(r5), List.of(new Point3D(1, 0, 3)), "findIntersections() result does not meet in the plane");
        assertEquals(p1.findIntersections(r5).size(), 1, "findIntersections() result does not meet in the plane");

        //TC14: The ray is orthogonal to the plane- the ray starts in the plane.
        Ray r6=new Ray(new Point3D(1, 0, 3), new Vector(0, -5, 0));
        assertEquals(p1.findIntersections(r6), null, "findIntersections() result does not intersect the plane");

        //TC15: The ray is orthogonal to the plane- the ray starts after the plane.
        Ray r7= new Ray(new Point3D(0, 0, 3), new Vector(0, 0, 1));
        assertEquals(p1.findIntersections(r7), null, "findIntersections() result does not meet the plane");

        //TC16: The ray is not orthogonal or parallel to the plane and begins at the plane.
        Ray r8=new Ray(new Point3D(4, 0, 4), new Vector(-3, 2, -1));
        assertEquals(p1.findIntersections(r8), null, "findIntersections() result does not intersect the plane");

        //TC17: The ray is not orthogonal or parallel to the plane and begins in the same point which appears as reference point in the plane.
        Ray r9=new Ray(new Point3D(3, 0, 4), new Vector(-2, 2, -1));
        assertEquals(p1.findIntersections(r9), null, "findIntersections() result does not intersect the plane");
    }
}