package renderer;

import org.junit.jupiter.api.Test;
import primitives.Color;

import static org.junit.jupiter.api.Assertions.*;

class ImageWriterTest {

    @Test
    void testWriteToImage() { //resolution 800 on 500
        ImageWriter imageWriter= new ImageWriter("testBlue", 800, 500);
        for( int i=0; i<800; i++){
            for (int j= 0;  j< 500; j++) {
                if (i % 50 == 0) { //color 16 lines of squares in black
                    imageWriter.writePixel(i, j, Color.BLACK);
                }
                else if (j % 50 == 0) { //color 10 lines of squares in black
                    imageWriter.writePixel(i, j, Color.BLACK);
                }
                else { //color the inside of squares in blue
                    imageWriter.writePixel(i, j, new Color(0d, 0d, 255d));
                }
            }
        }
        imageWriter.writeToImage();
    }
}