package hu.blackbelt.bannersnatch.generator;

import hu.blackbelt.bannersnatch.figlet.FigletRenderer;

import java.io.IOException;

public class GenerateAdocResources {

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();

        GenerateResourcesFileProcessor.iteraterFonts((f) -> {
                    final FigletRenderer figletRenderer = new FigletRenderer(f.figFont);
            sb.append("==== " + f.fontName + " (" + f.constName + ")\n[source]\n----\n\n");
            sb.append(figletRenderer.renderText(f.fontName) + "\n----\n");
        });
        System.out.println(sb.toString());
    }
}
