package primitives;


/**
 * class for Material
 * @author Odelia & Ruth
 */
public class Material {
    public double _kD=0;
    public double _kS=0;
    public int _nShininess=0;

    /**
     * @param kD factor of material
     * @return
     */
    public Material setkD(double kD) {
        _kD = kD;
        return this;
    }

    /**
     * @param kS factor of material
     * @return
     */
    public Material setkS(double kS) {
        _kS = kS;
        return this;
    }

    /**
     * @param nShininess shininess of material
     * @return
     */
    public Material setnShininess(int nShininess) {
        _nShininess = nShininess;
        return this;
    }
}
