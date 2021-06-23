package renderer;

import elements.Camera;
import primitives.Color;
import primitives.Ray;
import scene.Scene;
import java.util.MissingResourceException;

/**
 * class for Render
 * @author Odelia & Ruth
 */
//Creates the picture colors matrix from scene
public class Render {
    private ImageWriter _imageWriter;
    private Camera _camera;
    private RayTracerBase _rayTracerBase;

    public Render setImageWriter(ImageWriter imageWriter) {
        this._imageWriter = imageWriter;
        return this;
    }


    public Render setCamera(Camera camera) {
        this._camera = camera;
        return this;
    }

    public Render setRayTracer(RayTracerBase rayTracerBase) {
        this._rayTracerBase = rayTracerBase;
        return this;
    }

    //*renderImage demonstrates an image
    // It builds a ray for each pixel and creates a color for each ray
    //every color that is created fits to the right pixel in the picture
    // *
    public void renderImage() {

        //*checks if null values were inserted, throws an exception in such case*//
        if(_imageWriter==null) {
            throw new MissingResourceException("Image writer is missing", ImageWriter.class.getName(),"");
        }
        if (_camera == null ){
            throw new MissingResourceException("Camera is missing", Camera.class.getName(), "");
        }

        //   if (_scene == null ){
        //       throw new MissingResourceException("Scene is missing", Scene.class.getName(), "");
        // }

        if (_rayTracerBase == null ){
            throw new MissingResourceException("Ray tracer is missing", RayTracerBase.class.getName(), "");
        }

        int nX = _imageWriter.getNx();
        int nY = _imageWriter.getNy();
        for (int i = 0; i < nY; i++) {// go through all pixels of view plane
            for (int j = 0; j < nX; j++) {
                Ray ray = _camera.constructRayThroughPixel(nX, nY, j, i);//builds a ray for each (x,y) pixel
                Color color = _rayTracerBase.traceRay(ray);//builds a color for every ray
                _imageWriter.writePixel(j, i, color);// Color the pixel in picture, (j,i)
            }
        }

    }

    /**
     * Drawing a grid of pixels
     * @param interval size of pixel
     * @param color of grid
     */
    public void printGrid(int interval, Color color) {
        if (_imageWriter != null) {
            int nX = _imageWriter.getNx();
            int nY = _imageWriter.getNy();
            for (int i = 0; i < nY; i++) {// go through all pixels
                for (int j = 0; j < nX; j++) {
                    if (i % interval == 0 || j % interval == 0) { //giving color to lines only
                        _imageWriter.writePixel(j, i, color);
                    }
                }
            }
        }
        else {
            throw new MissingResourceException("The image writer is missing", ImageWriter.class.getName(), "");
        }

    }

    /**
     * Delegate writeToImage
     */
    public void writeToImage() {
        if (_imageWriter!=null){
            _imageWriter.writeToImage();
        }
        else{
            throw new MissingResourceException("", "", "");
        }
    }
}

