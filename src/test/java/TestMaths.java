import org.junit.jupiter.api.Test;

import club.smarti.java.Maths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SuppressWarnings({"ResultOfMethodCallIgnored", "Convert2MethodRef"})
public class TestMaths {

    @Test
    public void testMaxInteger() {
        assertEquals(0, Maths.max(0));
        assertEquals(1, Maths.max(1));
        assertEquals(-1, Maths.max(-1));
        assertEquals(Integer.MAX_VALUE, Maths.max(Integer.MAX_VALUE));
        assertEquals(Integer.MIN_VALUE, Maths.max(Integer.MIN_VALUE));

        assertEquals(0, Maths.max(0, 0));
        assertEquals(1, Maths.max(1, 1));
        assertEquals(2, Maths.max(1, 2));
        assertEquals(1, Maths.max(1, -1));
        assertEquals(-1, Maths.max(-1, -1));
        assertEquals(-1, Maths.max(-2, -1));
        assertEquals(Integer.MAX_VALUE, Maths.max(Integer.MIN_VALUE, Integer.MAX_VALUE));

        assertEquals(0, Maths.max(0, 0, 0));
        assertEquals(1, Maths.max(1, 1, 1));
        assertEquals(3, Maths.max(1, 2, 3));
        assertEquals(1, Maths.max(1, -1, 0));
        assertEquals(-1, Maths.max(-1, -1, -1));
        assertEquals(-1, Maths.max(-5, -2, -1));
        assertEquals(Integer.MAX_VALUE, Maths.max(Integer.MIN_VALUE, 0, Integer.MAX_VALUE));

        assertEquals(0, Maths.max(0, 0, 0, 0, 0));
        assertEquals(5, Maths.max(1, 2, 3, 4, 5));
        assertEquals(-1, Maths.max(-1, -2, -3, -4, -5));
        assertEquals(200, Maths.max(-200, -100, 0, 100, 200));

        assertThrows(NullPointerException.class, () -> Maths.max((int[]) null));
        assertThrows(IllegalArgumentException.class, () -> Maths.max());
    }

    @Test
    public void testMaxFloat() {
        assertEquals(0f, Maths.max(0f));
        assertEquals(1f, Maths.max(1f));
        assertEquals(0.5f, Maths.max(0.5f));
        assertEquals(-1f, Maths.max(-1f));
        assertEquals(Float.MAX_VALUE, Maths.max(Float.MAX_VALUE));
        assertEquals(Float.MIN_VALUE, Maths.max(Float.MIN_VALUE));

        assertEquals(0f, Maths.max(0f, 0f));
        assertEquals(1f, Maths.max(1f, 1f));
        assertEquals(2.5f, Maths.max(1.5f, 2.5f));
        assertEquals(1f, Maths.max(1f, -1f));
        assertEquals(0.01f, Maths.max(0.01f, -0.01f));
        assertEquals(-1f, Maths.max(-1f, -1f));
        assertEquals(-1f, Maths.max(-2f, -1f));
        assertEquals(Float.MAX_VALUE, Maths.max(Float.MIN_VALUE, Float.MAX_VALUE));

        assertEquals(0f, Maths.max(0f, 0f, 0f));
        assertEquals(1f, Maths.max(1f, 1f, 1f));
        assertEquals(3f, Maths.max(1f, 2f, 3f));
        assertEquals(1f, Maths.max(1f, -1f, 0f));
        assertEquals(-1f, Maths.max(-1f, -1f, -1f));
        assertEquals(-1f, Maths.max(-5f, -2f, -1f));
        assertEquals(Float.MAX_VALUE, Maths.max(Float.MIN_VALUE, 0, Float.MAX_VALUE));

        assertEquals(0f, Maths.max(0f, 0f, 0f, 0f, 0f));
        assertEquals(0.5f, Maths.max(0.1f, 0.2f, 0.3f, 0.4f, 0.5f));
        assertEquals(-1f, Maths.max(-1f, -2f, -3f, -4f, -5f));
        assertEquals(200f, Maths.max(-200f, -100f, 0, 100f, 200f));

        assertThrows(NullPointerException.class, () -> Maths.max((float[]) null));
    }

