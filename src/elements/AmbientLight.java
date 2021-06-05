package elements;

import primitives.*;

/**
 * Class that defines the Ambient Light
 */
public class AmbientLight extends Light {

    /**
     * default constructor
     * transfers the color black to light's constructor
     */
    public AmbientLight(){
        super(Color.BLACK);
    }

    /**
     * constructor for the environmental light , return final power of ambient lighting
     * @param intensity Original light parameter- IA
     * @param KA the light intensity damping parameter
     */
    public AmbientLight(Color intensity, double KA) {
        super(intensity.scale(KA));  //The ultimate power of fill lighting
    }
}
