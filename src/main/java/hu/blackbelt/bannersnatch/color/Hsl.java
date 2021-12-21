package hu.blackbelt.bannersnatch.color;

/**
 * Represents a color in HSL color space.
 */
public class Hsl {
    public final double h, s, l;

    /**
     * Create a HSL color with the given parameters.
     * @param hue
     * @param saturation
     * @param lightness
     */
    public Hsl(double hue, double saturation, double lightness) {
        if (hue > 1.0 || hue < 0 || saturation > 1.0 || saturation < 0 || lightness > 1.0 || lightness < 0) {
            throw new IllegalArgumentException("hue, saturation and lightness value have to be between 0.0 and 1.0");
        }
        this.h = hue;
        this.s = saturation;
        this.l = lightness;
    }

    public Hsl(double hue) {
        this(hue, 1.0f, 0.5f);
    }

    /**
     * Convert HSL calue to RGB. It is used to make circle palette.
     *
     * @return
     */
    public Rgb toRgb() {
        double r, g, b;

        if (s <= 0) {
            r = g = b = l;/*w ww .  j ava2 s.c o m*/
        } else {
            double q = l < 0.5 ? l * (1 + s) : l + s - l * s;
            double p = 2 * l - q;
            r = hue2rgb(p, q, h + 1 / 3.0);
            g = hue2rgb(p, q, h);
            b = hue2rgb(p, q, h - 1 / 3.0);
        }
        return new Rgb((int) Math.round(r * 255), (int) Math.round(g * 255), (int) Math.round(b * 255));
    }

    /**
     * Return TermColor with the current value.
     * @return
     */
    public TermColor toTerminalColor() {
        return toRgb().toTerminalColor();
    }


    /**
     * Interpolate current value and the target color
     * @param target The target HSL value which ic achived when coefficient is 1.0.
     * @param coefficient
     * @return
     */
    public Hsl interpolate(Hsl target, double coefficient) {
        if (coefficient > 1.0 | coefficient < 0.0) {
            throw new IllegalArgumentException("Coefficient have to be between 0.0 and 1.0");
        }
        return new Hsl(
                this.h + (target.h - this.h) * coefficient,
                this.s + (target.s - this.s) * coefficient,
                this.l + (target.l - this.l) * coefficient);
    }

    private double hue2rgb(double p, double q, double t) {
        if (t < 0) {
            t += 1;
        }
        if (t > 1) {
            t -= 1;
        }
        if (t < 1 / 6.0) {
            return p + (q - p) * 6 * t;
        }
        if (t < 1 / 2.0) {
            return q;
        }
        if (t < 2 / 3.0) {
            return p + (q - p) * (2 / 3.0 - t) * 6;
        }
        return p;
    }
}
