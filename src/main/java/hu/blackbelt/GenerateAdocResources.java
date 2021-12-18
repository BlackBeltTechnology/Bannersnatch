package hu.blackbelt;

import hu.blackbelt.bannersnatch.jfiglet.FigletRenderer;

import java.io.IOException;

public class GenerateAdocResources {

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();

        GenerateResourcesFileProcessor.iteraterFonts((f) -> {
                    final FigletRenderer figletRenderer = new FigletRenderer(f.figFont);
            sb.append("#### " + f.fontName + " (" + f.constName + ")\n```\n");
            sb.append(figletRenderer.renderText(f.fontName) + "\n```\n");
        });
        System.out.println(sb.toString());
    }
}
