package primitives;

import java.util.Objects;

//**ray- straight that extends to infinity in only one direction**//
public class  Ray {
    final Point3D _pOrigin; //point..
    final Vector _direction; //direction vector.

    public Ray(Point3D p0, Vector direction) { //constructor.
        _pOrigin = p0;
        _direction = direction.normalize();
    }

    /**
     * getter for origin of the ray
     * @return p0
     */
    public primitives.Point3D getP0() {
        return _pOrigin;
    } //return the value of the point.

    /**
     * getter for direction vector of the ray
     * @return _dir
     */
    public primitives.Vector getDirection() {
        return _direction;
    } //return the value of the vector.

    @Override
    public boolean equals(Object o) { //compare between two objects.
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ray ray = (Ray) o;
        return Objects.equals(_pOrigin, ray._pOrigin) && Objects.equals(_direction, ray._direction);
    }

    @Override
    public String toString() { //return the values of the ray.
        return "Ray:" +
                "p0=" + _pOrigin +
                ", dir=" + _direction;
    }

    public Point3D getPoint(double t) {
        return _pOrigin.add(_direction.scale(t));
    }
}
