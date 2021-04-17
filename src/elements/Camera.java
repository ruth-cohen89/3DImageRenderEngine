package elements;

import primitives.Point3D;
import primitives.Vector;

import static primitives.Util.isZero;

public class Camera {
    final Point3D _p0;
    final Vector _vTo;
    final Vector _vUp;
    final Vector _vRight;

    //view plane parameters
    private double _height;
    private double _width;
    private double _distance;

    private Camera(Builder builder) {
        _p0=builder._p0;
        _vTo=builder._vTo;
        _vUp=builder._vUp;
        _vRight=builder._vRight;
        _height=builder._height;
        _width=builder._width;
        _distance=builder._distance;
    }

    public static class Builder {
        final Point3D _p0;
        final Vector _vTo;
        final Vector _vUp;
        final Vector _vRight;
        private double _height;
        private double _width;
        private double _distance;

    }

    private Builder(Point3D p0, Vector vTo, Vector vUp) {
        _p0 = p0;
        _vTo = vTo.normalized();
        _vUp = vUp.normalized();

        //if their dot-product is not 0, then they r not orthogonal
        if (!isZero(_vTo.dotProduct(_vUp))) {
            throw new IllegalArgumentException("vTo and vUp are not orthogonal");
        }

        //else
        _vRight = _vTo.crossProduct(_vUp);

    }

    public Builder setHeight(double height) {
        _height = height;
        return this;
    }

    public Builder setWidth(double width) {
        _width = width;
        return this;
    }

    public Builder setDistance(double distance) {
        _distance = distance;
        return this;
    }
}

