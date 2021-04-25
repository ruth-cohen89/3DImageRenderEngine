package scene;

import elements.*;
import geometries.*;
import primitives.*;

public class Scene {
    String _name;        //Name of the scene
    Color _background;  //background color
    final Geometries _geometries = new Geometries();
    AmbientLight _ambientLight;
}
