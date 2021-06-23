package scene;

import elements.*;
import geometries.*;
import primitives.*;

import java.util.LinkedList;
import java.util.List;

//**Scene of geometries
public class Scene {
    private final String _name;        //Name of the scene
    public Color background = Color.BLACK;  //background color(default-black)
    public Geometries geometries; //array of geometries(default-empty)
    public AmbientLight ambientLight=new AmbientLight();
    public List<LightSource> lights=new LinkedList<>();

    //*constructor
    // Sets name of scene and builds empty collection of geometries*/
    // /
    public Scene(String n1) {
        this._name=n1;
        geometries = new Geometries();
    }

    public Scene setLights(List<LightSource> lights) {
        this.lights = lights;
        return this;
    }

    public Scene setBackground(Color background) { //setter for the background color
        this.background = background;
        return this;
    }

    public Scene setGeometries(Geometries geometries) { //setter for the geometry 3D model
        this.geometries = geometries;
        return this;
    }

    public Scene setAmbientLight(AmbientLight ambientLight) { //setter for the ambient light
        this.ambientLight = ambientLight;
        return this;
    }

    public String getName() {
        return _name;
    }
}
