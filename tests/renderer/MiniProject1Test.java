
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

                //diagonal line 1 of swing
                new Polygon(new Point3D(500, 315, 100), new Point3D(520, 315, 100),   new Point3D(750, -400, 100),new Point3D(730, -400, 100))
                        .setEmission(new Color(java.awt.Color.BLUE))
                        .setMaterial(new Material().setKs(0.5).setKt(0.5)),

                //diagonal line 2 of swing
                new Polygon(new Point3D(570, 315, 100), new Point3D(590, 315, 100),   new Point3D(520, -400, 100),new Point3D(500, -400, 100))
                        .setEmission(new Color(java.awt.Color.BLUE))
                        .setMaterial(new Material().setKs(0.5).setKt(0.5)),

                //straight line of swing
                new Polygon(new Point3D(280, 245, 50), new Point3D(300, 245, 50),   new Point3D(300, -400, 50),new Point3D(280, -400, 50))
                        .setEmission(new Color(java.awt.Color.BLUE))
                        .setMaterial(new Material().setKs(0.5).setKt(0.5)),

                //Horizontal line of swing
                new Polygon(new Point3D(170, 208, 100), new Point3D(580, 208, 250),   new Point3D(580, 235, 250),new Point3D(170, 235, 100))
                        .setEmission(new Color(java.awt.Color.BLUE))
                        .setMaterial(new Material().setKs(0.5).setKt(0.5)),


                //straight line of bridge
                new Polygon(new Point3D(180, 240, 20), new Point3D(200, 240, 20),   new Point3D(200, -400, 20),new Point3D(180, -400, 20))
                        .setEmission(new Color(java.awt.Color.BLUE))
                        .setMaterial(new Material().setKs(0.5).setKt(0.5)),


                //chain of swing
                new Polygon(new Point3D(370, 240, 50), new Point3D(375, 240, 50),   new Point3D(375, -195, 50),new Point3D(370, -195, 50))
                        .setEmission(new Color(java.awt.Color.darkGray))
                        .setMaterial(new Material().setKs(0.5).setKt(0.5)),

                //chain of swing
                new Polygon(new Point3D(470, 245, 50), new Point3D(475, 245, 50),   new Point3D(475, -200, 50),new Point3D(470, -200, 50))
                        .setEmission(new Color(java.awt.Color.darkGray))
                        .setMaterial(new Material().setKs(0.5).setKt(0.5)),

                //swing
                new Polygon(new Point3D(460, -190, 150), new Point3D(364, -190, 10),   new Point3D(364, -210, 10),new Point3D(460, -210, 150))
                        .setEmission(new Color(java.awt.Color.YELLOW))
                        .setMaterial(new Material().setKs(0.5).setKt(0.5)),

                //most left straight line of bridge
                new Polygon(new Point3D(30, 85, 50), new Point3D(50, 85, 50),   new Point3D(50, -400, 50),new Point3D(30, -400, 50))
                        .setEmission(new Color(java.awt.Color.BLUE))
                        .setMaterial(new Material().setKs(0.5).setKt(0.5)),

                //surface 1 (2,1,4,3)
                new Polygon(new Point3D(128, -49, 150), new Point3D(145, -26, 1000),  new Point3D(145, -31, 1000),new Point3D(128, -57, 150))
                        .setEmission(new Color(java.awt.Color.GRAY))
                        .setMaterial(new Material().setKs(0.5).setKt(0.5)),

                //surface 2 (2,1,4,3)
                new Polygon(new Point3D(42, -49, 150), new Point3D(58, -26, 1000),  new Point3D(58, -31, 1000),new Point3D(42, -62, 150))
                        .setEmission(new Color(java.awt.Color.GRAY))
                        .setMaterial(new Material().setKs(0.5).setKt(0.5)),

                //middle straight line of bridge
                new Polygon (new Point3D(115, 100, 50), new Point3D(135, 100, 50),   new Point3D(135, -400, 50),new Point3D(115, -400, 50))
                        .setEmission(new Color(java.awt.Color.BLUE))
                        .setMaterial(new Material().setKs(0.5).setKt(0.5)),



                //yellow rectangle of bridge
                new Polygon(new Point3D(40, 80, 200), new Point3D(112, 80, 100),   new Point3D(112, -55, 100),new Point3D(40, -55, 200))
                        .setEmission(new Color(java.awt.Color.YELLOW))
                        .setMaterial(new Material().setKs(0.5).setKt(0.5)),

                //red rectangle of bridge
                new Polygon(new Point3D(120, 91, 250), new Point3D(277, 91, 100),  new Point3D(277, -50, 100),new Point3D(120, -50, 250))
                        .setEmission(new Color(java.awt.Color.RED))
                        .setMaterial(new Material().setKs(0.5).setKt(0.5)),

                //level of bridge
                new Polygon(new Point3D(20, -330, 100), new Point3D(140, -330, 100),  new Point3D(140, -350, 100),new Point3D(20, -350, 100))
                        .setEmission(new Color(java.awt.Color.GRAY))
                        .setMaterial(new Material().setKs(0.5).setKt(0.5)),

                //back level of bridge
                new Polygon(new Point3D(170, -330, 0), new Point3D(320, -330, 0),  new Point3D(320, -350, 0),new Point3D(170, -350,0))
                        .setEmission(new Color(java.awt.Color.GRAY))
                        .setMaterial(new Material().setKs(0.5).setKt(0.5)),

                //back level of bridge
                new Polygon(new Point3D(170, -330, 0), new Point3D(320, -330, 0),  new Point3D(320, -350, 0),new Point3D(170, -350,0))
                        .setEmission(new Color(java.awt.Color.GRAY))
                        .setMaterial(new Material().setKs(0.5).setKt(0.5)),

                //bridge 1
                new Polygon(new Point3D(109, -51, 150), new Point3D(-20, -41, 1000),  new Point3D(-20, -50, 1000),new Point3D(109, -61,150))
                        .setEmission(new Color(java.awt.Color.GRAY))
                        .setMaterial(new Material().setKs(0.5).setKt(0.5)),

                //bridge 2
                new Polygon(new Point3D(-37, -78, 150), new Point3D(-57, -44, 1000),  new Point3D(-57, -52, 1000),new Point3D(-37, -92,150))
                        .setEmission(new Color(java.awt.Color.GRAY))
                        .setMaterial(new Material().setKs(0.5).setKt(0.5)),

                //bridge 3
                new Polygon(new Point3D(-105, -79, 150), new Point3D(-161, -26, 1000),  new Point3D(-161, -32, 1000),new Point3D(-105, -96,150))
                        .setEmission(new Color(java.awt.Color.GRAY))
                        .setMaterial(new Material().setKs(0.5).setKt(0.5)),

                //right straight line of bridge (A)
                new Polygon(new Point3D(-230, 230, 50), new Point3D(-210, 230, 50),   new Point3D(-210, -400, 50),new Point3D(-230, -400, 50))
                        .setEmission(new Color(java.awt.Color.BLUE))
                        .setMaterial(new Material().setKs(0.5).setKt(0.5)),

                //More right straight line of bridge (B)
                new Polygon(new Point3D(-300, 230, 50), new Point3D(-320, 230, 50),   new Point3D(-320, -400, 50),new Point3D(-300, -400, 50))
                        .setEmission(new Color(java.awt.Color.BLUE))
                        .setMaterial(new Material().setKs(0.5).setKt(0.5)),


                //More left straight line of bridge (C)
                new Polygon(new Point3D(-380, 240, 50), new Point3D(-400, 240, 50),   new Point3D(-400, -400, 50),new Point3D(-380, -400, 50))
                    .setEmission(new Color(java.awt.Color.BLUE))
                    .setMaterial(new Material().setKs(0.5).setKt(0.5)),

                //left straight line of bridge (D)
                new Polygon(new Point3D(-450, 240, 50), new Point3D(-470, 240, 50),   new Point3D(-470, -400, 50),new Point3D(-450, -400, 50))
                    .setEmission(new Color(java.awt.Color.BLUE))
                    .setMaterial(new Material().setKs(0.5).setKt(0.5)),

                //red rectangle of bridge
                new Polygon(new Point3D(-222, 91, 80), new Point3D(-345, 91, 230),  new Point3D(-345, -50, 230),new Point3D(-222, -50, 80))
                    .setEmission(new Color(java.awt.Color.RED))
                    .setMaterial(new Material().setKs(0.5).setKt(0.5)),

                //yellow rectangle of bridge
                new Polygon(new Point3D(-394, 96, 80), new Point3D(-409, 91, 230),  new Point3D(-409, -50, 230),new Point3D(-394, -50, 80))
                        .setEmission(new Color(java.awt.Color.YELLOW))
                        .setMaterial(new Material().setKs(0.5).setKt(0.5)),


                //surface 2 (2,1,4,3)
                new Polygon(new Point3D(-214, -49, 150), new Point3D(-231, -26, 1000),  new Point3D(-231, -33, 1000),new Point3D(-214, -62, 150))
                    .setEmission(new Color(java.awt.Color.GRAY))
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