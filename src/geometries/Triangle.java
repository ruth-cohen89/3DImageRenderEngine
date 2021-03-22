package geometries;

import primitives.Point3D;

/*represents Triangle by using polygon and implementing geometry.*/
public class Triangle extends Polygon implements Geometry{

    public Triangle(Point3D p1, Point3D p2, Point3D p3){
        super(p1,p2,p3);
    }

    @Override
    public String toString() {
        return "Triangle{" +
                "vertices=" + vertices +
                ", plane=" + plane +
                '}';
    }
}
