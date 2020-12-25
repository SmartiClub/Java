package club.smarti.java;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

/**
 * Java object operations
 * |
 * Advantages:
 * - there are no Java/Android version limitations;
 * - extra @annotation as IDE hints;
 * - the inverted api (notEqual);
 */
public class Objects {

    /**
     * Compare 2 objects
     *
     * @param a - first object to compare
     * @param b - second object to compare
     * @return true if they are equal (or both null), false otherwise
     * @see java.util.Objects#equals(Object, Object)
     */
    @Contract("null, null->true; !null, null->false; null, !null->false;")
    public static boolean equal(@Nullable Object a, @Nullable Object b) {
        //noinspection EqualsReplaceableByObjectsCall
        return (a == b || (a != null && a.equals(b)));
    }

    /**
     * Compare 2 objects
     *
     * @param a - first object to compare
     * @param b - second object to compare
     * @return true if they are not equal (or both null), false otherwise
     */
    @Contract("null, null -> false; !null, null -> true; null, !null -> true")
    public static boolean notEqual(@Nullable Object a, @Nullable Object b) {
        return !equal(a, b);
    }
}