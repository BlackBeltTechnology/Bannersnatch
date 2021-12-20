package hu.blackbelt.bannersnatch.color;

public class TermColor {
    public static enum TermColorModel {
        COLOR_16,
        COLOR_256,
        COLOR_RGB,
        COLOR_16_LEGACY,
    }

    /* Terminal color conversion table */
    private static final int[] COLOR_256_TO_16 = {
            0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15,
            0, 4, 4, 4, 12, 12, 2, 6, 4, 4, 12, 12, 2, 2, 6, 4,
            12, 12, 2, 2, 2, 6, 12, 12, 10, 10, 10, 10, 14, 12, 10, 10,
            10, 10, 10, 14, 1, 5, 4, 4, 12, 12, 3, 8, 4, 4, 12, 12,
            2, 2, 6, 4, 12, 12, 2, 2, 2, 6, 12, 12, 10, 10, 10, 10,
            14, 12, 10, 10, 10, 10, 10, 14, 1, 1, 5, 4, 12, 12, 1, 1,
            5, 4, 12, 12, 3, 3, 8, 4, 12, 12, 2, 2, 2, 6, 12, 12,
            10, 10, 10, 10, 14, 12, 10, 10, 10, 10, 10, 14, 1, 1, 1, 5,
            12, 12, 1, 1, 1, 5, 12, 12, 1, 1, 1, 5, 12, 12, 3, 3,
            3, 7, 12, 12, 10, 10, 10, 10, 14, 12, 10, 10, 10, 10, 10, 14,
            9, 9, 9, 9, 13, 12, 9, 9, 9, 9, 13, 12, 9, 9, 9, 9,
            13, 12, 9, 9, 9, 9, 13, 12, 11, 11, 11, 11, 7, 12, 10, 10,
            10, 10, 10, 14, 9, 9, 9, 9, 9, 13, 9, 9, 9, 9, 9, 13,
            9, 9, 9, 9, 9, 13, 9, 9, 9, 9, 9, 13, 9, 9, 9, 9,
            9, 13, 11, 11, 11, 11, 11, 15, 0, 0, 0, 0, 0, 0, 8, 8,
            8, 8, 8, 8, 7, 7, 7, 7, 7, 7, 15, 15, 15, 15, 15, 15
    };
    private static final int[] q2c = {0x00, 0x5f, 0x87, 0xaf, 0xd7, 0xff};

    private Rgb rgb;
    private Rgb grayscale;

    public TermColor(Rgb rgb) {
        this.rgb = rgb;
        this.grayscale = rgb.toGrayscale();
    }

    /**
     * Convert an RGB triplet to the xterm(1) 256 colour palette.
     * <p>
     * xterm provides a 6x6x6 colour cube (16 - 231) and 24 greys (232 - 255). We
     * map our RGB colour to the closest in the cube, also work out the closest
     * grey, and use the nearest of the two.
     * <p>
     * Note that the xterm has much lower resolution for darker colours (they are
     * not evenly spread out), so our 6 levels are not evenly spread: 0x0, 0x5f
     * (95), 0x87 (135), 0xaf (175), 0xd7 (215) and 0xff (255). Greys are more
     * evenly spread (8, 18, 28 ... 238).
     *
     * @return color in 256 color terminal space
     */
    private int getColorValue256(int r, int g, int b) {
        int qr, qg, qb, cr, cg, cb, d, idx;
        int grey_avg, grey_idx, grey;

        /* Map RGB to 6x6x6 cube. */
        qr = colourTo6cube(r);
        cr = q2c[qr];
        qg = colourTo6cube(g);
        cg = q2c[qg];
        qb = colourTo6cube(b);
        cb = q2c[qb];

        /* If we have hit the colour exactly, return early. */
        if (cr == r && cg == g && cb == b)
            return ((16 + (36 * qr) + (6 * qg) + qb));

        /* Work out the closest grey (average of RGB). */
        grey_avg = (r + g + b) / 3;
        if (grey_avg > 238)
            grey_idx = 23;
        else
            grey_idx = (grey_avg - 3) / 10;
        grey = 8 + (10 * grey_idx);

        /* Is grey or 6x6x6 colour closest? */
        d = colorDistSq(cr, cg, cb, r, g, b);
        if (colorDistSq(grey, grey, grey, r, g, b) < d)
            idx = 232 + grey_idx;
        else
            idx = 16 + (36 * qr) + (6 * qg) + qb;
        return idx;
    }

