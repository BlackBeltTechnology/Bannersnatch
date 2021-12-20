package hu.blackbelt.bannersnatch;

import hu.blackbelt.bannersnatch.color.Hsl;
import hu.blackbelt.bannersnatch.color.Rgb;
import hu.blackbelt.bannersnatch.color.TermColor;
import hu.blackbelt.bannersnatch.figlet.FigFont;
import hu.blackbelt.bannersnatch.figlet.FigFontResources;
import hu.blackbelt.bannersnatch.figlet.FigletRenderer;
import lombok.Builder;

import java.io.IOException;
import java.util.*;

@Builder(builderMethodName = "bannerBuilder")
public class Banner {
    static Random random = new Random();

    @Builder.Default
    TermColor.TermColorModel termColorModel = TermColor.TermColorModel.COLOR_256;

    @Builder.Default
    boolean isGrayscale = false;

    @Builder.Default
    String fontName = FigFontResources.STANDARD;

    /**
     * Draw RGB palette
     * @param cols
     * @return
     */
    public String drawPalette(int cols, int rows) {
        StringBuilder sb = new StringBuilder();
        for (int l = 0; l < rows ; l++) {
            for (int h = 0; h < cols; h++) {
                Rgb rgb = new Hsl(
                        (1.0 / (double) cols) * (double) h,
                        1.0,
                        (double) (l + 1) / (double) rows).toRgb();

                sb.append(rgb.toTerminalColor().toAnsiSequence(termColorModel, false, isGrayscale));
                sb.append("█");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Draw the given text as standard ascii.
     * @param text
     * @return
     */
    public String drawAscii(String text) {
        try {
            FigFont figFont = FigFontResources.loadFigFontResource(fontName);
            final FigletRenderer figletRenderer = new FigletRenderer(figFont);
            String art = figletRenderer.renderText(text);
            return art;
        } catch (IOException e) {
            return text;
        }
    }

    /**
     * Draw the given text with the given HSL color
     * @param text
     * @param hsl
     * @return
     */
    public String drawColor(String text, Hsl hsl) {
        return colorize(drawAscii(text), hsl, hsl);
    }

    /**
     * Draw the given text with the given RGB color
     * @param text
     * @param rgb
     * @return
     */
    public String drawColor(String text, Rgb rgb) {
        return colorize(drawAscii(text), rgb.toHsl(), rgb.toHsl());
    }

    /**
     * Draw the given text, fro up to down woth the given HSL color.
     * @param text
     * @param hslStart
     * @param hslEnd
     * @return
     */
    public String drawColorRange(String text, Hsl hslStart, Hsl hslEnd) {
        return colorize(drawAscii(text), hslStart, hslEnd);
    }

    /**
     * Draw the given text, fro up to down woth the given HSL color.
     * @param text
     * @param rgbStart
     * @param rgbEnd
     * @return
     */
    public String drawColorRange(String text, Rgb rgbStart, Rgb rgbEnd) {
        return colorize(drawAscii(text), rgbStart.toHsl(), rgbEnd.toHsl());
    }

    /**
     * Gining crazy, select random font and interpolate between two random HSL color.
     * @param text
     * @return
     */
    public String drawCrazy(String text) {

        List<String> fonts = new ArrayList<>(FigFontResources.FONTS);
        String fontName = fonts.get(random.nextInt (fonts.size()- 1));

        Banner figletColorRenderer = Banner.bannerBuilder()
                .fontName(fontName).termColorModel(termColorModel).isGrayscale(isGrayscale).build();

        Hsl hslStart = new Hsl(Math.random(), Math.random(), Math.random());
        Hsl hslEnd = new Hsl(Math.random(), Math.random(), Math.random());

        return figletColorRenderer.drawColorRange(text, hslStart, hslEnd);
    }


    /**
     * Colorize the given text lines with interpolated colors between given HSL color codes.
     * @param ascii
     * @param hslStart
     * @param hslStart
     * @return
     */
    public String colorize(String ascii, Hsl hslStart, Hsl hslEnd) {
        String[] artLines = ascii.split("\n");

        double inc = 1.0 / (double) artLines.length;
        double pos = 0;

        StringBuilder builder = new StringBuilder();
        for (String s : artLines) {
            builder.append(hslStart.interpolate(hslEnd, pos)
                    .toTerminalColor().toAnsiSequence(termColorModel, false, isGrayscale));
            builder.append(s);
            builder.append("\n");
            pos += inc;
        }
        return builder.toString();
    }
}
