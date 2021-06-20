package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * represents point light with direction
*has a center, its power also decreases
 */
public class SpotLight extends PointLight{

    private final Vector _direction;

    /**
     * constructor for SpotLight
     * @param intensity this is a I0
     * @param position
     * @param direction of DirectionalLight
     */
    public SpotLight(Color intensity, Point3D position, Vector direction) {
        super(intensity, position);
        _direction = direction.normalized();
    }

    /**
     * @param p Point3D
     * @return  the intensity of light source in point p
     */
    @Override
    public Color getIntensity(Point3D p) {
        //Further doubling should be added
    /*    double cosTetha = _direction.dotProduct(getL(p)); //direction*l
        Color intensity = super.getIntensity(p); //intensity from PointLight
        return intensity.scale(Math.max(0, cosTetha));    //intensity * factor
*/
        return super.getIntensity(p).scale(Math.max(0, _direction.dotProduct(getL(p))));
    }
}