    @Test
    public void testMinInteger() {
        assertEquals(0, Maths.min(0));
        assertEquals(1, Maths.min(1));
        assertEquals(-1, Maths.min(-1));
        assertEquals(Integer.MAX_VALUE, Maths.min(Integer.MAX_VALUE));
        assertEquals(Integer.MIN_VALUE, Maths.min(Integer.MIN_VALUE));

        assertEquals(0, Maths.min(0, 0));
        assertEquals(1, Maths.min(1, 1));
        assertEquals(1, Maths.min(1, 2));
        assertEquals(-1, Maths.min(1, -1));
        assertEquals(-1, Maths.min(-1, -1));
        assertEquals(-2, Maths.min(-2, -1));
        assertEquals(Integer.MIN_VALUE, Maths.min(Integer.MIN_VALUE, Integer.MAX_VALUE));

        assertEquals(0, Maths.min(0, 0, 0));
        assertEquals(1, Maths.min(1, 1, 1));
        assertEquals(1, Maths.min(1, 2, 3));
        assertEquals(-1, Maths.min(1, -1, 0));
        assertEquals(-1, Maths.min(-1, -1, -1));
        assertEquals(-5, Maths.min(-5, -2, -1));
        assertEquals(Integer.MIN_VALUE, Maths.min(Integer.MIN_VALUE, 0, Integer.MAX_VALUE));

        assertEquals(0, Maths.min(0, 0, 0, 0, 0));
        assertEquals(1, Maths.min(1, 2, 3, 4, 5));
        assertEquals(-5, Maths.min(-1, -2, -3, -4, -5));
        assertEquals(-200, Maths.min(-200, -100, 0, 100, 200));

        assertThrows(NullPointerException.class, () -> Maths.min((int[]) null));
        assertThrows(IllegalArgumentException.class, () -> Maths.min());
    }

    @Test
    public void testMinFloat() {
        assertEquals(0f, Maths.min(0f));
        assertEquals(1f, Maths.min(1f));
        assertEquals(0.5f, Maths.min(0.5f));
        assertEquals(-1f, Maths.min(-1f));
        assertEquals(Float.MAX_VALUE, Maths.min(Float.MAX_VALUE));
        assertEquals(Float.MIN_VALUE, Maths.min(Float.MIN_VALUE));

        assertEquals(0f, Maths.min(0f, 0f));
        assertEquals(1f, Maths.min(1f, 1f));
        assertEquals(1.5f, Maths.min(1.5f, 2.5f));
        assertEquals(-1f, Maths.min(1f, -1f));
        assertEquals(-0.01f, Maths.min(0.01f, -0.01f));
        assertEquals(-1f, Maths.min(-1f, -1f));
        assertEquals(-2f, Maths.min(-2f, -1f));
        assertEquals(Float.MIN_VALUE, Maths.min(Float.MIN_VALUE, Float.MAX_VALUE));

        assertEquals(0f, Maths.min(0f, 0f, 0f));
        assertEquals(1f, Maths.min(1f, 1f, 1f));
        assertEquals(1f, Maths.min(1f, 2f, 3f));
        assertEquals(-1f, Maths.min(1f, -1f, 0f));
        assertEquals(-1f, Maths.min(-1f, -1f, -1f));
        assertEquals(-5f, Maths.min(-5f, -2f, -1f));
        assertEquals(0, Maths.min(Float.MIN_VALUE, 0, Float.MAX_VALUE));

        assertEquals(0f, Maths.min(0f, 0f, 0f, 0f, 0f));
        assertEquals(0.1f, Maths.min(0.1f, 0.2f, 0.3f, 0.4f, 0.5f));
        assertEquals(-5f, Maths.min(-1f, -2f, -3f, -4f, -5f));
        assertEquals(-200f, Maths.min(-200f, -100f, 0, 100f, 200f));

        assertThrows(NullPointerException.class, () -> Maths.min((float[]) null));
    }

