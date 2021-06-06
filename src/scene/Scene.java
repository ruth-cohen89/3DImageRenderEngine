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
    public Geometries geometries = new Geometries(); //array of geometries(default-empty)
    public AmbientLight ambientLight=new AmbientLight(Color.BLACK, 0);
    public List<LightSource> lights;

    //*constructor
    // Sets name of scene and builds empty collection of geometries*/
    // /
    public Scene(String n1) {
        this._name=n1;
        geometries = new Geometries();
        lights=new LinkedList<LightSource>();
    }

    public Scene setLights(List<LightSource> lights) {
        lights = lights;
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
}
