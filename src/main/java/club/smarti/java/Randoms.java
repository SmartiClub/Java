package club.smarti.java;

import java.util.Random;

/**
 * Random data generators
 * Note: generated values are not cryptographically secure
 * |
 * Advantages:
 * - support value range
 * - some embedded asserts
 * - reused Random generator
 *
 * @see java.util.concurrent.ThreadLocalRandom
 */
public final class Randoms {

    /**
     * Note: "Instances of java.util.Random are threadsafe" (C) Java 7, Oracle
     */
    private final static Random RND = new Random();

    /**
     * Get random integer value [0, max), i.e. 0 <= x < max
     *
     * @param max value (exclusive)
     * @return next pseudorandom value
     */
    public static int get(int max) {
        if (max <= 0) {
            throw new IllegalArgumentException("Incorrect range: " + max);
        }
        return RND.nextInt(max);
    }

    /**
     * Get random long value [0, max), i.e. 0 <= x < max
     *
     * @param max value (exclusive)
     * @return next pseudorandom value
     */
    public static long get(long max) {
        if (max <= 0) {
            throw new IllegalArgumentException("Incorrect range: " + max);
        }
        if (max <= Integer.MAX_VALUE) {
            return get((int) max);
        }
        long rnd = Math.abs(RND.nextLong());
        return rnd % max;
    }

    /**
     * Get random float value [0, max), i.e. 0 <= x < max
     *
     * @param max value (exclusive)
     * @return next pseudorandom value
     */
    public static float get(float max) {
        if (max <= 0) {
            throw new IllegalArgumentException("Incorrect range: " + max);
        }
        float rnd = RND.nextFloat() * max;
        if (rnd == max) {
            // e.g. 0.9999987f * 1.23e-40f
            return 0f;
        }
        return rnd;
    }

    /**
     * Get random double value [0, max), i.e. 0 <= x < max
     *
     * @param max value (exclusive)
     * @return next pseudorandom value
     */
    public static double get(double max) {
        if (max <= 0) {
            throw new IllegalArgumentException("Incorrect range: " + max);
        }
        double rnd = RND.nextDouble() * max;
        if (rnd == max) {
            return 0f;
        }
        return rnd;
    }

    /**
     * Get random boolean value
     *
     * @return next pseudorandom value
     */
    public static boolean getBoolean() {
        return RND.nextBoolean();
    }

    /**
     * Get random sign (1 or -1)
     *
     * @return pseudorandom value
     */
    public static int sign() {
        return get(2) == 1 ? 1 : -1;
    }
}