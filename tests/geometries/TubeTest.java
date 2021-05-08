package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Unit tests for geometries.Plane class
 * @author Odelia and Ruth
 */
class TubeTest {

    //test if the function returns a correct normal
    @Test
    void testGetNormal() {
        //t1 is represented by radius and height(ray)
        Tube t1 = new Tube(new Ray(new Point3D(0, 0, 1), new Vector(0, 0, 1)), 1);
        // ============ Equivalence Partitions Tests ==============
        // TC01: Simple case of generate normal
        Vector v1 = new Vector(0, 1, 0);
        Vector v2 = t1.getNormal(new Point3D(0, 1, 2));
        assertEquals(v1, v2, "ERROR: wrong normal");

        // =============== Boundary Values Tests ==================

        // TC10: The point is in front of the head of the ray
        Vector v3 = new Vector(0, 1, 0);
        Vector v4 = t1.getNormal(new Point3D(0, 1, 1));
        assertEquals(v3, v4, "ERROR: wrong normal");
    }
}//f