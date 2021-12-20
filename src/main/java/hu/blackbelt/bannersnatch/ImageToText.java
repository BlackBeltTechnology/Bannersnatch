package hu.blackbelt.bannersnatch;

import hu.blackbelt.bannersnatch.color.Rgb;
import hu.blackbelt.bannersnatch.color.TermColor;
import lombok.Builder;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;

@Builder
public class ImageToText {
    /**
     * Get ascii character for gray value, the ramp interpolated between 0 (black) and 255 (white)
     */
    public enum GrayRampType {
        STANDARD("$@B%8&WM#*oahkbdpqwmZO0QLCJUYXzcvunxrjft/|()1{}[]?-_+~<>i!lI;:,\"^`'. "),
        SHORT("@%#*+=-:. "),
        UNICODE_SHADE("\u2588\u2593\u2592\u2591 "),
        IBM_437_SHADE(new String(new char[] { 219, 178, 177, 176, 32}));

        public String ramp;
        GrayRampType(String ramp) {
            this.ramp = ramp;
        }
    }

    @Builder.Default
    boolean isColorConverted = true;

    @Builder.Default
    boolean useGrayRamp = true;

    @Builder.Default
    int width = 80;

    @Builder.Default
    double aspectRatio =  3.0 / 4.0;

    @Builder.Default
    boolean isGraycale = false;

    @Builder.Default
    String customGrayRamp = "";

    @Builder.Default
    GrayRampType grayRampType = GrayRampType.STANDARD;

    @Builder.Default
    TermColor.TermColorModel termColorModel = TermColor.TermColorModel.COLOR_256;


    private BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = resizedImage.createGraphics();
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        graphics2D.dispose();
        return resizedImage;
    }

    /**
     * Convert image to ansii / ascii art
     * @param image
     * @return
     */
    public String convertImage(InputStream image) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(image);
        } catch (IOException e) {
        }

        img = resizeImage(img, width, (int) ((double) img.getHeight() * ((double) width / (double) img.getHeight()) * aspectRatio));

        StringBuilder sb = new StringBuilder();
        sb.append(TermColor.RESET);
        for (int i = 0; i < img.getHeight(); i++) {
            String lastAnsiSeq = "";
            for (int j = 0; j < img.getWidth(); j++) {
                Color pixcol = new Color(img.getRGB(j, i));
                Rgb rgb = new Rgb(pixcol.getRGB());
                if (pixcol.getAlpha() > 0) {
                    if (isColorConverted) {
                        String rgbSeq = rgb.toTerminalColor().toAnsiSequence(termColorModel, false, isGraycale);
                        if (!lastAnsiSeq.equals(rgbSeq)) {
                            sb.append(rgbSeq);
                            lastAnsiSeq = rgbSeq;
                        }
                    }
                    if (useGrayRamp) {
                        sb.append(strChar(255 - rgb.toGrayscale().r));
                    } else {
                        sb.append("█");
                    }
                } else {
                    sb.append(' ');
                }
            }
            sb.append("\n");
        }
        sb.append(TermColor.RESET);
        return sb.toString();
    }

    private char getCharacterForGrayScale(int grayScale) {
        String grayRamp = grayRampType.ramp;
        if (customGrayRamp != null && !customGrayRamp.trim().equals("")) {
            grayRamp = customGrayRamp;
        }
        return grayRamp.charAt((int)(Math.ceil(((grayRamp.length() - 1) * grayScale) / 255)));
    }

    private char strChar(int g) {
        return getCharacterForGrayScale(g);
    }
}