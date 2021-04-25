package elements;

import primitives.*;

/**
 * Class that defines the Ambient Light
 */
public class AmbientLight {
    Color _intensity;    //Original refill light- IA
    double _KA;          //Filling light attenuation coefficient KA

    public AmbientLight(Color intensity, double KA) {
        //TODO
    }
    public Color getIntensity() {
        return _intensity;
    }
}
