package hu.blackbelt.bennersnatch.gradient;

public class GradientColors {

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


    /*
    public static String draw() {

        StringBuilder builder = new StringBuilder();
        String art = asciiArts[random.nextInt (ApplicationStartedAsciiArts.asciiArts.length - 1)];
        String[] artLines = art.split("\n");
        int start = random.nextInt(colors.length - 1);
        boolean forward = true;
        if (artLines.length + start > colors.length) {
            forward = false;
        } else if (artLines.length + start <= colors.length && start >= artLines.length) {
            forward = random.nextBoolean();
        }
        int inc = 1;
        int curr = start;
        if (!forward) {
            inc = -1;
        }
        for (String s : artLines) {
            builder.append(colors[curr]);
            builder.append(s);
            builder.append("\n");
            curr += inc;
        }
        return builder.toString();
    }
     */

}
