package hu.blackbelt.bannersnatch;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class ImageToTextTest {

    @Test
    void convertImage() throws FileNotFoundException {
        System.out.println(ImageToText.builder()
                .isColorConverted(false)
                .targetWidth(40).build()
                .convertImage(new FileInputStream("src/test/resources/judo-man-icon-cropped.png")));
        System.out.println(ImageToText.builder()
                .targetWidth(40).build()
                .convertImage(new FileInputStream("src/test/resources/judo-man-icon-cropped.png")));
        System.out.println(ImageToText.builder()
                .isGraycale(true)
                .targetWidth(40).build()
                .convertImage(new FileInputStream("src/test/resources/judo-man-icon-cropped.png")));
        System.out.println(ImageToText.builder()
                .useGrayMap(false)
                .targetWidth(40).build()
                .convertImage(new FileInputStream("src/test/resources/judo-man-icon-cropped.png")));
        System.out.println(ImageToText.builder()
                .useGrayMap(false)
                .targetWidth(60).build()
                .convertImage(new FileInputStream("src/test/resources/judo-man-icon-cropped.png")));

        assertTrue(true);
    }
}