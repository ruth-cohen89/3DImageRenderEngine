
package geometries;
import primitives.*;
/**
 * abstract class for all the geometries that have a normal from them
 * Geometry in an extension of intersectable
 */
public abstract class Geometry implements Intersectable {
    protected Color _emission = Color.BLACK; //פליטה של אור
    private Material _material = new Material();

    /**
     *
     * @param point should be null for flat geometries
     * @return the normal vector yo the geometries
     */
    abstract public  Vector getNormal(Point3D point);

    /**
     * get for emmission color field
     * @return  emmission field
     */
    public Color getEmission() {
        return _emission;
    }

    /**
     * set the Emmission
     * @param emission
     * @return all this geometry
     */
    public Geometry setEmission(Color emission) {
        _emission = emission;
        return this;
    }

    /**
     *
     * @return get the Material
     */
    public Material getMaterial() {
        return _material;
    }

    public Geometry setMaterial(Material material) {
        _material = material;
        return this;
    }

}


