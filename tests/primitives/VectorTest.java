package primitives;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

class VectorTest {
    //t
    Vector v1= new Vector(1,2,3);//bigger than 1
    Vector v2= new Vector(0.5,0,0);//smaller than 1
    Vector v3= new Vector(1,0,0);//exactly 1

    @Test
    void testTestEquals() {
    }


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
        if (!isZero(v1.lengthSquared() - 14))
            out.println("ERROR: lengthSquared() wrong value");
    }

    //assertEquals("...", new Vector(1,2,3).length(), 3.5...)
    // Vector vCopy = new Vector(v1.getHead());
    //   Vector vCopyNormalize = vCopy.normalize();'

    //testing if Length() returns a true length of the vector
    @Test
    void testLength(){
        if (!isZero(v1.length() - Math.sqrt(14)))
            out.println("ERROR: Length() returns wrong value");
       // assertEquals(v1.length(),Math.sqrt(14),"ERROR: Length() gives wrong value");
    }

    @Test
    void testNormalize() {
        //testing a vector which is bigger than 1(equivalence class)
        Vector u1 = v1;
        u1.normalize();
        assertEquals(1, u1.length(),1e-10);

        //testing a vector which is smaller than 1 (equivalence class)
        Vector u2 = v2;
        u2.normalize();
        assertEquals(1, u2.length(),1e-10);

        //testing a vector which is equivalent to 1 (boundary value)
        Vector u3 = v3;
        u3.normalize();
        assertEquals(1, u3.length(),1e-10);
        // test vector normalization vs vector length and cross-product

        //Vector v = new Vector(3.5,-5,10);
        //v.normalize();
        //assertEquals(1, v.length(),1e-10);

        //assertThrows(IllegalArgumentException.class,
            //    ()-> {Vector w=new Vector(0,0,0);},
              //  "head cannot be (0,0,0)");
    }

    //"ERROR: normalized() function does not create a new vector"


    //testing if Normalized() returns a true new normal to vector
    @Test
    void testNormalized() {

        //testing a vector which is bigger than 1(equivalence class)
        Vector u1 = new Vector(1/Math.sqrt(14),2/Math.sqrt(14),3/Math.sqrt(14));
        assertEquals (v1.normalized(),u1,"ERROR: normalized() gives wrong value");

        //testing a vector which is smaller than 1 (equivalence class)
        Vector u2 = new Vector(1,0,0);
        assertEquals (v2.normalized(),u2,"ERROR: normalized() gives wrong value");

        //testing a vector which is equivalent to 1 (boundary value)
        Vector u3 = new Vector(1,0,0);
        assertEquals (v3.normalized(),u3,"ERROR: normalized() gives wrong value");


    }
}

