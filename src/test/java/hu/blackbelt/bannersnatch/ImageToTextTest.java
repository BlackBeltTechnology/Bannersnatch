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
                .width(40).build()
                .convertImage(new FileInputStream("src/test/resources/judo-man-icon-cropped.png")));
        System.out.println(ImageToText.builder()
                .width(40).build()
                .convertImage(new FileInputStream("src/test/resources/judo-man-icon-cropped.png")));
        System.out.println(ImageToText.builder()
                .isGraycale(true)
                .width(40).build()
                .convertImage(new FileInputStream("src/test/resources/judo-man-icon-cropped.png")));
        System.out.println(ImageToText.builder()
                .useGrayRamp(false)
                .width(40).build()
                .convertImage(new FileInputStream("src/test/resources/judo-man-icon-cropped.png")));
        System.out.println(ImageToText.builder()
                .useGrayRamp(false)
                .width(60).build()
                .convertImage(new FileInputStream("src/test/resources/judo-man-icon-cropped.png")));

        System.out.println(ImageToText.builder()
                .useGrayRamp(true)
                .grayRampType(ImageToText.GrayRampType.SHORT)
                .width(60).build()
                .convertImage(new FileInputStream("src/test/resources/judo-man-icon-cropped.png")));

        System.out.println(ImageToText.builder()
                .useGrayRamp(true)
                .isColorConverted(true)
                .grayRampType(ImageToText.GrayRampType.UNICODE_SHADE)
                .width(60).build()
                .convertImage(new FileInputStream("src/test/resources/judo-man-icon-cropped.png")));

        System.out.println(ImageToText.builder()
                .useGrayRamp(false)
                .isColorConverted(true)
                .transparentColor(16)
                .width(60).build()
                .convertImage(new FileInputStream("src/test/resources/judo-man-icon-cropped.png")));

        assertTrue(true);
    }
}