package primitives;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//@author Ruth & Odelia
class RayTest {

    /**
     * Test method for {@link primitives.Ray#findClosestPoint(java.util.List<Point3D>)}.
     */
    @Test
    void findClosestPoint() {

        Point3D p1 = new Point3D(0,2,2);
        Point3D p2 = new Point3D(0,5,0);
        Point3D p3 = new Point3D(0,0,11);
        Point3D p4 = new Point3D(0,0,1);

        List<Point3D> points = List.of(p1, p2, p3, p4);
        // ============ Equivalence Partitions Tests ==============

        //TC01:The middle point in the list of points is the closest to the ray
        assertEquals(p3, new Ray(new Vector(0, 0, 1), new Point3D(0, 0, 9)).findClosestPoint(points), "ERROR: wrong closest point");

        // =============== Boundary Values Tests ==================

        // TC10: The list is empty31
        assertNull(new Ray(new Vector(0, 0, 1), new Point3D(0, -8, 5)).findClosestPoint(new ArrayList<>()), "ERROR: the list is empty");

        // TC11: The first point in the list is the closest
        assertEquals(p1, new Ray(new Vector(0, 0, 1), new Point3D(0, 1, 2)).findClosestPoint(points), "ERROR: wrong closest point");

        // TC12: The last point in the list is the closest
        assertEquals(p4, new Ray(new Vector(0, 0, 1), new Point3D(0, 0, 2)).findClosestPoint(points), "ERROR: wrong closest point");
    }
}