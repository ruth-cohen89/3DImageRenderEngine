package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Vector;

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
    @Test
    void testFindIntersections() {
    }

}