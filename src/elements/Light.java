package elements;

import primitives.Color;

/**
 * abstract class for Light
 * @author Odelia & Ruth
 */
abstract class Light {
    /**
     * @param _intensity intensity of the light
     */
    protected Color _intensity; //Original refill light- IA

    /**
     * constructor
     */
    protected Light(Color intensity) {
        this._intensity = intensity;
    }

    public Color getIntensity() {
        return _intensity;
    }


}
