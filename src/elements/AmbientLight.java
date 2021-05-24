package elements;

import primitives.*;

/**
 * class for Ambient Lighting
 * @author Odelia & Ruth
 */
public class AmbientLight extends Light{
    /**
     * @param _KA Discount factor
     */
    double _KA;

    /**
     * constructor
     * @param iA Original light intensity
     * @param KA Discount factor
     * The constructor calculate the final light intensity
     */
    public AmbientLight(Color iA, double KA) {
        super(iA.scale(KA));
    }

    /**
     * default constructor
     */
    public AmbientLight() {
        super(Color.BLACK);
    }

}