    /**
     * Get 256 color terminal code
     *
     * @return
     */
    public int getColorValue256() {
        return getColorValue256(rgb.r, rgb.g, rgb.b);
    }

    /**
     * Get 256 color grayscale terminal code
     *
     * @return
     */
    public int getGrayscaleValue256() {
        return getColorValue256(grayscale.r, grayscale.g, grayscale.b);
    }

    /**
     * Get 16 color terminal code
     *
     * @return
     */
    public int getColorValue16() {
        return (COLOR_256_TO_16[getColorValue256() & 0xff]);
    }

    /**
     * Get 16 color grayscale terminal code
     *
     * @return
     */
    public int getGrayscaleValue16() {
        return (COLOR_256_TO_16[getGrayscaleValue256() & 0xff]);

    }

    /**
     * Get the color code depends on color number and gray parameter
     * @param termColorModel The terminal outout color model
     * @param gray Is grayscaled
     * @return
     */
    public int getColor(TermColorModel termColorModel, boolean gray) {
        if (TermColorModel.COLOR_256 == termColorModel && !gray) {
            return getColorValue256();
        } else if (TermColorModel.COLOR_256 == termColorModel && gray) {
            return getGrayscaleValue256();
        } else if (TermColorModel.COLOR_256 != termColorModel && gray) {
            return getGrayscaleValue256();
        } else {
            return getColorValue16();
        }
    }

    /**
     * Create ANSI escape sequence from colot
     *
     * ESC[38;5;⟨n⟩m Select foreground color
     * ESC[48;5;⟨n⟩m Select background color
     *   0-  7:  standard colors (as in ESC [ 30–37 m)
     *   8- 15:  high intensity colors (as in ESC [ 90–97 m)
     *  16-231:  6 × 6 × 6 cube (216 colors): 16 + 36 × r + 6 × g + b (0 ≤ r, g, b ≤ 5)
     * 232-255:  grayscale from black to white in 24 steps
     *
     * @param isBackground
     * @param termColorModel
     * @param isGrayScale
     * @return
     */
    public String toAnsiSequence(TermColorModel termColorModel, boolean isBackground, boolean isGrayScale) {
        StringBuilder sb = new StringBuilder();
        sb.append("\u001b[");
        Rgb col = rgb;
        if (isGrayScale) {
            col = grayscale;
        }

        if (termColorModel == TermColorModel.COLOR_RGB ||
                termColorModel == TermColorModel.COLOR_256 ||
                termColorModel == TermColorModel.COLOR_16) {
            if (isBackground) {
                sb.append("48;");
            } else {
                sb.append("38;");
            }
            if (termColorModel == TermColorModel.COLOR_RGB) {
                sb.append("2;" + col.r + ";" + col.g + ";" + col.b);
            } else if (termColorModel == TermColorModel.COLOR_256 || termColorModel == TermColorModel.COLOR_16) {
                sb.append("5;" + getColor(termColorModel, isGrayScale));
            }
        } else {
            int color = getColor(termColorModel, isGrayScale);
            int seq;
            if (color < 8) {
                seq = color + 30;
            } else {
                seq = color + 82;
            }
            if (isBackground) {
                seq = seq + 10;
            }
            sb.append(seq);
        }
        sb.append('m');
        return sb.toString();
    }

    private int colorDistSq(int cr, int cg, int cb, int r, int g, int b) {
        return ((cr - r) * (cr - r) + (cg - g) * (cg - g) + (cb - b) * (cb - b));
    }

    private int colourTo6cube(int v) {
        if (v < 48)
            return (0);
        if (v < 114)
            return (1);
        return ((v - 35) / 40);
    }
}
