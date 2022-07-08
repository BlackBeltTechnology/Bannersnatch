package hu.blackbelt.bannersnatch;

import hu.blackbelt.bannersnatch.color.Hsl;
import hu.blackbelt.bannersnatch.color.Rgb;
import hu.blackbelt.bannersnatch.color.TermColor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BannerTest {

    @Test
    void drawPalette() {
        System.out.println("RGB Terminal\n\n" + Banner.bannerBuilder()
                .termColorModel(TermColor.TermColorModel.COLOR_RGB).build().drawPalette(80, 16) + "\n\n");
        System.out.println("256 Color Terminal\n\n" + Banner.bannerBuilder()
                .termColorModel(TermColor.TermColorModel.COLOR_256).build().drawPalette(80, 16) + "\n\n");
        System.out.println("16 Color Terminal\n\n" + Banner.bannerBuilder()
                .termColorModel(TermColor.TermColorModel.COLOR_16).build().drawPalette(80, 16) + "\n\n");
        System.out.println("16 Color Legacy Terminal\n\n" + Banner.bannerBuilder()
                .termColorModel(TermColor.TermColorModel.COLOR_16_LEGACY).build().drawPalette(80, 16) + "\n\n");

        System.out.println("RGB Terminal (Grayscale)\n\n" + Banner.bannerBuilder()
                .termColorModel(TermColor.TermColorModel.COLOR_RGB).isGrayscale(true).build().drawPalette(80, 16) + "\n\n");
        System.out.println("256 Color Terminal (Grayscale)\n\n" + Banner.bannerBuilder()
                .termColorModel(TermColor.TermColorModel.COLOR_256).isGrayscale(true).build().drawPalette(80, 16) + "\n\n");
        System.out.println("16 Color Terminal (Grayscale)\n\n" + Banner.bannerBuilder()
                .termColorModel(TermColor.TermColorModel.COLOR_16).isGrayscale(true).build().drawPalette(80, 16) + "\n\n");
        System.out.println("16 Color Legacy Terminal (Grayscale)\n\n" + Banner.bannerBuilder()
                .termColorModel(TermColor.TermColorModel.COLOR_16_LEGACY).isGrayscale(true).build().drawPalette(80, 16) + "\n\n");

        assertTrue(true);
    }

    @Test
    void drawAscii() {
        System.out.println(Banner.bannerBuilder().build().drawAscii("Colorless ASCII") + "\n\n");
        assertTrue(true);
    }

    @Test
    void drawColor() {
        System.out.println(Banner.bannerBuilder().build().drawColor("Red Text",
                new Hsl(0, 1.0, 0.5)) + "\n\n");
        System.out.println(Banner.bannerBuilder().build().drawColor("Red Text",
                new Rgb(255, 0, 0)) + "\n\n");

        assertTrue(true);
    }

    @Test
    void drawColorRange() {
        System.out.println(Banner.bannerBuilder().build().drawColorRange("Rainbow Color",
                new Hsl(0, 1.0, 0.5), new Hsl(0.5, 1.0, 0.5)) + "\n\n");

        assertTrue(true);
    }

    @Test
    void drawCrazy() {
        for (int i = 0; i < 32; i ++) {
            System.out.println(Banner.bannerBuilder().build().drawCrazy("Crazy heh?") + "\n\n");
        }
        assertTrue(true);
    }

    @Test
    void colorize() {
        StringBuilder sb = new StringBuilder();
        sb.append("Line 1\n");
        sb.append("Line 2\n");
        sb.append("Line 3\n");
        sb.append("Line 4\n");
        sb.append("Line 5\n");
        sb.append("Line 6\n");
        sb.append("Line 7\n");
        sb.append("Line 8\n");
        sb.append("Line 9\n");
        sb.append("Line 10\n");
        sb.append("Line 11\n");
        sb.append("Line 12\n");
        sb.append("Line 13\n");
        sb.append("Line 14\n");
        sb.append("Line 15\n");
        sb.append("Line 16\n");

        System.out.println(Banner.bannerBuilder().build().colorize(sb.toString(),
                new Hsl(0, 1.0, 0.5), new Hsl(1.0, 1.0, 0.5)) + "\n\n");

        assertTrue(true);
    }
}