    @Test
    public void testRoundInteger() {
        assertEquals(0, Maths.round(0, 1));
        assertEquals(0, Maths.round(0, 2));
        assertEquals(1, Maths.round(1, 1));
        assertEquals(2, Maths.round(1, 2));
        assertEquals(8, Maths.round(7, 4));
        assertEquals(8, Maths.round(6, 4));
        assertEquals(9, Maths.round(10, 3));
        assertEquals(12, Maths.round(11, 3));
        assertEquals(-1, Maths.round(-1, 1));
        assertEquals(-3, Maths.round(-2, 3));
        assertEquals(-3, Maths.round(-4, 3));
        assertEquals(-8, Maths.round(-7, 4));
        assertEquals(-8, Maths.round(-6, 4));
        assertEquals(-9, Maths.round(-10, 3));
        assertEquals(2147483645, Maths.round(Integer.MAX_VALUE, 5));
        assertEquals(-2147483646, Maths.round(Integer.MIN_VALUE, 7));

        assertThrows(IllegalArgumentException.class, () -> Maths.round(10, 0));
        assertThrows(IllegalArgumentException.class, () -> Maths.round(10, -2));
        assertThrows(IllegalArgumentException.class, () -> Maths.round(Integer.MAX_VALUE, 10)); // overflow
        assertThrows(IllegalArgumentException.class, () -> Maths.round(Integer.MIN_VALUE, 10)); // overflow
    }

    @Test
    public void testRoundFloat() {
        assertEquals(0f, Maths.round(0, 1f));
        assertEquals(0f, Maths.round(0, 2f));
        assertEquals(1f, Maths.round(1f, 1f));
        assertEquals(2f, Maths.round(1f, 2f));
        assertEquals(2f, Maths.round(2.5f, 2f));
        assertEquals(1f, Maths.round(1.1f, 0.5f));
        assertEquals(1.5f, Maths.round(1.3f, 0.5f));
        assertEquals(10.5f, Maths.round(10f, 3.5f));
        assertEquals(0, Maths.round(-0.49f, 1f));
        assertEquals(-1f, Maths.round(-1f, 1f));
        assertEquals(-3f, Maths.round(-2f, 3f));
        assertEquals(-1f, Maths.round(-1.2f, 1f));
        assertEquals(-1f, Maths.round(-0.8f, 1f));
        assertEquals(-10.5f, Maths.round(-10f, 3.5f));

        assertThrows(IllegalArgumentException.class, () -> Maths.round(10f, 0));
        assertThrows(IllegalArgumentException.class, () -> Maths.round(10f, -2f));
    }

    @Test
    public void tesLog() {
        assertEquals(0.0, Maths.log(1.0, 100.0));
        assertEquals(3.0, Maths.log(8.0, 2.0));
        assertEquals(-2.0, Maths.log(0.04, 5.0));

        assertThrows(IllegalArgumentException.class, () -> Maths.log(0.0, 5.0));
    }

    @Test
    public void tesLog2() {
        assertEquals(0.0, Maths.log2(1.0));
        assertEquals(3.0, Maths.log2(8.0));
        assertEquals(-1.0, Maths.log2(0.5));

        assertThrows(IllegalArgumentException.class, () -> Maths.log2(0.0));
    }

    @Test
    public void tesRotate() {
        assertEquals(2, Maths.rotate(1, 1, 2));
        assertEquals(1, Maths.rotate(2, 1, 2));
        assertEquals(2, Maths.rotate(1, 1, 2, 3));
        assertEquals(3, Maths.rotate(2, 1, 2, 3));
        assertEquals(1, Maths.rotate(3, 1, 2, 3));
        assertEquals(Long.MAX_VALUE, Maths.rotate(Long.MIN_VALUE, Long.MIN_VALUE, Long.MAX_VALUE));

        assertEquals(2, Maths.rotate(null, null, 2, 3));
        assertEquals(3, Maths.rotate(2, null, 2, 3));
        assertNull(Maths.rotate(3, null, 2, 3));

        assertThrows(NullPointerException.class, () -> Maths.rotate(0, (Integer[]) null));
        assertThrows(IllegalArgumentException.class, () -> Maths.rotate(0));
        assertThrows(IllegalArgumentException.class, () -> Maths.rotate(0, 1));
        assertThrows(IllegalArgumentException.class, () -> Maths.rotate(0, 1, 2, 3));
    }
}