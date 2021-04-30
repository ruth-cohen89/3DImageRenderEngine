package renderer;

import org.junit.jupiter.api.Test;
import primitives.Color;

import static org.junit.jupiter.api.Assertions.*;

class ImageWriterTest {

    @Test
    void testWriteToImage() {
        ImageWriter imageWriter= new ImageWriter("testblue", 800, 500);
        for( int i=0; i<800; i++){
            for (int j= 0;  j< 500; j++) {
                if (i % 50 == 0) {
                    imageWriter.writePixel(i, j, Color.BLACK);
                }
                else if (j % 50 == 0) {
                    imageWriter.writePixel(i, j, Color.BLACK);
                }
                else {
                    imageWriter.writePixel(i, j, new Color(0d, 0d, 255d));
                }
            }
        }
        imageWriter.writeToImage();
    }
}