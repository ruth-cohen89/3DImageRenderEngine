package primitives;


public class Point3D {
    final Coordinate _x;
    final Coordinate _y;
    final Coordinate _z;

    /*defining point zero*/
    public final static Point3D zero = new Point3D(0d, 0d, 0d);

    /**
     * primary constructor for 3D point
     *
     * @param x value for x 3D point
     * @param y value for x 3D point
     * @param z value for x 3D point
     */

    /*defining a point with 3 coordinates*/
    public Point3D(Coordinate x, Coordinate y, Coordinate z) {
        _x = x;
        _y = y;
        _z = z;
    }

    /*defining a point with 3 double,(the number becomes a coordinate)*/
    public Point3D(double x, double y, double z) {
        // this(new Coordinate(x), new Coordinate(y), new Coordinate(z));
        _x = new Coordinate(x);
        _y = new Coordinate(y);
        _z = new Coordinate(z);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point3D point3D = (Point3D) o;
        return _x.equals(point3D._x) && _y.equals(point3D._y) && _z.equals(point3D._z);
    }

    @Override
    public String toString() {
        return "Point3D{" +
                "_x=" + _x +
                ", _y=" + _y +
                ", _z=" + _z +
                '}';
    }

    /*subtraction bet 2 points (2 heads that rep 2 vectors), returning the result as a head point*/
    public Vector subtract(Point3D pt2) {
        Point3D head= new Point3D(
                _x._coord - pt2._x._coord,
                _y._coord - pt2._y._coord,
                _z._coord - pt2._z._coord
        );
        if(zero.equals(head)) {
            throw new IllegalArgumentException("Vector head can not be point(0,0,0)");//(because the tail is already 0...)
        }
        return new Vector(head);

    /*addition bet 2 points (2 heads that rep 2 vectors), returning the result as a vector(head point)*/
    }
    public Point3D add(Vector v) {
        double x= _x._coord + v._head._x._coord;
        double y= _y._coord + v._head._y._coord;
        double z= _z._coord + v._head._z._coord;
        return new Point3D(x,y,z);
    }

    /*returns distanceSquared between 2 vectors */
    public double distanceSquared(Point3D p) {
        double xx= (_x._coord - p._x._coord) * (_x._coord- p._x._coord);
        double yy= (_y._coord- p._y._coord) * (_y._coord- p._y._coord);
        double zz= (_z._coord- p._z._coord) * (_z._coord- p._z._coord);

        return xx+ yy+ zz;
    }

/*the distance between 2 vectors is sqrt of distanceSquared*/
    public double distance(Point3D other) {
        return Math.sqrt(distanceSquared(other));
    }
}
