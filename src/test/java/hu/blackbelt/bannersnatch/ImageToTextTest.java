package hu.blackbelt.bannersnatch;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class ImageToTextTest {

    @Test
    void convertImage() throws FileNotFoundException {
        System.out.println(ImageToText.builder().isColorConverted(false).build().convertImage(new FileInputStream("src/test/resources/judo-man-icon-cropped.png")));
        System.out.println(ImageToText.builder().build().convertImage(new FileInputStream("src/test/resources/judo-man-icon-cropped.png")));
        System.out.println(ImageToText.builder().isGraycale(true).build().convertImage(new FileInputStream("src/test/resources/judo-man-icon-cropped.png")));
        System.out.println(ImageToText.builder().useGrayMap(false).build().convertImage(new FileInputStream("src/test/resources/judo-man-icon-cropped.png")));

        assertTrue(true);
    }
}