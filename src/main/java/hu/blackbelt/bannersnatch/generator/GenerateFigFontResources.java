package hu.blackbelt.bannersnatch.generator;

import hu.blackbelt.bannersnatch.figlet.FigletRenderer;

import java.io.IOException;
import java.util.Locale;

public class GenerateFigFontResources {

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();

        sb.append("package hu.blackbelt.bannersnatch.jfiglet;\n" +
                "\n" +
                "import java.io.IOException;\n" +
                "import java.io.InputStream;\n" +
                "import java.util.HashSet;\n" +
                "import java.util.Set;\n" +
                "\n" +
                "/**\n" +
                " * FigFontResources contains constants used to identify bundles FIGfont\n" +
                " * resources.\n" +
                " */\n");
        sb.append("public class FigFontResources {\n" +
                "\tpublic static final Set<String> FONTS = new HashSet();\n"
                );

        StringBuilder fontCatalog = new StringBuilder();

        GenerateResourcesFileProcessor.iteraterFonts((f) -> {
            final FigletRenderer figletRenderer = new FigletRenderer(f.figFont);
            //System.out.println(figletRenderer.renderText(fontName));
            String constName = f.fontName
                    .replace(" ", "_")
                    .replace("-", "_")
                    .replace("'", "_")
                    .replace("0", "ZERO")
                    .replace("1", "ONE")
                    .replace("2", "TWO")
                    .replace("3", "THREE")
                    .replace("4", "FOUR")
                    .replace("5", "FIVE")
                    .replace("6", "SIX")
                    .replace("7", "SERVEN")
                    .replace("8", "EIGHT")
                    .replace("9", "NINE")
                    .toUpperCase(Locale.ROOT);

            sb.append("\t/**\n" +
                    "\t * The {@value #" + constName + "} FIGfont.\n" +
                    "\t * <p>\n" +
                    "\t * Example output:\n" +
                    "\t * </p>\n" +
                    "\t * \n" +
                    "\t * <pre>\n" +
                    figletRenderer.renderText(f.fontName)
                            .replace("/", "&#47;")
                            .replace("\\", "&#92;")
                            .replace("*", "&#42;") +
                    "\n" +
                    "\t * </pre>\n" +
                    "\t */\n" +
                    "\tpublic static final String " + constName + " = \"" + f.file.getName() + "\";\n\n");
            fontCatalog.append("\t\tFONTS.add(" + f.constName + ");\n");

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
                "\t}\n\n" +
                "\tstatic {\n" +
                fontCatalog.toString() +
                "\t}\n" +
                "}\n");

        System.out.println(sb.toString());
    }
}
