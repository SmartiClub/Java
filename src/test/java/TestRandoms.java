import org.junit.jupiter.api.Test;

import club.smarti.java.Randoms;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SuppressWarnings("SameParameterValue")
public class TestRandoms {

    private final static int RUN_COUNT = 100_000;

    @Test
    public void testInt() {
        testInt(1, RUN_COUNT);
        testInt(2, RUN_COUNT);
        testInt(100, RUN_COUNT);
        testInt(Integer.MAX_VALUE, RUN_COUNT);

        assertThrows(IllegalArgumentException.class, () -> Randoms.get(0));
        assertThrows(IllegalArgumentException.class, () -> Randoms.get(-1));
        assertThrows(IllegalArgumentException.class, () -> Randoms.get(Integer.MIN_VALUE));
    }

    private void testInt(final int max, final int runs) {
        for (int run = 0; run < runs; run++) {
            int rnd = Randoms.get(max);
            assertTrue(rnd >= 0 && rnd < max, String.valueOf(max));
        }
    }

    @Test
    public void testLong() {
        testLong(1L, RUN_COUNT);
        testLong(2L, RUN_COUNT);
        testLong(100L, RUN_COUNT);
        testLong(Long.MAX_VALUE, RUN_COUNT);

        assertThrows(IllegalArgumentException.class, () -> Randoms.get(0L));
        assertThrows(IllegalArgumentException.class, () -> Randoms.get(-1L));
        assertThrows(IllegalArgumentException.class, () -> Randoms.get(Long.MIN_VALUE));
    }

    private void testLong(final long max, final int runs) {
        for (int run = 0; run < runs; run++) {
            long rnd = Randoms.get(max);
            assertTrue(rnd >= 0 && rnd < max, String.valueOf(max));
        }
    }

    @Test
    public void testFloat() {
        testFloat(1f, RUN_COUNT);
        testFloat(2f, RUN_COUNT);
        testFloat(100f, RUN_COUNT);
        testFloat(1000_000f, RUN_COUNT);
        testFloat(Float.MAX_VALUE, RUN_COUNT);
        testFloat(0.1f, RUN_COUNT);
        testFloat(1.23e-10f, RUN_COUNT);
        testFloat(1.23e-40f, RUN_COUNT);

        assertThrows(IllegalArgumentException.class, () -> Randoms.get(0f));
        assertThrows(IllegalArgumentException.class, () -> Randoms.get(-1f));
    }

    private void testFloat(final float max, final int runs) {
        for (int run = 0; run < runs; run++) {
            float rnd = Randoms.get(max);
            assertTrue(rnd >= 0 && rnd < max, String.valueOf(rnd));
        }
    }

    @Test
    public void testDouble() {
        testDouble(1.0, RUN_COUNT);
        testDouble(2.0, RUN_COUNT);
        testDouble(100.0, RUN_COUNT);
        testDouble(1000_000f, RUN_COUNT);
        testDouble(Double.MAX_VALUE, RUN_COUNT);
        testDouble(0.1, RUN_COUNT);
        testDouble(1.23e-10, RUN_COUNT);
        testDouble(1.23e-40, RUN_COUNT);

        assertThrows(IllegalArgumentException.class, () -> Randoms.get(0.0));
        assertThrows(IllegalArgumentException.class, () -> Randoms.get(-1.0));
    }

    private void testDouble(final double max, final int runs) {
        for (int run = 0; run < runs; run++) {
            double rnd = Randoms.get(max);
            assertTrue(rnd >= 0 && rnd < max, String.valueOf(max));
        }
    }

    @Test
    public void testBoolean() {
        for (int run = 0; run < RUN_COUNT; run++) {
            boolean bool = Randoms.getBoolean();
        }
    }

    @Test
    public void testSign() {
        for (int run = 0; run < RUN_COUNT; run++) {
            int sign = Randoms.sign();
            assertTrue(sign == 1 || sign == -1, String.valueOf(sign));
        }
    }
}