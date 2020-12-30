package club.smarti.java;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

/**
 * Math operations
 * *
 * Advantages:
 * – single line helpers
 * – ...
 */
@SuppressWarnings("DuplicatedCode")
public final class Maths {

    /**
     * Get max value
     *
     * @param numbers - arrays of integer values
     * @return max value
     */
    @Contract(pure = true, value = "null -> fail")
    public static int max(int... numbers) {
        if (numbers == null) {
            throw new NullPointerException("Null array");
        }
        int count = numbers.length;
        if (count == 0) {
            throw new IllegalArgumentException("Empty array");
        }
        // Bypass
        if (count == 1) {
            return numbers[0];
        }
        // Bypass
        if (count == 2) {
            return Math.max(numbers[0], numbers[1]);
        }
        // Bypass
        else if (count == 3) {
            return Math.max(numbers[0], Math.max(numbers[1], numbers[2]));
        }
        else {
            int max = numbers[0];
            for (int n = 1; n < count; n++) {
                if (numbers[n] > max) {
                    max = numbers[n];
                }
            }
            return max;
        }
    }

    /**
     * Get max value
     *
     * @param numbers - arrays of float values
     * @return max value
     */
    @Contract(pure = true, value = "null -> fail")
    public static float max(float... numbers) {
        if (numbers == null) {
            throw new NullPointerException("Null array");
        }
        int count = numbers.length;
        if (count == 0) {
            throw new IllegalArgumentException("Empty array");
        }
        // Bypass
        if (count == 1) {
            return numbers[0];
        }
        // Bypass
        if (count == 2) {
            return Math.max(numbers[0], numbers[1]);
        }
        // Bypass
        else if (count == 3) {
            return Math.max(numbers[0], Math.max(numbers[1], numbers[2]));
        }
        else {
            float max = numbers[0];
            for (int n = 1; n < count; n++) {
                if (numbers[n] > max) {
                    max = numbers[n];
                }
            }
            return max;
        }
    }

    /**
     * Get min value
     *
     * @param numbers - arrays of integer values
     * @return min value
     */
    @Contract(pure = true, value = "null -> fail")
    public static int min(int... numbers) {
        if (numbers == null) {
            throw new NullPointerException("Null array");
        }
        int count = numbers.length;
        if (count == 0) {
            throw new IllegalArgumentException("Empty array");
        }
        // Bypass
        if (count == 1) {
            return numbers[0];
        }
        // Bypass
        if (count == 2) {
            return Math.min(numbers[0], numbers[1]);
        }
        // Bypass
        else if (count == 3) {
            return Math.min(numbers[0], Math.min(numbers[1], numbers[2]));
        }
        else {
            int min = numbers[0];
            for (int n = 1; n < count; n++) {
                if (numbers[n] < min) {
                    min = numbers[n];
                }
            }
            return min;
        }
    }

    /**
     * Get min value
     *
     * @param numbers - arrays of float values
     * @return min value
     */
    @Contract(pure = true, value = "null -> fail")
    public static float min(float... numbers) {
        if (numbers == null) {
            throw new NullPointerException("Null array");
        }
        int count = numbers.length;
        if (count == 0) {
            throw new IllegalArgumentException("Empty array");
        }
        // Bypass
        if (count == 1) {
            return numbers[0];
        }
        // Bypass
        if (count == 2) {
            return Math.min(numbers[0], numbers[1]);
        }
        // Bypass
        else if (count == 3) {
            return Math.min(numbers[0], Math.min(numbers[1], numbers[2]));
        }
        else {
            float min = numbers[0];
            for (int n = 1; n < count; n++) {
                if (numbers[n] < min) {
                    min = numbers[n];
                }
            }
            return min;
        }
    }

    /**
     * Round number to nearest value with the specified common denominator
     * Example: (4, 5) => 5
     * Example: (10, 3) => 9
     * Example: (-2, 3) => -3
     *
     * @param number - value to round
     * @param denominator - common denominator
     * @return the rounded value
     */
    @Contract(pure = true)
    public static int round(int number, int denominator) {
        if (denominator <= 0) {
            throw new IllegalArgumentException("Incorrect common denominator: " + denominator);
        }
        int ratio = number / denominator;
        int result = denominator * ratio;

        if (Math.abs(number - result) * 2 >= denominator) {
            if (number > 0) {
                if (result > Integer.MAX_VALUE - denominator) {
                    throw new IllegalArgumentException("Integer overflow");
                }
                result += denominator;
            }
            else if (number < 0) {
                if (result < Integer.MIN_VALUE + denominator) {
                    throw new IllegalArgumentException("Integer overflow");
                }
                result -= denominator;
            }
        }
        return result;
    }

    /**
     * Round number to nearest value with the specified common denominator
     * TODO: result overflow is not handled here
     * Example: (10, 3) => 9
     * Example: (10, 3.5) => 10.5
     * Example: (-2.5, 2) => -2
     *
     * @param number - value to round
     * @param denominator - common denominator
     * @return the rounded value
     */
    @Contract(pure = true)
    public static float round(float number, float denominator) {
        if (denominator <= 0) {
            throw new IllegalArgumentException("Incorrect common denominator: " + denominator);
        }
        return Math.round(number / denominator) * denominator;
    }

    /**
     * Logarithm with custom base
     * TODO: result overflow is not handled here
     *
     * @param value - a value
     * @param base - custom base
     * @return logarithm of the value
     */
    @Contract(pure = true)
    public static double log(double value, double base) {
        if (value == 0) {
            throw new IllegalArgumentException("Zero value");
        }
        // Bypass
        if (base == 10) {
            return Math.log10(value);
        }
        // Bypass
        if (base == Math.E) {
            return Math.log(value);
        }
        return Math.log(value) / Math.log(base);
    }

    /**
     * Logarithm with custom base
     * TODO: result overflow is not handled here
     *
     * @param value - a value
     * @return base 2 logarithm of the value
     */
    @Contract(pure = true)
    public static double log2(double value) {
        return log(value, 2);
    }

    /**
     * Switch between a few values
     * Note: values iteration order - 0, 1, ..., N-1, N, 0, 1, ...
     * Note: be aware of equal values in the array (TODO: track them automatically)
     *
     * @param current - current value
     * @param values - array of values, null value is accepted as well
     * @return next value from the array
     */
    @Nullable
    @SafeVarargs
    @Contract(pure = true)
    public static <T extends Number> T rotate(@Nullable T current, T... values) {
        if (values == null) {
            throw new NullPointerException("Null value array");
        }
        int count = values.length;
        if (count == 0) {
            throw new IllegalArgumentException("Empty value array");
        }
        else if (count == 1) {
            throw new IllegalArgumentException("There is only one accepted value");
        }

        int index;
        for (index = 0; index < count; index++) {
            T value = values[index];
            if (current == null && value == null ||
                    current != null && current.equals(value)) {
                break;
            }
        }
        if (index == count) {
            throw new IllegalArgumentException("The object isn't initialized yet");
        }
        index = (index + 1 < count ? index + 1 : 0);
        return values[index];
    }
}