import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import club.smarti.java.Strings;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SuppressWarnings({"ConstantConditions", "ResultOfMethodCallIgnored"})
public class TestStrings {

    @Test
    public void testNotEmpty() {
        assertTrue(Strings.notEmpty(" "));
        assertTrue(Strings.notEmpty("A"));
        assertTrue(Strings.notEmpty("Test string"));

        assertFalse(Strings.notEmpty(null));
        assertFalse(Strings.notEmpty(""));
    }

    @Test
    public void testStartsWithUpperCase() {
        assertTrue(Strings.startsWithUpperCase("A"));
        assertTrue(Strings.startsWithUpperCase("AAA"));
        assertTrue(Strings.startsWithUpperCase("Aaa aaa"));
        assertTrue(Strings.startsWithUpperCase("Écrit"));  // non-English
        assertTrue(Strings.startsWithUpperCase("Тест"));  // non-English

        assertFalse(Strings.startsWithUpperCase(""));
        assertFalse(Strings.startsWithUpperCase(" "));
        assertFalse(Strings.startsWithUpperCase("aA"));
        assertFalse(Strings.startsWithUpperCase("word"));
        assertFalse(Strings.startsWithUpperCase("écrit"));  // non-English
        assertFalse(Strings.startsWithUpperCase("тест"));  // non-English
        assertFalse(Strings.startsWithUpperCase("?"));  // special symbols
        assertFalse(Strings.startsWithUpperCase("."));  // special symbols

        assertThrows(NullPointerException.class, () -> Strings.startsWithUpperCase(null));
    }

    @Test
    public void testStartsWithLowerCase() {
        assertTrue(Strings.startsWithLowerCase("a"));
        assertTrue(Strings.startsWithLowerCase("aaa"));
        assertTrue(Strings.startsWithLowerCase("aAAA"));
        assertTrue(Strings.startsWithLowerCase("écrit"));  // non-English
        assertTrue(Strings.startsWithLowerCase("тест"));  // non-English

        assertFalse(Strings.startsWithLowerCase(""));
        assertFalse(Strings.startsWithLowerCase(" "));
        assertFalse(Strings.startsWithLowerCase("A"));
        assertFalse(Strings.startsWithLowerCase("AAA"));
        assertFalse(Strings.startsWithLowerCase("Aaa"));
        assertFalse(Strings.startsWithLowerCase("Écrit"));  // non-English
        assertFalse(Strings.startsWithLowerCase("Тест"));  // non-English
        assertFalse(Strings.startsWithLowerCase("?"));  // special symbols
        assertFalse(Strings.startsWithLowerCase("."));  // special symbols

        assertThrows(NullPointerException.class, () -> Strings.startsWithLowerCase(null));
    }

    @Test
    public void testCountChar() {
        assertEquals(0, Strings.count("", 'A'));
        assertEquals(0, Strings.count("  ", 'A'));
        assertEquals(0, Strings.count("ABC", 'a'));  // not found
        assertEquals(1, Strings.count("ABC", 'A'));  // found once
        assertEquals(2, Strings.count("ABC ABC", 'A'));  // multiple findings
        assertEquals(1, Strings.count("Écrit", 'É')); // non-English
        assertEquals(1, Strings.count("Word 123", '2')); // in the middle
        assertEquals(7, Strings.count("???????", '?'));  // 100% filling
        assertEquals(2, Strings.count("1\t2\t3", '\t'));  // special symbols
        assertEquals(0, Strings.count("Some phrase", (char) 0));  // special symbols

        assertThrows(NullPointerException.class, () -> Strings.count(null, 'x'));
    }

    @Test
    public void testCountString() {
        assertEquals(0, Strings.count("", "A"));
        assertEquals(0, Strings.count("  ", "A"));
        assertEquals(0, Strings.count("ABC", "a"));  // not found
        assertEquals(1, Strings.count("ABC", "A"));  // found once
        assertEquals(2, Strings.count("ABC ABC", "A"));  // multiple findings
        assertEquals(1, Strings.count("Écrit", "É")); // non-English
        assertEquals(1, Strings.count("Word 123", "2")); // in the middle
        assertEquals(7, Strings.count("???????", "?"));  // 100% filling
        assertEquals(2, Strings.count("1\t2\t3", "\t"));  // special symbols

        assertEquals(0, Strings.count("ABC", "AA"));  // not found
        assertEquals(1, Strings.count("xABCx", "ABC"));  // found once
        assertEquals(2, Strings.count("ABC ABC", "ABC"));  // multiple findings
        assertEquals(2, Strings.count("ABCABC", "ABC"));  // 100% filling
        assertEquals(0, Strings.count("Écrit", "é"));  // non-English
        assertEquals(3, Strings.count("aaaaaa", "aa"));  // intersections
        assertEquals(2, Strings.count("01010101", "0101"));  // intersections

        assertThrows(NullPointerException.class, () -> Strings.count(null, "x"));
        assertThrows(NullPointerException.class, () -> Strings.count("x", null));
        assertThrows(IllegalArgumentException.class, () -> Strings.count("x", ""));
    }

