package elements;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.isZero;

/**
 * The Camera class
 */
public class Camera {
    private final Point3D _P0;  //The eye of the camera
    private final Vector _vTo;
    private final Vector _vUp;
    private final Vector _vRight;

    //View Plane parameters
    double _width;
    double _height;
    double _distance;  //The distance between the View Plane to the camera

    private Camera(Builder builder) {
        _P0 = builder._P0;
        _vTo = builder._vTo;
        _vUp = builder._vUp;
        _vRight = builder._vRight;
        _height = builder._height;
        _width = builder._width;
        _distance = builder._distance;
    }

    /**
     * set the length and width of ViewPlane
     * @param width  width of the ViewPlane
     * @param height height of the ViewPlane
     * @return this
     */
    public Camera setViewPlaneSize(double width, double height) {
        _width = width;
        _height = height;
        return this;
    }
    /**
     * set the distance of ViewPlane
     * @param distance The distance between the View Plane to the camera
     * @return this
     */
    public Camera setDistance(double distance) {
        _distance = distance;
        return this;
    }

    /**
     * construct a Ray through Pixel
     * @param nX     number of pixels on x axis
     * @param nY     number of pixels on y axis
     * @param j     index of the pixel on the view plane on the x axis
     * @param i     index of the pixel on the view plane on the y axis
     * @return       the Ray through Pixel
     */
    public Ray constructRayThroughPixel(int nX, int nY, int j, int i) {
        Point3D Pc= _P0.add(_vTo.scale(_distance));   //PC= P0 +d*vTo ->> Calculation from the presentation
        double Rx= _width/nX;  //Rx= w/nx ->> Calculation from the presentation
        double Ry=  _height/nY; //Ry= h/ny ->> Calculation from the presentation

        Point3D pIJ= Pc;
        double Xj= (j - (nX-1)/2d) * Rx ; //Xj= (j - (nX-1)/2d) * Rx ->> Calculation from the presentation
        double Yi= -(i- (nY-1)/2d)* Ry; //Yi= -(i- (nY-1)/2d)* Ry;  ->> Calculation from the presentation

        if(!isZero(Xj)){
            pIJ = pIJ.add(_vRight.scale(Xj)); //pIJ= pIJ + _vRight*Xj ->> Calculation from the presentation
        }
        if(!isZero(Yi)){
            pIJ = pIJ.add(_vUp.scale(Yi)); //pIJ= pIJ + _vUp*Yi ->> Calculation from the presentation
        }

        Vector Vij= pIJ.subtract(_P0); //Vij= pIJ-P0
        return new Ray( Vij,_P0);

    }

    public static class Builder {
        final private Point3D _P0;  //The eye of the camera
        final private Vector _vTo;
        final private Vector _vUp;
        final private Vector _vRight;
        //View Plane parameters
        private double  _width=1;
        private double _height=1;
        private double _distance=1;  //The distance between the View Plane to the camera

        /**
         * set the length and width of ViewPlane
         * @param width  width of the ViewPlane
         * @return this
         */
        public Builder setWidth(double width) {
            _width = width;
            return this;
        }
        /**
         * set the length and width of ViewPlane
         * @param height height of the ViewPlane
         * @return this
         */
        public Builder setHeight(double height) {
            _height = height;
            return this;
        }
        /**
         * set the distance of ViewPlane
         * @param distance The distance between the View Plane to the camera
         * @return this
         */
        public Builder setDistance(double distance) {
            _distance = distance;
            return this;
        }

        public Camera build(){
            Camera camera= new Camera(this);
            return camera;
        }

        public Builder(Point3D p0, Vector vTo, Vector vUp) {
            _P0 = p0;
            _vTo = vTo.normalized();
            _vUp = vUp.normalized();
            if (!isZero(_vTo.dotProduct(_vUp))) {
                throw new IllegalArgumentException("vTo and vUp are not orthogonal");
            }
            _vRight = _vTo.crossProduct(_vUp); //This vector has been normalized
        }
    }
}



