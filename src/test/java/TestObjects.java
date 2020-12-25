import org.junit.jupiter.api.Test;

import club.smarti.java.Objects;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestObjects {

    private final static Object OBJ = new Object();
    private final static String STR = "string";

    @Test
    public void testEqual() {
        // With null
        assertTrue(Objects.equal(null, null));
        assertTrue(Objects.equal(OBJ, OBJ));
        assertFalse(Objects.equal(null, OBJ));
        assertFalse(Objects.equal(OBJ, null));

        // With primitives (autoboxing)
        assertTrue(Objects.equal(true, true));
        assertTrue(Objects.equal(false, false));
        assertTrue(Objects.equal(0, 0));
        assertTrue(Objects.equal(1, 1));
        assertFalse(Objects.equal(1, 1L)); // int vs. long
        assertFalse(Objects.equal(5f, 5.0)); // float vs. double

        // With strings
        assertTrue(Objects.equal(STR, STR));
        assertTrue(Objects.equal(STR, "string"));
        assertFalse(Objects.equal("String", "string"));

        // With data structures
        assertTrue(Objects.equal(new Data(10), new Data(10)));
        assertFalse(Objects.equal(new Data(10), new Data(20)));
    }

    @Test
    public void testNotEqual() {
        // With null
        assertFalse(Objects.notEqual(null, null));
        assertFalse(Objects.notEqual(OBJ, OBJ));
        assertTrue(Objects.notEqual(null, OBJ));
        assertTrue(Objects.notEqual(OBJ, null));

        // With primitives (autoboxing)
        assertFalse(Objects.notEqual(true, true));
        assertFalse(Objects.notEqual(false, false));
        assertFalse(Objects.notEqual(0, 0));
        assertFalse(Objects.notEqual(1, 1));
        assertTrue(Objects.notEqual(1, 1L)); // int vs. long
        assertTrue(Objects.notEqual(5f, 5.0)); // float vs. double

        // With strings
        assertFalse(Objects.notEqual(STR, STR));
        assertFalse(Objects.notEqual(STR, "string"));
        assertTrue(Objects.notEqual("String", "string"));

        // With data structures
        assertFalse(Objects.notEqual(new Data(10), new Data(10)));
        assertTrue(Objects.notEqual(new Data(10), new Data(20)));
    }

    /**
     * Test structure
     */
    private final static class Data {
        final int value;

        private Data(int value) {
            this.value = value;
        }

        @Override
        public int hashCode() {
            return value;
        }

        @Override
        public boolean equals(Object obj) {
            return (obj instanceof Data && value == ((Data) obj).value);
        }
    }
}