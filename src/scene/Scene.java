package scene;

import elements.*;
import geometries.*;
import primitives.*;

import java.util.LinkedList;
import java.util.List;

//**Scene of geometries
public class Scene {
    public String _name;        //Name of the scene
    public Color _background = Color.BLACK;  //background color(default-black)
    public Geometries _geometries = new Geometries(); //array of geometries(default-empty)
    public AmbientLight _ambientLight=new AmbientLight(Color.BLACK, 0);
    public List<LightSource> _lights;

    //*constructor
    // Sets name of scene and builds empty collection of geometries*//
    public Scene(String n1) {
        this._name=n1;
        _geometries = new Geometries();
        _lights=new LinkedList<LightSource>();
    }

    public Scene setLights(List<LightSource> lights) {
        _lights = lights;
        return this;
    }

    public Scene setBackground(Color background) { //setter for the background color
        this._background = background;
        return this;
    }

    public Scene setGeometries(Geometries geometries) { //setter for the geometry 3D model
        this._geometries = geometries;
        return this;
    }

    public Scene setAmbientLight(AmbientLight ambientLight) { //setter for the ambient light
        this._ambientLight = ambientLight;
        return this;
    }
}
