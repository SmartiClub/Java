import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import club.smarti.java.Lists;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestLists {

    @Test
    public void testCreate() {
        ArrayList<?> empty = Lists.create();
        assertEquals(0, empty.size());

        ArrayList<?> nullList = Lists.create((Object) null);
        assertEquals(1, nullList.size());
        assertNull(nullList.get(0));

        ArrayList<?> intList = Lists.create(-1, 0, 1);
        assertEquals(3, intList.size());
        assertEquals(-1, intList.get(0));
        assertEquals(0, intList.get(1));
        assertEquals(1, intList.get(2));

        ArrayList<?> mixedList = Lists.create(100, 50.0, "Text");
        assertEquals(3, mixedList.size());
        assertEquals(100, mixedList.get(0));
        assertEquals(50.0, mixedList.get(1));
        assertEquals("Text", mixedList.get(2));

        assertThrows(NullPointerException.class, () -> Lists.create((Object[]) null));
    }

    @Test
    public void testGetItemType() {
        assertNull(Lists.getItemType(new ArrayList<>()));
        assertNull(Lists.getItemType(Lists.create(null, null, null)));

        assertEquals(Boolean.class, Lists.getItemType(Lists.create(true, false)));
        assertEquals(Short.class, Lists.getItemType(Lists.create((short) 1, (short) 2, (short) 3)));
        assertEquals(Character.class, Lists.getItemType(Lists.create('a', 'b', 'c')));
        assertEquals(Short.class, Lists.getItemType(Lists.create((short) 1, (short) 2, (short) 3)));
        assertEquals(Integer.class, Lists.getItemType(Lists.create(1, 2, 3)));
        assertEquals(Long.class, Lists.getItemType(Lists.create(1L, 2L, 3L)));
        assertEquals(Float.class, Lists.getItemType(Lists.create(1f, 2f, 3f)));
        assertEquals(Double.class, Lists.getItemType(Lists.create(1.0, 2.0, 3.0)));

        assertEquals(Object.class, Lists.getItemType(Lists.create(new Object())));
        assertEquals(String.class, Lists.getItemType(Lists.create("S1", "S2", "S3")));

        assertEquals(int[].class, Lists.getItemType(Lists.create((Object) new int[5])));
        assertEquals(String[].class, Lists.getItemType(Lists.create((Object) new String[5])));

        // Multi class item list is not supported, so the first item is only checked
        assertEquals(Integer.class, Lists.getItemType(Lists.create(1, 2.0, "Text")));
        assertEquals(Double.class, Lists.getItemType(Lists.create(null, 2.0, "Text")));
        assertEquals(String.class, Lists.getItemType(Lists.create(null, null, "Text")));

        assertThrows(NullPointerException.class, () -> Lists.getItemType(null));
    }

    @Test
    public void testToArray() {
        assertArrayEquals(new Integer[]{}, Lists.toArray(Integer.class, Lists.create()));
        assertArrayEquals(new Integer[]{null}, Lists.toArray(Integer.class, Lists.create((Integer) null)));
        assertArrayEquals(new Integer[]{1, 2, 3}, Lists.toArray(Integer.class, Lists.create(1, 2, 3)));

        assertArrayEquals(new Boolean[]{true, false}, Lists.toArray(Boolean.class, Lists.create(true, false)));
        assertArrayEquals(new String[]{"S1", "S2"}, Lists.toArray(String.class, Lists.create("S1", "S2")));

        assertThrows(NullPointerException.class, () -> Lists.toArray(null, new ArrayList<>()));
        assertThrows(NullPointerException.class, () -> Lists.toArray(Integer.class, null));
    }

    @Test
    public void testHasDuplicates() {
        assertFalse(Lists.hasDuplicates(Lists.create()));

        assertFalse(Lists.hasDuplicates(Lists.create((Object) null)));
        assertTrue(Lists.hasDuplicates(Lists.create(null, null)));
        assertFalse(Lists.hasDuplicates(Lists.create(null, "Text")));

        assertTrue(Lists.hasDuplicates(Lists.create(0, 0, 0)));
        assertFalse(Lists.hasDuplicates(Lists.create(1, 2, 3)));
        assertTrue(Lists.hasDuplicates(Lists.create(1, 2, 3, 1)));

        assertFalse(Lists.hasDuplicates(Lists.create("S1", "S2", "S3")));
        assertTrue(Lists.hasDuplicates(Lists.create("S1", "S2", "S2", "S3")));

        // Same hash but not equal
        assertFalse(Lists.hasDuplicates(Lists.create(2603341, "Text")));
        assertFalse(Lists.hasDuplicates(Lists.create("AaAaAa", "BBBBBB")));

        assertThrows(NullPointerException.class, () -> Lists.hasDuplicates(null));
    }

    @Test
    public void testShuffle() {
        ArrayList<Integer> empty = new ArrayList<>();
        Lists.shuffle(empty);
        assertEquals(0, empty.size());

        ArrayList<Integer> singleItemList = Lists.create(5);
        Lists.shuffle(singleItemList);
        assertEquals(1, singleItemList.size());
        assertEquals(5, singleItemList.get(0));

        ArrayList<Integer> intList = Lists.create(1, 2, 3);
        Lists.shuffle(intList);
        assertEquals(3, intList.size());
        assertTrue(intList.contains(1));
        assertTrue(intList.contains(2));
        assertTrue(intList.contains(3));

        ArrayList<Float> floatList = Lists.create(1f, 2f, 3f, 4f, 5f, 6f, 7f, 8f, 9f, 10f); // 10! variants (3628800, big enough)
        ArrayList<Float> floatCopyList = Lists.create(1f, 2f, 3f, 4f, 5f, 6f, 7f, 8f, 9f, 10f);
        Lists.shuffle(floatList);
        assertEquals(10, floatList.size());
        assertTrue(floatList.contains(1f));
        assertTrue(floatList.contains(2f));
        assertTrue(floatList.contains(3f));
        assertTrue(floatList.contains(4f));
        assertTrue(floatList.contains(5f));
        assertTrue(floatList.contains(6f));
        assertTrue(floatList.contains(7f));
        assertTrue(floatList.contains(8f));
        assertTrue(floatList.contains(9f));
        assertTrue(floatList.contains(10f));
        boolean sameOrder = true;
        for (int n = 0; n < 10 && sameOrder; n++) {
            sameOrder = floatList.get(n).equals(floatCopyList.get(n));
        }
        assertFalse(sameOrder);

        assertThrows(NullPointerException.class, () -> Lists.shuffle(null));
    }
}