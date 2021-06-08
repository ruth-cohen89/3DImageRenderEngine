package primitives;


/**
 * class for Material
 * @author Odelia & Ruth
 */
public class Material {
    public double _kD=0.0;
    public double _kS=0.0;
    public double _kT=0.0;//transparency coefficient
    public double _kR=0.0;//reflection coefficient

    public int _Shininess =0;


    /**
     * @param kD factor of material
     * @return object
     */
    public Material setKd(double kD) {
        _kD = kD;
        return this;
    }

    /**
     * @param kS factor of material
     * @return object
     */
    public Material setKs(double kS) {
        _kS = kS;
        return this;
    }

    /**
     * transparency coefficient
     * @param kT factor of material
     * @return object
     */
    public Material setKt(double kT) {
        _kT = kT;
        return this;
    }

    /**
     * reflection coefficient
     * @param kR factor of material
     * @return object
     */
    public Material setKr(double kR) {
        _kR = kR;
        return this;
    }


    /**
     * @param nShininess shininess of material
     * @return object
     */
    public Material setShininess(int nShininess) {
        _Shininess = nShininess;
        return this;
    }
}
