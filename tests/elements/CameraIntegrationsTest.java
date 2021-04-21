package elements;

import geometries.Sphere;
import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CameraIntegrationsTest {
    @Test
    void testSphere1withCam() {
        Sphere s1=new Sphere(new Point3D(0, 0, -3), 1);

        Camera cam=new Camera(Point3D.ZERO, new  Vector(0, 0, -1), new Vector(0, 1, 0)).setDistance(1).setViewPlaneSize(3, 3);

            List<Point3D> allPoints = null;

        public void cameraIntersections(Camera cam) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    Ray r = cam.constructRayThroughPixel(3, 3, j, i);
                    List<Point3D> lst = s1.findIntersections(r);
                    if (lst != null) {
                        if (allPoints == null) {
                            allPoints = new LinkedList<>();
                        }
                        allPoints.addAll(lst);
                    }
                }
            }
        }
        assertEquals(allPoints.size(), 2, "wrong number of intersections");
    }
}
