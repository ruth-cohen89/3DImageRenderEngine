package primitives;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static primitives.Util.isZero;

class VectorTest {
    //sample vectors
    Vector v1= new Vector(1,2,3);//bigger than 1
    Vector v2= new Vector(0.5,0,0);//smaller than 1
    Vector v3= new Vector(1,0,0);//exactly 1

    @Test
    void testTestEquals() {
    }


    @Test
    void testDotProduct() {
    }


    @Test//if vector 0 is created then constructor throws an exception, else the message is displayed.
    void testVectorZero(){
        assertThrows(IllegalArgumentException.class,
                    ()-> new Vector(0,0,0),
                    "no exception was thrown by vector constructor when creating a zero vector");
    }

    /**
     * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
     */
    @Test
    public void testCrossProduct() {

        // ============ Equivalence Partitions Tests ==============
        Vector v3 = new Vector(0, 3, -2);
        Vector vr = v1.crossProduct(v3);

        // TC01: Test that length of cross-product is proper (orthogonal vectors taken
        // for simplicity) if the length of result vector and of the multiplication between the 2 is equal
        //then it means testCrossProduct is done correctly.
        assertTrue(isZero(v1.length() * v3.length()-vr.length()),
                        "crossProduct() result is wrong");

        // TC02: Test cross-product result orthogonality to its operands
        //if v1 is orthogonal to v3 then scalar-product multiplication between v1 and vr and also between v3 and vr should be zero.
        //and it means crossProduct() result is orthogonal to both v1 & v3.
        //if not true then throws an exception.
        assertTrue(isZero(vr.dotProduct(v1)),"crossProduct() result is not orthogonal to 1st operand");
        assertTrue(isZero(vr.dotProduct(v3)),"crossProduct() result is not orthogonal to 2nd operand");

        // =============== Boundary Values Tests ==================
        // TC11: test zero vector from cross-product of co-lined vectors
        //compare 2 co-linear vectors and test if crossProduct on them returns 0
        Vector v10 = new Vector(-2, -4, -6);
        assertThrows(IllegalArgumentException.class,
                () -> v1.crossProduct(v10),
                "crossProduct() for co-lined vectors does not throw an exception"
                    );
    }

    //testing if testLengthSquared() returns a true value
    @Test
    void testLengthSquared() {
            assertTrue(isZero(v1.lengthSquared() - 14),
                    "ERROR: lengthSquared() wrong value");
    }

    //if (!isZero(v1.lengthSquared() - 14))

    //testing if Length() returns a true length of the vector
    @Test
    void testLength(){
        assertEquals(v1.length(),Math.sqrt(14),"ERROR: Length() gives wrong value");
    }

    //testing if testNormalize() returns a true value???fix
    @Test
    void testNormalize() {

        // ============ Equivalence Partitions Tests ==============
        //testing a vector which is bigger than 1(equivalence class)
        Vector u1 = v1;
        u1.normalize();
        assertTrue(isZero(1-u1.length()),"Normalize() doesn't normalize correctly");
       // assertEquals(1, u1.length(),0.00001);

        //testing a vector which is smaller than 1 (equivalence class)
        Vector u2 = v2;
        u2.normalize();
        assertTrue(isZero(1-u2.length()),"Normalize() doesn't normalize correctly");
       // assertEquals(1, u2.length(),1e-10);

        // =============== Boundary Values Tests ==================
        //testing a vector which is equivalent to 1 (boundary value)
        Vector u3 = v3;
        u3.normalize();
        assertTrue(isZero(1-u3.length()),"Normalize() doesn't normalize correctly");

        //assertEquals(1, u3.length(),1e-10);
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

        // ============ Equivalence Partitions Tests ==============
        //testing a vector which is bigger than 1(equivalence class)
        Vector u1 = new Vector(1/Math.sqrt(14),2/Math.sqrt(14),3/Math.sqrt(14));
        assertEquals (v1.normalized(),u1,"ERROR: normalized() gives wrong value");

        //testing a vector which is smaller than 1 (equivalence class)
        Vector u2 = new Vector(1,0,0);
        assertEquals (v2.normalized(),u2,"ERROR: normalized() gives wrong value");

        // =============== Boundary Values Tests ==================
        //testing a vector which is equivalent to 1 (boundary value)
        Vector u3 = new Vector(1,0,0);
        assertEquals (v3.normalized(),u3,"ERROR: normalized() gives wrong value");


    }
}

