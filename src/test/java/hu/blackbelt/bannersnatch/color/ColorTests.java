package hu.blackbelt.bannersnatch.color;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ColorTests {

    @Test
    public void testColorConversation() {
        assertRgb(new Hsl(0, 1.0, 0.5), 255, 0, 0);

        assertRgb(new Hsl(60.0 * (1.0 / 360.0), 1.0, 0.5), 255, 255, 0);
        assertRgb(new Hsl(120.0 * (1.0 / 360.0), 1.0, 0.5), 0, 255, 0);

        assertRgb(new Hsl(180.0 * (1.0 / 360.0), 1.0, 0.5), 0, 255, 255);
        assertRgb(new Hsl(180.0 * (1.0 / 360.0), 1.0, 0.5), 0, 255, 255);

        assertRgb(new Hsl(240.0 * (1.0 / 360.0), 1.0, 0.5), 0, 0, 255);
        assertRgb(new Hsl(360.0 * (1.0 / 360.0), 1.0, 0.5), 255, 0, 0);
        assertRgb(new Hsl(0, 0.5, 0.5), 191, 64, 64);
        assertRgb(new Hsl(0, 0, 0.5), 128, 128, 128);
        assertRgb(new Hsl(0, 1.0, 0.75), 255, 128, 128);
        assertRgb(new Hsl(0, 1.0, 0.5)
                .interpolate(new Hsl(60.0 * (1.0 / 360.0), 1.0, 0.5), 0.5), 255, 128, 0);

    }

    private void assertRgb(Hsl hsl, int r, int g, int b) {
        assertEquals(r, hsl.toRgb().r);
        assertEquals(g, hsl.toRgb().g);
        assertEquals(b, hsl.toRgb().b);

        assertEquals(r, hsl.toRgb().toHsl().toRgb().r);
        assertEquals(g, hsl.toRgb().toHsl().toRgb().g);
        assertEquals(b, hsl.toRgb().toHsl().toRgb().b);

    }
}