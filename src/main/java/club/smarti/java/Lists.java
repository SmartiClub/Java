package club.smarti.java;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Utils for operations wth Lists
 * *
 * Advantages:
 * – embedded asserts
 * – avoid client code duplication
 */
@SuppressWarnings("PointlessBooleanExpression")
public class Lists {

    /**
     * Create a list from set of items
     * Note: it returns ArrayList in contradistinction to {@link java.util.Arrays#asList(Object[])}
     *
     * @param items - items of the new list
     * @return result list (can be empty, but never null)
     */
    @NotNull
    @Contract(pure = true, value = "null -> fail")
    public static <T> ArrayList<T> create(T... items) {
        if (items == null) {
            throw new NullPointerException("Item array is null");
        }
        ArrayList<T> list = new ArrayList<>(items.length);
        for (int n = 0; n < items.length; n++) {
            list.add(n, items[n]);
        }
        return list;
    }

    /**
     * Get item class
     * Note: all the list items are expected to be the same class
     *
     * @param list - non empty list
     * @return item class or null if it can't be determined
     */
    @Nullable
    @Contract(pure = true, value = "null -> fail")
    public static <T> Class<T> getItemType(List<T> list) {
        if (list == null) {
            throw new NullPointerException("List is null");
        }
        for (T item : list) {
            if (item != null) {
                //noinspection unchecked
                Class<T> cls = (Class<T>) item.getClass();
                if (cls != null) {
                    return cls;
                }
            }
        }
        return null;
    }

    /**
     * Convert list to array
     *
     * @param cls - item class
     * @param list - list to convert
     * @return result array (can be empty, but never null)
     */
    @NotNull
    @Contract(pure = true, value = "null, _ -> fail; _, null -> fail")
    public static <T> T[] toArray(Class<T> cls, List<T> list) {
        if (cls == null) {
            throw new NullPointerException("Item class is null");
        }
        if (list == null) {
            throw new NullPointerException("List is null");
        }
        T[] array = Arrays.create(cls, list.size());
        list.toArray(array);
        return array;
    }

    /**
     * Check if the list contains duplicates
     * Note:
     * Note: two null items are also considered duplicates
     *
     * @param data - dataset to check
     * @return true if at least one duplicate item was found
     */
    @Contract(pure = true, value = "null -> fail")
    public static <T> boolean hasDuplicates(Iterable<T> data) {
        if (data == null) {
            throw new NullPointerException("Dataset is null");
        }
        Set<T> set = new HashSet<>();
        for (T item : data) {
            if (set.add(item) == false) {
                return true;
            }
        }
        return false;
    }

    /**
     * Randomize the item order in the list
     *
     * @param list - list to shuffle
     */
    @Contract(value = "null -> fail")
    public static void shuffle(List<?> list) {
        if (list == null) {
            throw new NullPointerException("List is null");
        }
        Collections.shuffle(list, Randoms.generator());
    }
}
