package geometries;
/**
 * Unit tests for geometries.Triangle class
 *
 * @author Ruth & Odelia
 */
import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Vector;
import static org.junit.jupiter.api.Assertions.*;

class TriangleTest {

    @Test
    void testFindIntersections() {

    }//test if it returns a true normal
    void testGetNormal() {
        // ========== Equivalence Partition Test ==========
        Triangle t1 = new Triangle(new Point3D(0, 0, 0), new Point3D(1, 0, 0), new Point3D(0, 0, 1));
        assertEquals( t1.getNormal(Point3D.zero), new Vector(0,-1,0),"getNormal() result is not a solution");
    }
}