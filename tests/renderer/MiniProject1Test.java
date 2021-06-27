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
            .setViewPlaneSize(800, 600).setDistance(1000).build();

    scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));
    //GEOMETRIES
    scene.geometries.add(
            new Polygon(new Point3D(500, 350, 100), new Point3D(520, 350, 100),   new Point3D(750, -400, 100),new Point3D(730, -400, 100))
            .setEmission(new Color(java.awt.Color.BLUE))
            .setMaterial(new Material().setKs(0.5).setKt(0.5)),

            new Polygon(new Point3D(570, 350, 100), new Point3D(590, 350, 100),   new Point3D(520, -400, 100),new Point3D(500, -400, 100))
                    .setEmission(new Color(java.awt.Color.BLUE))
                    .setMaterial(new Material().setKs(0.5).setKt(0.5)),

            new Polygon(new Point3D(300, 255, 50), new Point3D(320, 255, 50),   new Point3D(320, -400, 50),new Point3D(300, -400, 50))
                    .setEmission(new Color(java.awt.Color.BLUE))
                    .setMaterial(new Material().setKs(0.5).setKt(0.5)),

            new Polygon(new Point3D(170, 246, 100), new Point3D(580, 246, 250),   new Point3D(580, 260, 250),new Point3D(170, 260, 100))
                    .setEmission(new Color(java.awt.Color.BLUE))
                    .setMaterial(new Material().setKs(0.5).setKt(0.5)),

            new Polygon(new Point3D(180, 245, 50), new Point3D(200, 245, 50),   new Point3D(200, -400, 50),new Point3D(180, -400, 50))
                    .setEmission(new Color(java.awt.Color.BLUE))
                    .setMaterial(new Material().setKs(0.5).setKt(0.5)),

            new Polygon(new Point3D(370, 260, 50), new Point3D(375, 260, 50),   new Point3D(375, -195, 50),new Point3D(370, -195, 50))
                    .setEmission(new Color(java.awt.Color.darkGray))
                    .setMaterial(new Material().setKs(0.5).setKt(0.5)),

            new Polygon(new Point3D(470, 265, 50), new Point3D(475, 265, 50),   new Point3D(475, -200, 50),new Point3D(470, -200, 50))
                    .setEmission(new Color(java.awt.Color.darkGray))
                    .setMaterial(new Material().setKs(0.5).setKt(0.5)),

            new Polygon(new Point3D(460, -190, 150), new Point3D(370, -190, 10),   new Point3D(370, -210, 10),new Point3D(460, -210, 150))
                    .setEmission(new Color(java.awt.Color.darkGray))
                    .setMaterial(new Material().setKs(0.5).setKt(0.5))
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