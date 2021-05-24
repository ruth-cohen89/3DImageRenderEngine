package geometries;

import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;

/**
 * abstract class for all the geometries that have a normal from them
 */
public abstract class Geometry implements Intersectable{

    private Material _material;

    public Material getMaterial() {
        return _material;
    }

    public Geometry setMaterial(Material material) {
        _material = material;
        return this;
    }

    /**
     *
     * @param point should be null for flat geometries
     * @return the normal to the geometry
     */
    abstract public Vector getNormal(Point3D point);
    protected Color  emission=Color.BLACK;

    public Color getEmission() {
        return emission;
    }

    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return this;
    }


}
