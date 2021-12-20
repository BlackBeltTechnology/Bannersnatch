package hu.blackbelt.bannersnatch.generator;

import hu.blackbelt.bannersnatch.figlet.FigFont;
import hu.blackbelt.bannersnatch.figlet.FigFontResources;
import hu.blackbelt.bannersnatch.figlet.FigletRenderer;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.function.Consumer;

public class GenerateResourcesFileProcessor {

    public static class Font {
        File file;
        String constName;
        String fontName;
        FigFont figFont;

        public Font(File file, String constName, String fontName, FigFont figFont) {
            this.file = file;
            this.constName = constName;
            this.fontName = fontName;
            this.figFont = figFont;

        }
    }

    public static void iteraterFonts(Consumer<Font> transformFile) throws IOException {
        StringBuilder sb = new StringBuilder();

        Set ignoredFonts = new HashSet();
        ignoredFonts.add("heart_right");
        ignoredFonts.add("Modular");
        ignoredFonts.add("Heart Left");
        ignoredFonts.add("Broadway KB");
        ignoredFonts.add("Maxfour");
        ignoredFonts.add("Heart Right");
        ignoredFonts.add("Jacky");
        ignoredFonts.add("broadway_kb");
        ignoredFonts.add("Greek");
        ignoredFonts.add("Sweet");
        ignoredFonts.add("heart_left");
        ignoredFonts.add("Stforek");
        ignoredFonts.add("Pyramid");
        ignoredFonts.add("alligator3");
        ignoredFonts.add("maxiwi");
        ignoredFonts.add("miniwi");
        ignoredFonts.add("Wavy");

        File[] files = new File("./src/main/resources").toPath().normalize().toFile().listFiles();
        for (int i = 0; i < files.length; i++){
            if (files[i].isFile()){ //this line weeds out other directories/folders
                File f = files[i];
                if (f.getName().endsWith(".flf")) {
                    String fontName = f.getName().substring(0, f.getName().length() - 4);
                    if (!ignoredFonts.contains(fontName)) {
                        FigFont figFont = FigFontResources.loadFigFontResource(f.getName());
                        final FigletRenderer figletRenderer = new FigletRenderer(figFont);
                        String constName = fontName
                                .replaceAll(" ", "_")
                                .replaceAll("-", "_")
                                .replaceAll("'", "_")
                                .replaceAll("0", "ZERO")
                                .replaceAll("1", "ONE")
                                .replaceAll("2", "TWO")
                                .replaceAll("3", "THREE")
                                .replaceAll("4", "FOUR")
                                .replaceAll("5", "FIVE")
                                .replaceAll("6", "SIX")
                                .replaceAll("7", "SERVEN")
                                .replaceAll("8", "EIGHT")
                                .replaceAll("9", "NINE")
                                .toUpperCase(Locale.ROOT);

                        transformFile.accept(new Font(f, constName, fontName, figFont));
                    }
                }
            }
        }
    }
}
