package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Unit tests for geometries.Sphere class
 * @author Odelia and Ruth
 */

class SphereTest {

    void getNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: calculates normal to a sphere
        Sphere sphere = new Sphere(new Point3D(0, 0, 1), 1);
        Vector v1 = new Vector(0, 0, 1);
        Vector v2 = sphere.getNormal(new Point3D(0, 0, 2));
        assertEquals(v1, v2, "ERROR: wrong normal");
    }
    /**
     * Test method for {@link geometries.Sphere#findIntersections(primitives.Ray)}.
     */
    @Test
    public void testFindIntersections() {
        Sphere sphere = new Sphere(new Point3D(1, 0, 0), 1d);

        // ============ Equivalence Partitions Tests ==============

        Point3D p1 = new Point3D(0.0651530771650466, 0.355051025721682, 0);
        Point3D p2 = new Point3D(1.53484692283495, 0.844948974278318, 0);
        List<Point3D> result = sphere.findIntersections(new Ray(new Point3D(-1, 0, 0), new Vector(3, 1, 0)));

        // TC01: Ray's line is outside the sphere (0 points)
        assertEquals(sphere.findIntersections(new Ray(new Point3D(-1, 0, 0), new Vector(1, 1, 0))), "Ray's line out of sphere");

        // TC02: Ray starts before and crosses the sphere (2 points)
        assertEquals(2, result.size(), "Wrong number of points");
        if (result.get(0).getX() > result.get(1).getX())
            result = List.of(result.get(1), result.get(0));
        assertEquals(List.of(p1, p2), result, "Ray crosses sphere");

        // TC03: Ray starts inside the sphere (1 point)
        assertEquals(List.of(new Point3D(2, 0, 0)), sphere.findIntersections(new Ray(new Point3D(0.5, 0.5, -0.5), new Vector(1.5, -0.5, 0.5))), "Ray from inside sphere");

        // TC04: Ray starts after the sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point3D(2, 1, 0), new Vector(1, 1, 0.5))), "Sphere behind ray");

        // =============== Boundary Values Tests ==================

        // **** Group: Ray's line crosses the sphere (but not the center)
        // TC11: Ray starts at sphere and goes inside (1 points)
        assertEquals(List.of(new Point3D(2, 0, 0)), sphere.findIntersections(new Ray(new Point3D(1, 1, 0), new Vector(1, -1, 0))), "Ray from sphere inside");

        // TC12: Ray starts at sphere and goes outside (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point3D(1, 1, 0), new Vector(1, 1, 0))), "Ray from sphere outside");

        // **** Group: Ray's line goes through the center
        // TC13: Ray starts before the sphere (2 points)
        assertEquals(2, sphere.findIntersections(new Ray(new Point3D(1, -3, 0), new Vector(0, 1, 0))), "Wrong number of points");

        // TC14: Ray starts at sphere and goes inside (1 points)
        assertEquals(List.of(new Point3D(1, -1, 0)), sphere.findIntersections(new Ray(new Point3D(2, 0, 0), new Vector(-1, -1, 0))), "Ray from sphere and crosses sphere");

        // TC15: Ray starts inside (1 points)
        assertEquals(List.of(new Point3D(1, -1, 0)), sphere.findIntersections(new Ray(new Point3D(1, -0.5, 0), new Vector(0, -1, 0))), "Ray from inside sphere");

        // TC16: Ray starts at the center (1 points)
        assertEquals(List.of(new Point3D(1, -1, 0)), sphere.findIntersections(new Ray(new Point3D(1, 0, 0), new Vector(0, -1, 0))), "Ray from the center of a sphere");

        // TC17: Ray starts at sphere and goes outside (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point3D(1, -1, 0), new Vector(0, -1, 0))), "Ray from sphere outside");

        // TC18: Ray starts after sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point3D(1, 2, 0), new Vector(0, 1, 0))), "Ray outside sphere");

        // **** Group: Ray's line is tangent to the sphere (all tests 0 points)

        // TC19: Ray starts before the tangent point
        assertNull(sphere.findIntersections(new Ray(new Point3D(0, 1, 0), new Vector(1, 0 ,0))), "Tangent line, ray before sphere");

        // TC20: Ray starts at the tangent point
        assertNull(sphere.findIntersections(new Ray(new Point3D(1, 1, 0), new Vector(1, 0, 0))), "Tangent line, ray after sphere");

        // TC21: Ray starts after the tangent point
        assertNull(sphere.findIntersections(new Ray(new Point3D(2, 1, 0), new Vector(1, 0, 0))), "Tangent line, ray after sphere");

        // **** Group: Special cases

        // TC19: Ray's line is outside, ray is orthogonal to ray start to sphere's center line
        assertEquals(sphere.findIntersections(new Ray(new Point3D(3, 0, 0), new Vector(0, 0, 1))), "Ray orthogonal to ray head to center line");
    }
}