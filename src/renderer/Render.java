package renderer;

import elements.Camera;
import scene.Scene;
import java.util.MissingResourceException;


public class Render {
    private ImageWriter _imageWriter;
    private Scene _scene;
    private Camera _camera;
    private RayTracerBase _rayTracerBase;

    public Render setImageWriter(ImageWriter imageWriter) {
        this._imageWriter = imageWriter;
        return this;
    }

    public Render setScene(Scene scene) {
        this._scene = scene;
        return this;
    }

    public Render setCamera(Camera camera) {
        this._camera = camera;
        return this;
    }

    public Render setRayTracerBase(RayTracerBase rayTracerBase) {
        this._rayTracerBase = rayTracerBase;
        return this;
    }

    public void renderImage() {
        if(_imageWriter==null) {
            throw new MissingResourceException("Image writer is missing", )
        }
    }
}
