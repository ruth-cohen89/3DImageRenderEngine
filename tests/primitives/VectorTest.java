package primitives;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

class VectorTest {
    Vector v1= new Vector(1,2,3);
    Vector v2= new Vector(-2,-4,-6);
    Vector v3= new Vector(0,3,-2);

    @Test
    void testTestEquals() {
    }

    //@Test
//    void testCrossProduct() {
//    }

    @Test
    void testDotProduct() {
    }


    @Test
    void testVectorZero(){
        assertThrows(IllegalArgumentException.class, ()-> new Vector(0,0,0), "nunun!");
    }
    /**
     * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
     */
    @Test
    public void testCrossProduct() {
       // Vector v3 = new Vector(0, 3, -2);

        // ============ Equivalence Partitions Tests ==============
        Vector v3 = new Vector(0, 3, -2);
        Vector vr = v1.crossProduct(v3);

        // TC01: Test that length of cross-product is proper (orthogonal vectors taken
        // for simplicity)
        assertEquals(v1.length() * v3.length(),
                 vr.length(),
                0.00001,
                "crossProduct() wrong result length");

        // TC02: Test cross-product result orthogonality to its operands
        assertTrue(isZero(vr.dotProduct(v1)),"crossProduct() result is not orthogonal to 1st operand");
        assertTrue(isZero(vr.dotProduct(v3)),"crossProduct() result is not orthogonal to 2nd operand");

        // =============== Boundary Values Tests ==================
        // TC11: test zero vector from cross-productof co-lined vectors
        //Vector v3 = new Vector(-2, -4, -6);
        assertThrows(IllegalArgumentException.class,
                () -> v1.crossProduct(v2),
                "crossProduct() for parallel vectors does not throw an exception"
                    );
        // try {
        //     v1.crossProduct(v2);
        //     fail("crossProduct() for parallel vectors does not throw an exception");
        // } catch (Exception e) {}
    }

    @Test
    void testLengthSquared() {
    }

    @Test
    void testLength(){
        Vector vCopy = new Vector(v.getHead());
        Vector vCopyNormalize = vCopy.normalize();
        assertTrue(isZero(vCopyNormalize.length()-1),"ERROR: normalize() result is not a unit vector");
    }

    @Test
    void testNormalize() {
        // test vector normalization vs vector length and cross-product
        Vector v = new Vector(3.5,-5,10);
        v.normalize();
        assertEquals(1, v.length(),1e-10);

        assertThrows(IllegalArgumentException.class,
                ()-> {Vector w=new Vector(0,0,0);},
                "head cannot be (0,0,0)");
    }

    @Test
    void testNormalized() {
        Vector u = v.normalized();
        assertEquals (u,v,"ERROR: normalizated() function does not create a new vector");
    }
}