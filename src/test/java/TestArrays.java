import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Map;

import club.smarti.java.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SuppressWarnings({"ResultOfMethodCallIgnored", "ObviousNullCheck"})
public class TestArrays {

    @Test
    public void testCreate() {
        assertArrayEquals(new Integer[]{}, Arrays.create(Integer.class, 0));
        assertArrayEquals(new Integer[3], Arrays.create(Integer.class, 3));

        assertArrayEquals(new String[]{}, Arrays.create(String.class, 0));
        assertArrayEquals(new String[3], Arrays.create(String.class, 3));

        assertThrows(NullPointerException.class, () -> Arrays.create(null, 10));
        assertThrows(IllegalArgumentException.class, () -> Arrays.create(Integer.class, -1));
    }

    @Test
    public void testCopy() {
        assertArrayEquals(new int[0], Arrays.copy(new int[0]));
        assertArrayEquals(new Integer[0], Arrays.copy(new Integer[0]));
        assertArrayEquals(new String[0], Arrays.copy(new String[0]));

        assertArrayEquals(new boolean[]{true, false}, Arrays.copy(new boolean[]{true, false}));
        assertArrayEquals(new byte[]{1, 2, 3}, Arrays.copy(new byte[]{1, 2, 3}));
        assertArrayEquals(new char[]{'a', 'b', 'c'}, Arrays.copy(new char[]{'a', 'b', 'c'}));
        assertArrayEquals(new short[]{'a', 'b', 'c'}, Arrays.copy(new short[]{'a', 'b', 'c'}));
        assertArrayEquals(new int[]{1, 2, 3}, Arrays.copy(new int[]{1, 2, 3}));
        assertArrayEquals(new long[]{1L, 2L, 3L}, Arrays.copy(new long[]{1L, 2L, 3L}));
        assertArrayEquals(new float[]{1f, 2f, 3f}, Arrays.copy(new float[]{1f, 2f, 3f}));
        assertArrayEquals(new double[]{1.0, 2.0, 3.0}, Arrays.copy(new double[]{1.0, 2.0, 3.0}));

        assertArrayEquals(new Boolean[]{true, false}, Arrays.copy(new Boolean[]{true, false}));
        assertArrayEquals(new Byte[]{1, 2, 3}, Arrays.copy(new Byte[]{1, 2, 3}));
        assertArrayEquals(new Character[]{'a', 'b', 'c'}, Arrays.copy(new Character[]{'a', 'b', 'c'}));
        assertArrayEquals(new Short[]{'a', 'b', 'c'}, Arrays.copy(new Short[]{'a', 'b', 'c'}));
        assertArrayEquals(new Integer[]{1, 2, 3}, Arrays.copy(new Integer[]{1, 2, 3}));
        assertArrayEquals(new Long[]{1L, 2L, 3L}, Arrays.copy(new Long[]{1L, 2L, 3L}));
        assertArrayEquals(new Float[]{1f, 2f, 3f}, Arrays.copy(new Float[]{1f, 2f, 3f}));
        assertArrayEquals(new Double[]{1.0, 2.0, 3.0}, Arrays.copy(new Double[]{1.0, 2.0, 3.0}));

        assertArrayEquals(new String[]{"S1", "S2"}, Arrays.copy(new String[]{"S1", "S2"}));

        assertArrayEquals(new int[][]{{1, 2}, {3, 4}}, Arrays.copy(new int[][]{{1, 2}, {3, 4}}));

        // Check if the result array is not linked to the source
        int[] src = new int[]{1, 2, 3};
        int[] copy = Arrays.copy(src);
        assertArrayEquals(src, copy);
        src[0] = 5;
        assertEquals(1, copy[0]);

        assertThrows(NullPointerException.class, () -> Arrays.copy(null));
        assertThrows(IllegalArgumentException.class, () -> Arrays.copy("Not an array"));
        assertThrows(IllegalArgumentException.class, () -> Arrays.copy(0));
        assertDoesNotThrow(() -> Arrays.copy(new String[]{"S1", "S2"}));
    }

