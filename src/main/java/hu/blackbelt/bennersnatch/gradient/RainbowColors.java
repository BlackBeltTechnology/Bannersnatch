package hu.blackbelt.bennersnatch.gradient;

import hu.blackbelt.bannersnatch.jfiglet.FigFont;
import hu.blackbelt.bannersnatch.jfiglet.FigFontResources;
import hu.blackbelt.bannersnatch.jfiglet.FigletRenderer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RainbowColors {
    static Random random = new Random();

    public static String[] GRADIENT_COLORS = new String[]{
            "\u001b[38;5;17m",
            "\u001b[38;5;18m",
            "\u001b[38;5;19m",
            "\u001b[38;5;20m",
            "\u001b[38;5;21m",
            "\u001b[38;5;63m",
            "\u001b[38;5;69m",
            "\u001b[38;5;105m",
            "\u001b[38;5;111m",
            "\u001b[38;5;147m",
            "\u001b[38;5;153m",
            "\u001b[38;5;152m",
            "\u001b[38;5;151m",
            "\u001b[38;5;115m",
            "\u001b[38;5;114m",
            "\u001b[38;5;78m",
            "\u001b[38;5;77m",
            "\u001b[38;5;40m",
            "\u001b[38;5;76m",
            "\u001b[38;5;112m",
            "\u001b[38;5;148m",
            "\u001b[38;5;154m",
            "\u001b[38;5;190m",
            "\u001b[38;5;226m",
            "\u001b[38;5;220m",
            "\u001b[38;5;214m",
            "\u001b[38;5;208m",
            "\u001b[38;5;202m",
            "\u001b[38;5;196m",
            "\u001b[38;5;203m",
            "\u001b[38;5;167m",
            "\u001b[38;5;174m",
            "\u001b[38;5;138m",
            "\u001b[38;5;145m",
            "\u001b[38;5;188m",
            "\u001b[38;5;231m"
    };

    public static String drawPalette() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < GRADIENT_COLORS.length; i++) {
            sb.append(GRADIENT_COLORS[i] + "██");
        }
        return sb.toString();
    }

    public static String drawAscii(String fontName, String text) throws IOException {

        FigFont figFont = FigFontResources.loadFigFontResource(fontName);
        final FigletRenderer figletRenderer = new FigletRenderer(figFont);
        String art = figletRenderer.renderText(text);
        return art;
    }

    public static String drawGradient(String fontName, String text, int start, int end) throws IOException {
        String ascii = drawAscii(fontName, text);
        String[] artLines = ascii.split("\n");

        float inc = ((float)(end - start)) / (float) artLines.length;
        float pos = (float) start;

        StringBuilder builder = new StringBuilder();
        for (String s : artLines) {
            builder.append(GRADIENT_COLORS[(int) pos]);
            builder.append(s);
            builder.append("\n");
            pos += inc;
        }
        return builder.toString();
    }

    public static String colorize(String ascii, int paletteStart, int paletteEnd) throws IOException {
        String[] artLines = ascii.split("\n");

        float inc = ((float)(paletteEnd - paletteStart)) / (float) artLines.length;
        float pos = (float) paletteStart;

        StringBuilder builder = new StringBuilder();
        for (String s : artLines) {
            builder.append(GRADIENT_COLORS[(int) pos]);
            builder.append(s);
            builder.append("\n");
            pos += inc;
        }
        return builder.toString();
    }

    public static String randomDraw(String text) throws IOException {
        List<String> fonts = new ArrayList<>(FigFontResources.FONTS);
        String fontName = fonts.get(random.nextInt (fonts.size()- 1));
        String ascii = drawAscii(fontName, text);
        String[] artLines = ascii.split("\n");

        int pos = random.nextInt(GRADIENT_COLORS.length - 1);
        int end = 0;
        int start = 0;
        if (artLines.length + pos > GRADIENT_COLORS.length) {
            start  = pos - artLines.length;
            end = pos;
        } else  {
            start  = pos;
            end = pos + artLines.length;
        }
        return colorize(ascii, start, end);
    }

}
