package renderer;

import static org.junit.jupiter.api.Assertions.*;

import elements.AmbientLight;
import elements.Camera;
import elements.SpotLight;
import geometries.Cylinder;
import geometries.Polygon;
import geometries.Sphere;
import geometries.Tube;
import org.junit.jupiter.api.Test;
import primitives.*;
import scene.Scene;

class MiniProject1Test {
    private Scene scene = new Scene("Test scene");
@Test
    public void kinderGarten()
{
    Camera camera=new Camera.Builder(new Point3D(0, 0, 2000), new Vector(0, 0, -1), new Vector(0, 1, 0))
            .setViewPlaneSize(600, 600).setDistance(1000).build();

    scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));
    //GEOMETRIES
    scene.geometries.add(
    //         new Tube(new Ray(new Vector(0.2,0.5,0),new Point3D(110, 10, 0)), 10)
      //      .setEmission(new Color(java.awt.Color.DARK_GRAY))
        //    .setMaterial(new Material().setKs(0.5).setKd(0.5)),

//            new Tube(new Ray(new Vector(0.2,-0.5,0.6),new Point3D(90, 10, 0)), 10)
//                    .setEmission(new Color(java.awt.Color.DARK_GRAY))
//                    .setMaterial(new Material().setKs(0.5).setKd(0.5)),

            new Cylinder(new Ray(new Vector(0.3,1,0),new Point3D(110, 10, 0)), 10, 5)
                   .setEmission(new Color(java.awt.Color.DARK_GRAY))
                    .setMaterial(new Material().setKs(0.5).setKd(0.5))
           // new Sphere(35, new Point3D(100, 0, 10))
             //       .setEmission(new Color(java.awt.Color.YELLOW))
               //     .setMaterial(new Material().setKd(0.15).setKt(0.4).setKs(0.15).setKr(0.6).setShininess(20))
    );
    //LIGHTS
    scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point3D(60, 50, 0), new Vector(0, 0, -1))
            .setKl(4E-5).setKq(2E-7));

    ImageWriter imageWriter = new ImageWriter("MiniProject1 Photo", 600, 600);

    Render render = new Render() //
            .setImageWriter(imageWriter) //
            .setCamera(camera) //
            .setRayTracer(new BasicRayTracer(scene));
    //System.out.print( scene.getName());
    render.renderImage();
    render.writeToImage();
}

}