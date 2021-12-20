package hu.blackbelt.bannersnatch.color;

/**
 * Represents an RGB color. It contains red green and blue component which
 * values range are 0-255.
 */
public class Rgb {
    public int r, g, b;

    /**
     * Create RGB class
     * @param r
     * @param g
     * @param b
     */
    public Rgb(int r, int g, int b) {
        if (r > 255 | r < 0 | g > 255 | g < 0 | b > 255 | b < 0) {
            throw new IllegalArgumentException("r,b,b value have to be between 0 and 255.");
        }
        this.r = r;
        this.g = g;
        this.b = b;
    }

    /**
     * Create RGB with 24bit RGB values.
     * @param val
     */
    public Rgb(int val) {
        this.r = (val >> 16) & 0xff;
        this.g = (val >> 8) & 0xff;
        this.b = val & 0xff;
    }

    /**
     * Convert RGB value to 24bit RGB integer
     * @return
     */
    int toInt() {
        return (r << 16) + (g << 8) + b;
    }

    /**
     * Convert RGB value to HSL color.
     * @return
     */
    public Hsl toHsl() {
        // Get RGB values in the range 0 - 1
        double r = this.r / 255.0;
        double g = this.g / 255.0;
        double b = this.b / 255.0;

        // Minimum and Maximum RGB values are used in the HSL calculations
        double min = Math.min(r, Math.min(g, b));
        double max = Math.max(r, Math.max(g, b));

        // Calculate the Hue
        double h = 0;

        if (max == min) {
            h = 0;
        } else if (max == r) {
            h = ((60 * (g - b) / (max - min)) + 360) % 360;
        } else if (max == g) {
            h = (60 * (b - r) / (max - min)) + 120.0;
        } else if (max == b) {
            h = (60 * (r - g) / (max - min)) + 240;
        }

        // Calculate the Luminance
        double l = (max + min) / 2;

        // Calculate the Saturation
        double s = 0;

        if (max == min) {
            s = 0;
        } else if (l <= .5f) {
            s = (max - min) / (max + min);
        } else {
            s = (max - min) / (2 - max - min);
        }
        return new Hsl(h / 360.0, s, l);
    }

    /**
     * Convert RGB value to terminal color
     * @return Terminal code value
     */
    public TermColor toTerminalColor() {
        return new TermColor(this);
    }

    /**
     * Interpolate current value and the target color
     * @param target The target RGB value which ic achived when coefficient is 1.0.
     * @param coefficient
     * @return
     */
    public Rgb interpolate(Rgb target, double coefficient) {
        return new Rgb(
                (int) ((double) this.r + ((double) target.r - (double) this.r) * coefficient),
                (int) ((double) this.g + ((double) target.g - (double) this.g) * coefficient),
                (int) ((double) this.b + ((double) target.b - (double) this.b) * coefficient));
    }

    /**
     * Convert RGB value to grayscale RGB value.
     * @return
     */
    public Rgb toGrayscale() {
        int gray = ((int) (0.21f * (float) r + 0.72f * (float) g + 0.07f * (float) b)) & 0xff;
        return new Rgb(gray, gray, gray);
    }

}
