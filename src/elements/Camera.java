package elements;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.isZero;

public class Camera {
    private final Point3D _p0;
    private final Vector _vTo;
    private final Vector _vUp;
    private final Vector _vRight;

    //view plane parameters
    private double _height;
    private double _width;
    private double _distance;

    public Camera(Point3D p0, Vector vTo, Vector vUp) {
        _p0=p0;
        _vTo=vTo.normalized();
        _vUp=vUp.normalized();
        _vRight=_vTo.crossProduct(_vUp);
    }

    /**return the value of the point*/
    public Point3D getP0() {
        return _p0;
    }

    /**return the value of the vector who goes up*/
    public Vector getvUp() {
        return _vUp;
    }

    /**return the value of the vector who goes forward*/
    public Vector getvTo() {
        return _vTo;
    }

    /**return the value of the vector who goes right*/
    public Vector getvRight() {
        return _vRight;
    }

    /**setter for the height and width
     * return the camera*/
    public Camera setViewPlaneSize(double width, double height) {
        _height=height;
        _width=width;
        return this;
    }

    /**set the distance for the view plane*/
    public Camera setDistance(double distance) {
        _distance=distance;
        return this;
    }

    /**intersections of ray with pixels im the view plane*/
    public Ray constructRayThroughPixel(int nX, int nY, int j, int i) {
        Point3D Pc=_p0.add(_vTo.scale(_distance));

        double Rx=_width/nX;
        double Ry=_height/nY;

        Point3D pIJ=Pc;

        double xj=(j-((nX-1)/2d))*Rx;
        double yi=-(i-((nY-1)/2d))*Ry;
        if(!isZero(xj)) {
            pIJ=pIJ.add(_vRight.scale(xj));
        }
        if(!isZero(yi)) {
            pIJ=pIJ.add(_vUp.scale(yi));
        }
        Vector Vij=pIJ.subtract(_p0);
        return new Ray(_p0, Vij);
    }
    public static class Builder {

        /**constructor*/
        public Builder(Point3D p0, Vector vTo, Vector vUp) {
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

        final Point3D _p0;
        final Vector _vTo;
        final Vector _vUp;
        final Vector _vRight;
        private double _height=1;
        private double _width=1;
        private double _distance=1;

    }

}

