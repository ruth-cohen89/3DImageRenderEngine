package primitives;

import java.util.Objects;

public class  Ray {
    final Point3D _p0; //point..
    final Vector _dir; //direction vector.

    public Ray(Point3D p0, Vector dir) { //constructor.
        _p0 = p0;
        _dir = dir;
    }

    public primitives.Point3D getP0() {
        return _p0;
    } //return the value of the point.

    public primitives.Vector getDir() {
        return _dir;
    } //return the value of the vector.

    @Override
    public boolean equals(Object o) { //compare between two objects.
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ray ray = (Ray) o;
        return Objects.equals(_p0, ray._p0) && Objects.equals(_dir, ray._dir);
    }

    @Override
    public String toString() { //return the values of the ray.
        return "Ray:" +
                "p0=" + _p0 +
                ", dir=" + _dir;
    }
}
