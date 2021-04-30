package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RayTest {

    /**
     * Test method for {@link primitives.Ray#findClosestPoint(java.util.List<Point3D>)}.
     */
    @Test
    void findClosestPoint() {

        // ============ Equivalence Partitions Tests ==============

        //TC01:The middle point in the list of points is the closest to the ray

        // =============== Boundary Values Tests ==================

        //TC11:The list is empty.

        //TC12:The first point of the list is the closest to the ray.

        //TC13:The last point of the list is the closest to the ray.
    }
}