package primitives;


import static primitives.Point3D.zero;

//**Vector - A foundational object in geometry with direction and size,
// defined by the end point (when the starting point - the beginning of the axes).
//**//

//empty constructor
public class Vector {
    Point3D _head;

    /*constructor with head*/
    public Vector(Point3D head) {
        if (zero.equals(head)) {
            throw new IllegalArgumentException("Vector head can not be point(0,0,0)");
        }
        _head = new Point3D(head._x._coord, head._y._coord, head._z._coord);
    }

    /*constructor with doubles. calls first constructor
    *"this" makes the program call the constructor of that same class*/
    public Vector(double x, double y, double z) {
        this(new Point3D(x, y, z));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vector)) return false;
        Vector vector = (Vector) o;
        return _head.equals(vector._head);
    }

    /*returns head of vector, which represents it.*/
    public Point3D getHead() {
        //return head
        return new Point3D(_head._x._coord,_head._y._coord,_head._z._coord);
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

        double len = length();
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
}
