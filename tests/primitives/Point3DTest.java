package primitives;

import org.junit.jupiter.api.Test;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;

class Point3DTest {

    Point3D p1 = new Point3D(1, 2, 3);
    Point3D p2=new Point3D(1, 1, 1);
    @Test
    void testSubtract() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: A simple case of subtract
        assertEquals(new Vector(1, 1, 1),new Point3D(2, 3, 4).subtract(p1),
                "ERROR: subtract result is incorrect");

    }

    @Test
    void testAdd() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: A simple case of add
        assertEquals(Point3D.zero, p1.add(new Vector(-1, -2, -3)),
                "ERROR: add result is incorrect");
    }

    @Test
    void testDistanceSquared() {
        assertEquals(5, p1.distanceSquared(p2),
                "ERROR: distanceSquared result is incorrect");
    }

    @Test
    void testDistance() {
        assertEquals(Math.sqrt(5), p1.distance(p2),
                "ERROR: distance result is incorrect");
    }
}