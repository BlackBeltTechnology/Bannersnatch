package hu.blackbelt;

import hu.blackbelt.bannersnatch.jfiglet.FigletRenderer;

import java.io.IOException;
import java.util.Locale;

public class GenerateFigFontResources {

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();

        sb.append("package hu.blackbelt.bannersnatch.jfiglet;\n" +
                "\n" +
                "import java.io.IOException;\n" +
                "import java.io.InputStream;\n" +
                "\n" +
                "/**\n" +
                " * FigFontResources contains constants used to identify bundles FIGfont\n" +
                " * resources.\n" +
                " */\n");
        sb.append("public class FigFontResources {\n");

        GenerateResourcesFileProcessor.iteraterFonts((f) -> {
            final FigletRenderer figletRenderer = new FigletRenderer(f.figFont);
            //System.out.println(figletRenderer.renderText(fontName));
            String constName = f.fontName
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

            sb.append("\t/**\n" +
                    "\t * The {@value #" + constName + "} FIGfont.\n" +
                    "\t * <p>\n" +
                    "\t * Example output:\n" +
                    "\t * </p>\n" +
                    "\t * \n" +
                    "\t * <pre>\n" +
                    figletRenderer.renderText(f.fontName)
                            .replaceAll("/", "&#47;")
                            .replaceAll("\\\\", "&#92;")
                            .replaceAll("\\*", "&#42;") +
                    "\n" +
                    "\t * </pre>\n" +
                    "\t */\n" +
                    "\tpublic static final String " + constName + " = \"" + f.file.getName() + "\";\n\n");
        });

        sb.append("\tprivate FigFontResources() {\n" +
                "\t\t// Do nothing.\n" +
                "\t}\n" +
                "\n" +
                "\t/**\n" +
                "\t * Loads a {@link FigFont} from a resource name.\n" +
                "\t * \n" +
                "\t * @param resourceName\n" +
                "\t *            The name of the resource from which to load a {@link FigFont}.\n" +
                "\t * @return The {@link FigFont} loaded from the requested resource.\n" +
                "\t * @throws IOException\n" +
                "\t *             if there is problem loading a {@link FigFont} from the specified\n" +
                "\t *             resource.\n" +
                "\t */\n" +
                "\tpublic static FigFont loadFigFontResource(final String resourceName) throws IOException {\n" +
                "\t\ttry (final InputStream inputStream = FigFontResources.class.getClassLoader()\n" +
                "\t\t\t\t.getResourceAsStream(resourceName)) {\n" +
                "\t\t\treturn FigFont.loadFigFont(inputStream);\n" +
                "\t\t}\n" +
                "\t}\n" +
                "}\n");

        System.out.println(sb.toString());
    }
}
