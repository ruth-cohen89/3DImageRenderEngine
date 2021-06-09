
package elements;

import geometries.*;
import org.junit.jupiter.api.Test;
import primitives.*;

import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * integration tests Class
 *
 * @author Ruth & Odelia
 */
/*integration tests of creating rays and finding intersections between them and geometries*/
class CameraIntegrationsTest {

    //*creating 2 cameras *//
    Camera camera0 = new Camera.Builder(Point3D.zero, new Vector(0, 0, 1),
            new Vector(0, -1, 0)).
            setDistance(1)
            .setWidth(3)
            .setHeight(3)
            .build();

    Camera camera1 = new Camera.Builder(new Point3D(0, 0, -0.5),
            new Vector(0, 0, 1),
            new Vector(0, -1, 0))
            .setDistance(1)
            .setWidth(3)
            .setHeight(3)
            .build();

    /**
     * A private help function to prevent repeating the test calculation code for sphere
     *
     * @param Nx       width - Number of pixels on x axis
     * @param Ny       height - Number of pixels on y axis
     * @param sphere   The Sphere
     * @param expected What assertEquals expects to receive
     * @param message  Printed if the return value is not as expected
     * @param cam      The camera
     */
    public void sphereWithCam(int Nx, int Ny, Sphere sphere, int expected, String message, Camera cam) {
        List<Point3D> results;
        int count = 0;
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                results = sphere.findIntersections(cam.constructRayThroughPixel(Nx, Ny, j, i));
                if (results != null)
                    count += results.size();
            }
        }
        assertEquals(expected, count, "The result is not as expected");
    }

    /**
     * A private help function to prevent repeating the test calculation code for plane
     *
     * @param Nx       number of pixels on x axis
     * @param Ny       number of pixels on y axis
     * @param plane    The Plane
     * @param expected What assertEquals expects to receive
     * @param message  Printed if the return value is not as expected
     * @param cam      The camera
     */

    public void planeWithCam(int Nx, int Ny, Plane plane, int expected, String message, Camera cam) {
        List<Point3D> results;
        int count = 0;
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                results = plane.findIntersections(cam.constructRayThroughPixel(Nx, Ny, j, i));
                if (results != null)
                    count += results.size();
            }
        }
        assertEquals(expected, count, "The result is not as expected");
    }

    /**
     * A private help function to prevent repeating the test calculation code for triangle
     *
     * @param triangle The Triangle
     * @param expected What assertEquals expects to receive
     * @param message  If not return what we expected to print the this message
     * @param cam      The camera
     */

    public void triangleWithCam(Triangle triangle, int expected, String message, Camera cam) {
        cam.setViewPlaneSize(3, 3).setDistance(1);
        int count = 0;
        for (int i = 0; i < 3; ++i)
            for (int j = 0; j < 3; ++j) {
                List<Point3D> results = triangle.findIntersections(cam.constructRayThroughPixel(3, 3, j, i));
                if (results != null)
                    count += results.size();
            }
        assertEquals(expected, count, "The result is not as expected");
    }

    /*********************************
     * test intersections Ray/Sphere *
     ********************************/

    //Sphere first test
    @Test
    void constructRayThroughPixelWithSphere1() {
        Sphere sph = new Sphere(new Point3D(0, 0, 3), 1);
        sphereWithCam(3, 3, sph, 2, "The result is not as expected", camera0);
    }

    //Sphere second test
    @Test
    void constructRayThroughPixelWithSphere2() {
        Sphere sph = new Sphere(new Point3D(0, 0, 2.5), 2.5);
        sphereWithCam(3, 3, sph, 18, "The result is not as expected", camera1);
    }

    //Sphere third test
    @Test
    void constructRayThroughPixelWithSphere3() {
        Sphere sph = new Sphere(new Point3D(0, 0, 2), 2);
        sphereWithCam(3, 3, sph, 10, "The result is not as expected", camera1);

    }

    //Sphere fourth test
    @Test
    void constructRayThroughPixelWithSphere4() {
        Sphere sph = new Sphere(new Point3D(0, 0, 1), 4);
        sphereWithCam(3, 3, sph, 9, "The result is not as expected", camera1);
    }

    //Sphere fifth test
    @Test
    void constructRayThroughPixelWithSphere5() {
        Sphere sph = new Sphere(new Point3D(0, 0, -1), 0.5);
        sphereWithCam(3, 3, sph, 0, "The result is not as expected", camera1);
    }

    /*********************************
     * test intersections Ray/Plane *
     ********************************/

    //Plane first test
    @Test
    void constructRayThroughPixelWithPlane1() {
        geometries.Plane plane = new Plane(new Point3D(0, 0, 3), new Vector(0, 0, 1));
        planeWithCam(3, 3, plane, 9, "The result is not as expected", camera0);
    }

    //Plane second test
    @Test
    void constructRayThroughPixelWithPlane2() {
        Plane plane = new Plane(new Point3D(0, 0, 2), new Vector(0, -0.5, 1));
        planeWithCam(3, 3, plane, 9, "The result is not as expected", camera0);
    }

    //Plane third test
    @Test
    void constructRayThroughPixelWithPlane3() {
        Plane plane = new Plane(new Point3D(0, 0, 2), new Vector(0, -1, 1));
        planeWithCam(3, 3, plane, 6, "The result is not as expected", camera0);
    }

    /***********************************
     * test intersections Ray/Triangle *
     ***********************************/

    //Triangle first test
    @Test
    void constructRayThroughPixelWithTriangle1() {
        Triangle triangle = new Triangle(new Point3D(0, -1, 2),
                new Point3D(1, 1, 2),
                new Point3D(-1, 1, 2));
        triangleWithCam(triangle, 1, "The result is not as expected", camera0);
    }

    //Triangle second test
    @Test
    void constructRayThroughPixelWithTriangle2() {
        Triangle triangle = new Triangle(new Point3D(0, -20, 2),
                new Point3D(1, 1, 2),
                new Point3D(-1, 1, 2));
        triangleWithCam(triangle, 2, "The result is not as expected", camera0);
    }
}


