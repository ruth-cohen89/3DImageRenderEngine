package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Vector;

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


}