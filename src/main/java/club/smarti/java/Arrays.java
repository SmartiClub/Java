package club.smarti.java;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Array;

/**
 * Utils for operations wth different types of array
 * *
 * Advantages:
 * – support primitive data types
 * – embedded asserts
 * – avoid data casting
 * – avoid client code duplication
 */
@SuppressWarnings({"WeakerAccess", "PointlessBooleanExpression", "unused"})
public class Arrays {

    /**
     * Create a native array of the required type
     *
     * @param cls - array item class
     * @param size - size of the new array (it's allowed to be 0)
     * @return the new array
     */
    @NotNull
    @Contract(pure = true, value = "null, _ -> fail")
    public static <T> T[] create(Class<T> cls, int size) {
        if (cls == null) {
            throw new NullPointerException("Item class is null");
        }
        if (size < 0) {
            throw new IllegalArgumentException("Incorrect size: " + size);
        }
        //noinspection unchecked
        return (T[]) Array.newInstance(cls, size);
    }

    /**
     * Create a new native array of the required type filled with items
     * Note: primitive types are supported
     *
     * @param src - array to copy
     * @return copy of the array
     */
    @NotNull
    @Contract(pure = true, value = "null -> fail")
    public static <T> T copy(T src) {
        if (src == null) {
            throw new NullPointerException("Null source array");
        }
        Class<?> cls = getItemType(src.getClass());
        int size = size(src);
        Object copy = Array.newInstance(cls, size);
        copy(src, 0, copy, 0, size);
        //noinspection unchecked
        return (T) copy;
    }

    /**
     * Copy items from one array to another
     * Just a wrapper for system utility for consistent imports
     *
     * @param src - source array (can be primitive)
     * @param srcPos - starting position in the source array
     * @param dest - destination array (can be primitive)
     * @param destPos - v position in the destination array
     * @param size - the number of array elements to be copied
     */
    @Contract(value = "null, _, _, _, _ -> fail; _, _, null, _, _ -> fail")
    @SuppressWarnings({"SuspiciousSystemArraycopy"})
    public static void copy(Object src, int srcPos, Object dest, int destPos, int size) {
        if (src == null) {
            throw new NullPointerException("Null source array");
        }
        if (dest == null) {
            throw new NullPointerException("Null destination array");
        }
        if (size < 0) {
            throw new IllegalArgumentException("Incorrect array size: " + size);
        }
        System.arraycopy(src, srcPos, dest, destPos, size);
    }

    /**
     * Copy items from one array to another
     * Just a wrapper for system utility for consistent imports
     *
     * @param src - source array (can be primitive)
     * @param dest - destination array (can be primitive)
     */
    @Contract(value = "null, _ -> fail; _, null -> fail")
    @SuppressWarnings("SuspiciousSystemArraycopy")
    public static void copy(Object src, Object dest) {
        if (src == null) {
            throw new NullPointerException("Null source array");
        }
        if (dest == null) {
            throw new NullPointerException("Null destination array");
        }
        System.arraycopy(src, 0, dest, 0, size(src));
    }

    /**
     * Get size of the array
     *
     * @param array - an array of unknown type (can be primitive)
     */
    @Contract(pure = true, value = "null -> fail")
    public static int size(Object array) {
        if (array == null) {
            throw new NullPointerException("Null array");
        }
        Class<?> cls = array.getClass();
        if (Classes.isArray(cls) == false) {
            throw new IllegalArgumentException("Not an array: " + cls);
        }

        if (cls == boolean[].class) {
            return ((boolean[]) array).length;
        }
        else if (cls == byte[].class) {
            return ((byte[]) array).length;
        }
        else if (cls == short[].class) {
            return ((short[]) array).length;
        }
        else if (cls == char[].class) {
            return ((char[]) array).length;
        }
        else if (cls == int[].class) {
            return ((int[]) array).length;
        }
        else if (cls == long[].class) {
            return ((long[]) array).length;
        }
        else if (cls == float[].class) {
            return ((float[]) array).length;
        }
        else if (cls == double[].class) {
            return ((double[]) array).length;
        }
        else {
            return ((Object[]) array).length;
        }
    }

    /**
     * Check if the array is not empty
     *
     * @param array - an array to check
     */
    @Contract(pure = true)
    public static boolean notEmpty(@Nullable Object array) {
        if (array == null) {
            return false;
        }
        return size(array) > 0;
    }

    /**
     * Get class of item in the array
     * In contradistinction to {@link Class#getComponentType()} it supports primitive type arrays
     *
     * @param cls - class to check
     * @return item class or null if it can't be determined
     * @throws NullPointerException if the class is null
     */
    @Nullable
    @Contract(pure = true, value = "null -> fail")
    public static Class<?> getItemType(Class<?> cls) {
        if (cls == null) {
            throw new NullPointerException("Null class");
        }
        if (cls == boolean[].class) {
            return boolean.class;
        }
        else if (cls == byte[].class) {
            return byte.class;
        }
        else if (cls == short[].class) {
            return short.class;
        }
        else if (cls == char[].class) {
            return char.class;
        }
        else if (cls == int[].class) {
            return int.class;
        }
        else if (cls == long[].class) {
            return long.class;
        }
        else if (cls == float[].class) {
            return float.class;
        }
        else if (cls == double[].class) {
            return double.class;
        }
        return cls.getComponentType();
    }

    /**
     * Concatenate 2 arrays into a new one. It supports primitive data types.
     *
     * @param first - first array, can be empty
     * @param second - second array, can be empty
     * @return new array containing items from the both arrays
     */
    @NotNull
    @Contract(pure = true, value = "null, _ -> fail; _, null -> fail")
    public static <T> T concat(T first, T second) {
        if (first == null) {
            throw new NullPointerException("Null first part array");
        }
        if (second == null) {
            throw new NullPointerException("Null second part array");
        }
        if (first.getClass() != second.getClass()) {
            String error = String.format("Different array types: %s, %s", first.getClass(), second.getClass());
            throw new IllegalArgumentException(error);
        }

        Class<?> itemType = getItemType(first.getClass());
        int size1 = size(first);
        int size2 = size(second);
        //noinspection unchecked
        T result = (T) Array.newInstance(itemType, size1 + size2);

        copy(first, 0, result, 0, size1);
        copy(second, 0, result, size1, size2);
        return result;
    }

    /**
     * Find item in the array
     *
     * @param array - array to search
     * @param obj - item to search
     * @return index of the found item, -1 if not found
     */
    @Contract(pure = true, value = "null, _ -> fail")
    public static <T> int indexOf(T[] array, T obj) {
        if (array == null) {
            throw new NullPointerException("Null array");
        }
        for (int n = 0; n < array.length; n++) {
            T item = array[n];
            if (item == obj) {
                return n;
            }
            else if (item != null && item.equals(obj)) {
                return n;
            }
        }
        return -1;
    }

    /**
     * Check if the object is in the array
     *
     * @param array - array to search
     * @param obj - item to search
     * @return true if the item was found in the array, false otherwise
     */
    @Contract(pure = true, value = "null, _ -> fail")
    public static <T> boolean contains(T[] array, T obj) {
        if (array == null) {
            throw new NullPointerException("Null array");
        }
        return indexOf(array, obj) >= 0;
    }
}