    @Test
    public void testSize() {
        assertEquals(0, Arrays.size(new boolean[0]));
        assertEquals(0, Arrays.size(new byte[0]));
        assertEquals(0, Arrays.size(new char[0]));
        assertEquals(0, Arrays.size(new short[0]));
        assertEquals(0, Arrays.size(new int[0]));
        assertEquals(0, Arrays.size(new long[0]));
        assertEquals(0, Arrays.size(new float[0]));
        assertEquals(0, Arrays.size(new double[0]));

        assertEquals(0, Arrays.size(new Boolean[0]));
        assertEquals(0, Arrays.size(new Byte[0]));
        assertEquals(0, Arrays.size(new Character[0]));
        assertEquals(0, Arrays.size(new Short[0]));
        assertEquals(0, Arrays.size(new Integer[0]));
        assertEquals(0, Arrays.size(new Long[0]));
        assertEquals(0, Arrays.size(new Float[0]));
        assertEquals(0, Arrays.size(new Double[0]));

        assertEquals(2, Arrays.size(new boolean[]{true, false}));
        assertEquals(3, Arrays.size(new byte[]{1, 2, 3}));
        assertEquals(3, Arrays.size(new char[]{'a', 'b', 'c'}));
        assertEquals(3, Arrays.size(new short[]{'a', 'b', 'c'}));
        assertEquals(3, Arrays.size(new int[]{1, 2, 3}));
        assertEquals(3, Arrays.size(new long[]{1L, 2L, 3L}));
        assertEquals(3, Arrays.size(new float[]{1f, 2f, 3f}));
        assertEquals(3, Arrays.size(new double[]{1.0, 2.0, 3.0}));

        assertEquals(2, Arrays.size(new Boolean[]{true, false}));
        assertEquals(3, Arrays.size(new Byte[]{1, 2, 3}));
        assertEquals(3, Arrays.size(new Character[]{'a', 'b', 'c'}));
        assertEquals(3, Arrays.size(new Short[]{'a', 'b', 'c'}));
        assertEquals(3, Arrays.size(new Integer[]{1, 2, 3}));
        assertEquals(3, Arrays.size(new Long[]{1L, 2L, 3L}));
        assertEquals(3, Arrays.size(new Float[]{1f, 2f, 3f}));
        assertEquals(3, Arrays.size(new Double[]{1.0, 2.0, 3.0}));

        assertEquals(0, Arrays.size(new String[0]));
        assertEquals(2, Arrays.size(new String[]{"S1", "S2"}));

        assertEquals(0, Arrays.size(new int[0][2]));
        assertEquals(2, Arrays.size(new int[][]{{1, 2, 3}, {4, 5, 6}}));

        assertThrows(NullPointerException.class, () -> Arrays.size(null));
        assertThrows(IllegalArgumentException.class, () -> Arrays.size("Not an array"));
        assertThrows(IllegalArgumentException.class, () -> Arrays.size(0));
    }

    @Test
    public void testNotEmpty() {
        assertFalse(Arrays.notEmpty(null));
        assertFalse(Arrays.notEmpty(new boolean[0]));
        assertFalse(Arrays.notEmpty(new int[0]));
        assertFalse(Arrays.notEmpty(new Integer[0]));
        assertFalse(Arrays.notEmpty(new String[0]));

        assertTrue(Arrays.notEmpty(new boolean[]{false}));
        assertTrue(Arrays.notEmpty(new boolean[]{true, false}));
        assertTrue(Arrays.notEmpty(new int[]{0}));
        assertTrue(Arrays.notEmpty(new int[]{1, 2, 3}));
        assertTrue(Arrays.notEmpty(new String[]{"S1"}));
    }

    @Test
    public void testGetItemType() {
        assertNull(Arrays.getItemType(int.class));
        assertNull(Arrays.getItemType(String.class));
        assertNull(Arrays.getItemType(ArrayList.class));

        assertEquals(boolean.class, Arrays.getItemType(boolean[].class));
        assertEquals(byte.class, Arrays.getItemType(byte[].class));
        assertEquals(short.class, Arrays.getItemType(short[].class));
        assertEquals(char.class, Arrays.getItemType(char[].class));
        assertEquals(int.class, Arrays.getItemType(int[].class));
        assertEquals(long.class, Arrays.getItemType(long[].class));
        assertEquals(float.class, Arrays.getItemType(float[].class));
        assertEquals(double.class, Arrays.getItemType(double[].class));
        assertEquals(Object.class, Arrays.getItemType(Object[].class));
        assertEquals(Boolean.class, Arrays.getItemType(Boolean[].class));
        assertEquals(Integer.class, Arrays.getItemType(Integer[].class));
        assertEquals(Double.class, Arrays.getItemType(Double[].class));
        assertEquals(ArrayList.class, Arrays.getItemType(ArrayList[].class));
        assertEquals(int[].class, Arrays.getItemType(int[][].class));
        assertEquals(Map[].class, Arrays.getItemType(Map[][].class));

        //noinspection InstantiatingObjectToGetClassObject
        assertEquals(int.class, Arrays.getItemType(new int[10].getClass()));
        //noinspection InstantiatingObjectToGetClassObject
        assertEquals(Integer.class, Arrays.getItemType(new Integer[10].getClass()));

        assertThrows(NullPointerException.class, () -> Arrays.getItemType(null));
    }

