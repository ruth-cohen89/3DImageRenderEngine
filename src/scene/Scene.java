package scene;

import elements.*;
import geometries.*;
import primitives.*;

//**Scene of geometries
public class Scene {
    public String _name;        //Name of the scene
    public Color _background=Color.BLACK;  //background color(default-black)
    public Geometries _geometries = new Geometries(); //array of geometries(default-empty)
    public AmbientLight _ambientLight=new AmbientLight(Color.BLACK, 0);

    //*constructor
    // Sets name of scene and builds empty collection of geometries*//
    public Scene(String n1) {
        this._name=n1;
        _geometries = new Geometries();
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
