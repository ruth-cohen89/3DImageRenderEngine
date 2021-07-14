package primitives;


import static primitives.Point3D.zero;

//**Vector - A foundational object in geometry with direction and size,
// defined by the end point (when the starting point - the beginning of the axes).
//**//


/**
 * class for Vector
 * @author Odelia & Ruth
 */
public class Vector {
    Point3D _head;

    /*constructor with head*/
    public Vector(Point3D head) {
        //if zero throw error
        if (zero.equals(head)) {
            throw new IllegalArgumentException("Vector head can not be point(0,0,0)");
        }
        _head = head;
                //new Point3D(head._x._coord, head._y._coord, head._z._coord);
    }

    /*constructor with doubles. calls first constructor
     *"this" makes the program call the constructor of that same class*/
    public Vector(double x, double y, double z) {
        this(new Point3D(x, y, z));
    }

    /**
     * equal function
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vector)) return false;
        Vector vector = (Vector) o;
        return _head.equals(vector._head);
    }

    /*returns head of vector, which represents it.*/
    public Point3D getHead() {
        return _head;
       // return new Point3D(_head._x._coord,_head._y._coord,_head._z._coord);
    }

    /*Vector product(duplication)*/
    public Vector crossProduct(Vector v) {
        double u1 = _head._x._coord;
        double u2 = _head._y._coord;
        double u3 = _head._z._coord;

        double v1 = v._head._x._coord;
        double v2 = v._head._y._coord;
        double v3 = v._head._z._coord;

        return new Vector(
                u2 * v3 - v2 * u3,
                u3 * v1 - v3 * u1,
                u1 * v2 - v1 * u2
        );
    }

    /*Scalar product*/
    public double dotProduct(Vector v) {
        double x = _head._x._coord * v._head._x._coord;
        double y = _head._y._coord * v._head._y._coord;
        double z = _head._z._coord * v._head._z._coord;

        return x + y + z;
    }

    /**
     * substract vectors
     * @param v
     * @return new vector after the subtract
     */
    public Vector substract(Vector v) {
        double x = _head._x._coord - v._head._x._coord;
        double y = _head._y._coord - v._head._y._coord;
        double z = _head._z._coord - v._head._z._coord;

        return new Vector(x, y, z);
    }

    /**
     * calculate the length of the vector squared
     * @return double length squared
     */
    public double lengthSquared() {
        double xx = _head._x._coord * _head._x._coord;
        double yy = _head._y._coord * _head._y._coord;
        double zz = _head._z._coord * _head._z._coord;

        return xx + yy + zz;
    }

    /*the length of a vector is sqrt of lengthSquared*/
    public double length() {
        return Math.sqrt(lengthSquared());
    }

    /*normalize the vector itself
     * returns a vector in the same direction but with length 1*/
    public Vector normalize() {

        double len = this.length();
        double newX = _head._x._coord / len;
        double newY = _head._y._coord / len;
        double newZ = _head._z._coord / len;

        Point3D newPoint = new Point3D(newX, newY, newZ);

        //head vector cannot be point (0,0,0).
        if (zero.equals(newPoint)) {
            throw new IllegalArgumentException("Vector head can not be point(0,0,0)");
        }

        _head = newPoint;
        return this;
    }

    /*return a new normalized vector(does not change the original)
     * a normal of a vector is in the same direction but with length 1
     **/
    public Vector normalized() {
        Vector result = new Vector(_head);
        result.normalize();
        return result;
    }

    /*Vector multiplication by a number-scalar*/
    public Vector scale(double num) {

        double x = _head._x._coord * num;
        double y = _head._y._coord * num;
        double z = _head._z._coord * num;

        return new Vector(x, y, z);


    }

    /**
     * adds vectors
     * @param v
     * @return new vector after the adding
     */
    public Vector add(Vector v) {
        double x = _head._x._coord + v._head._x._coord;
        double y = _head._y._coord + v._head._y._coord;
        double z = _head._z._coord + v._head._z._coord;
        return new Vector(x, y, z);
    }

    /**
     * This function helps us to calculate  a normal vector to the vector that calls the function
     * @return a new vector
     */
    public Vector normalVector()
    {
        double coordinate;

        if(this.getHead().getX()>0) //finding the smallest coordinate of the vector to replace it with 0
        {
            coordinate = this.getHead().getX();
        }
        else {
            coordinate = -this.getHead().getX();
        }

        if(Math.abs(this.getHead().getY())<coordinate)
        {
            coordinate=1;
            if(this.getHead().getY()>0)
                coordinate=this.getHead().getY();
            else
                coordinate=-this.getHead().getY();
        }
        if(Math.abs(this.getHead().getZ())<coordinate)
        {
            coordinate=2;

        }
        if(coordinate==0) {//if x is the smallest
            return new Vector(0, -this.getHead().getZ(), this.getHead().getY()).normalize();
        }
        if(coordinate==1) {//if y is the smallest
            return new Vector(-this.getHead().getZ(), 0, this.getHead().getX()).normalize();
        }
        //else, z is the smallest
        return new Vector(this.getHead().getY(),-this.getHead().getX(),0).normalize();
    }
}