    @Test
    public void testConcat() {
        assertArrayEquals(new int[0], Arrays.concat(new int[0], new int[0]));
        assertArrayEquals(new int[]{100}, Arrays.concat(new int[0], new int[]{100}));
        assertArrayEquals(new int[]{100}, Arrays.concat(new int[]{100}, new int[0]));

        assertArrayEquals(new boolean[]{true, true, false, false}, Arrays.concat(new boolean[]{true, true}, new boolean[]{false, false}));
        assertArrayEquals(new byte[]{1, 2, 3, 4}, Arrays.concat(new byte[]{1, 2}, new byte[]{3, 4}));
        assertArrayEquals(new int[]{1, 2, 3, 4}, Arrays.concat(new int[]{1, 2}, new int[]{3, 4}));
        assertArrayEquals(new float[]{1f, 2f, 3f, 4f}, Arrays.concat(new float[]{1f, 2f}, new float[]{3f, 4f}));
        assertArrayEquals(new double[]{1.0, 2.0, 3.0, 4.0}, Arrays.concat(new double[]{1.0, 2.0}, new double[]{3.0, 4.0}));

        assertArrayEquals(new Character[]{1, 2, 3, 4}, Arrays.concat(new Character[]{1, 2}, new Character[]{3, 4}));
        assertArrayEquals(new Integer[]{1, 2, 3, 4}, Arrays.concat(new Integer[]{1, 2}, new Integer[]{3, 4}));
        assertArrayEquals(new Long[]{1L, 2L, 3L, 4L}, Arrays.concat(new Long[]{1L, 2L}, new Long[]{3L, 4L}));
        assertArrayEquals(new String[]{"S1", "S2", "S3"}, Arrays.concat(new String[]{"S1", "S2"}, new String[]{"S3"}));

        assertArrayEquals(new int[][]{{1, 2}, {3, 4}}, Arrays.concat(new int[][]{{1, 2}}, new int[][]{{3, 4}}));

        assertThrows(NullPointerException.class, () -> Arrays.concat(null, new int[5]));
        assertThrows(NullPointerException.class, () -> Arrays.concat(new int[5], null));
        assertThrows(IllegalArgumentException.class, () -> Arrays.concat(new int[5], new long[5]));
        assertThrows(IllegalArgumentException.class, () -> Arrays.concat(new int[5], new Integer[5]));
        assertThrows(IllegalArgumentException.class, () -> Arrays.concat(new Object[5], new Integer[5]));
    }

    @Test
    public void testIndexOf() {
        // Not found
        assertEquals(-1, Arrays.indexOf(new String[0], "Text"));
        assertEquals(-1, Arrays.indexOf(new Integer[]{1, 2, 3}, 100));
        assertEquals(-1, Arrays.indexOf(new Integer[]{1, 2, 3}, null));
        assertEquals(-1, Arrays.indexOf(new Integer[]{null, null, null}, 100));
        assertEquals(-1, Arrays.indexOf(new Integer[]{1, 2, 3}, "1"));

        // Item found
        assertEquals(0, Arrays.indexOf(new Integer[]{0}, 0));
        assertEquals(0, Arrays.indexOf(new Integer[]{1, 2}, 1));
        assertEquals(1, Arrays.indexOf(new Integer[]{1, 2}, 2));
        assertEquals(1, Arrays.indexOf(new Integer[]{1, 2, 3, 4, 5, 2, 2}, 2));
        assertEquals(0, Arrays.indexOf(new String[]{"S1", "S2", "S3"}, "S1"));
        assertEquals(2, Arrays.indexOf(new Object[]{0, 100, "S1"}, "S1"));
        assertEquals(1, Arrays.indexOf(new String[]{"S1", null, "S2"}, null));

        assertThrows(NullPointerException.class, () -> Arrays.indexOf(null, 100));
    }

    @Test
    public void testContains() {
        // Not found
        assertFalse(Arrays.contains(new String[0], "Text"));
        assertFalse(Arrays.contains(new Integer[]{1, 2, 3}, 100));
        assertFalse(Arrays.contains(new Integer[]{1, 2, 3}, null));
        assertFalse(Arrays.contains(new Integer[]{null, null, null}, 100));
        assertFalse(Arrays.contains(new Integer[]{1, 2, 3}, "1"));

        // Item found
        assertTrue(Arrays.contains(new Integer[]{0}, 0));
        assertTrue(Arrays.contains(new Integer[]{1, 2}, 1));
        assertTrue(Arrays.contains(new Integer[]{1, 2}, 2));
        assertTrue(Arrays.contains(new Integer[]{1, 2, 3, 4, 5, 2, 2}, 2));
        assertTrue(Arrays.contains(new String[]{"S1", "S2", "S3"}, "S1"));
        assertTrue(Arrays.contains(new Object[]{0, 100, "S1"}, "S1"));
        assertTrue(Arrays.contains(new String[]{"S1", null, "S2"}, null));

        assertThrows(NullPointerException.class, () -> Arrays.contains(null, 100));
    }
}