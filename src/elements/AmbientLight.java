package elements;

import primitives.*;

/**
 * Class that defines the Ambient Light
 */
public class AmbientLight {
    Color _intensity;    //Original refill light- IA
    double _KA;          //Filling light attenuation coefficient KA

    /*The constructor returns the final color intensity
     (multiply the intensity in light attenuation)*/
    public AmbientLight(Color intensity, double KA) {
        _intensity = intensity.scale(KA);
    }

    public Color getIntensity() {
        return _intensity;
    }
}