    @Test
    public void testJoin() {
        assertEquals("", Strings.join(new ArrayList<>(), ","));  // empty list
        assertEquals("A", Strings.join(Collections.singletonList("A"), ","));  // single item
        assertEquals("A,B,C", Strings.join(Arrays.asList("A", "B", "C"), ",")); // single char delimiter
        assertEquals("A, B, C", Strings.join(Arrays.asList("A", "B", "C"), ", "));  // multiple char delimiter
        assertEquals("A//C", Strings.join(Arrays.asList("A", "", "C"), "/"));  // missed content
        assertEquals("А Б В", Strings.join(Arrays.asList("А", "Б", "В"), " "));  // non-English
        assertEquals("AAAAA", Strings.join(Arrays.asList("A", "A", "A"), "A")); // the same symbol
        assertEquals("__", Strings.join(Arrays.asList("", "", ""), "_"));  // no content

        assertThrows(NullPointerException.class, () -> Strings.join(null, ","));
        assertThrows(IllegalArgumentException.class, () -> Strings.join(Arrays.asList("AAA", null), ""));
    }

    @Test
    public void testSplit() {
        testSplitVerify("", ",", ""); // empty string
        testSplitVerify("Aaa", ",", "Aaa"); // delimiter not found
        testSplitVerify("Aaa", "Aaa", "", ""); // empty content
        testSplitVerify("A,B,C", ",", "A", "B", "C"); // single char delimiter
        testSplitVerify("A, b, c", ", ", "A", "b", "c"); // multiple chars delimiter
        testSplitVerify("A or B or C", "or", "A ", " B ", " C"); // do not trim
        testSplitVerify("А или Б или В", " или ", "А", "Б", "В"); // non-English
        testSplitVerify("xyz", "12345", "xyz"); // longer delimiter
        testSplitVerify("xyz", "\r\n", "xyz");  // special symbols

        assertThrows(NullPointerException.class, () -> Strings.split(null, "x"));
        assertThrows(NullPointerException.class, () -> Strings.split("x", null));
        assertThrows(NullPointerException.class, () -> Strings.split("abc", "x", null));
        assertThrows(IllegalArgumentException.class, () -> Strings.split("x", ""));

        ArrayList<String> list = new ArrayList<>();
        list.add("something");
        assertThrows(IllegalArgumentException.class, () -> Strings.split("abc", "x", list));
        // test the test environment
        assertThrows(AssertionFailedError.class, () -> testSplitVerify("abc", "b", "a", "x"));
    }

    private void testSplitVerify(String str, String divider, String... expected) {
        // Implicit buffer allocation
        testSplitVerify(Strings.split(str, divider), expected);

        // Explicit buffer allocation
        ArrayList<String> buffer = new ArrayList<>();
        Strings.split(str, divider, buffer);
        testSplitVerify(buffer, expected);
    }

    private void testSplitVerify(ArrayList<String> actual, String... expected) {
        assertNotNull(actual);
        assertNotNull(expected);
        assertEquals(expected.length, actual.size());

        for (int n = 0; n < actual.size(); n++) {
            assertEquals(expected[n], actual.get(n));
        }
    }

    @Test
    public void testWebSafe() throws UnsupportedEncodingException {
        assertEquals("", Strings.webSafe(""));
        assertEquals("aaa", Strings.webSafe("aaa"));
        assertEquals("aaa%3Fbbb", Strings.webSafe("aaa?bbb"));
        assertEquals("%D0%B0%D0%B1%D0%B2", Strings.webSafe("абв"));
    }

    @Test
    public void testRandom() {
        assertEquals("AAAAA", Strings.random("A", 5));
        assertEquals("AAAAA", Strings.random("AAA", 5));

        testRandom("A", 5);  // symbol copies
        testRandom("AAA", 5);  // non-effective char set
        testRandom("ABC", 5);   // normal
        testRandom("абвг", 5);  // non-English
        testRandom("ABC,.!", 5);  // special symbols
        testRandom("ABCD", 100_000);  // long result string
    }

    private void testRandom(String symbols, int length) {
        assertNotNull(symbols);

        String rnd = Strings.random(symbols, length);
        assertNotNull(rnd);
        assertEquals(length, rnd.length());

        for (int n = 0; n < length; n++) {
            char ch = rnd.charAt(n);
            assertTrue(symbols.indexOf(ch) >= 0);
        }
        System.out.println(rnd);
    }
}