package elements;

import primitives.*;
/**
 * abstract Light Class - all light sources in scene extend light
 */
abstract class Light  {
    /**
     * color of light intensity
     */
    protected final Color _intensity;   //Original light parameter- IA

    /**
     * constructor for Light
     * @param intensity
     */
    public Light(Color intensity) {
        _intensity = intensity;
    }
    /**
     * get for Light
     * @return _intensity
     */
    public Color getIntensity() {
        return _intensity;
    }

}
