package club.smarti.java;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Utils for Java string processing
 * *
 * Advantages:
 * – avoid client code duplication
 * – embedded asserts
 */
public class Strings {

    /**
     * Check if the string is not null and not empty
     * Note: whitespace string is considered non-empty
     *
     * @param str - string to check
     * @return true if the string length > 0, false otherwise
     */
    @Contract(pure = true, value = "null -> false")
    public static boolean notEmpty(String str) {
        return (str != null && !str.isEmpty());
    }

    /**
     * Check if the string starts with upper case letter
     *
     * @param str - string to check
     * @return true if the first character is upper case letter, false otherwise
     */
    @Contract(pure = true, value = "null -> fail")
    public static boolean startsWithUpperCase(String str) {
        if (str == null) {
            throw new NullPointerException("Null string");
        }
        if (str.length() > 0) {
            char ch = str.charAt(0);
            return Character.isUpperCase(ch);
        }
        return false;
    }

    /**
     * Check if the string starts with lower case letter
     *
     * @param str - string to check
     * @return true if the first character is lower case letter, false otherwise
     */
    @Contract(pure = true, value = "null -> fail")
    public static boolean startsWithLowerCase(String str) {
        if (str == null) {
            throw new NullPointerException("Null string");
        }
        if (str.length() > 0) {
            char ch = str.charAt(0);
            return Character.isLowerCase(ch);
        }
        return false;
    }

    /**
     * Count occurrences of the character
     *
     * @param str - string to check
     * @param ch - character to search
     * @return how many times the character appears in the string
     */
    @Contract(pure = true, value = "null, _ -> fail")
    public static int count(String str, char ch) {
        if (str == null) {
            throw new NullPointerException("Null string");
        }
        int count = 0;
        int len = str.length();

        for (int n = 0; n < len; n++) {
            if (str.charAt(n) == ch) {
                count++;
            }
        }
        return count;
    }

    /**
     * Count occurrences of the substring (without intersections)
     *
     * @param str - string to check
     * @param sub - substring to search
     * @return how many times the substring appears in the string (without intersections)
     */
    @Contract(pure = true, value = "null, _ -> fail; _, null -> fail")
    public static int count(String str, String sub) {
        if (str == null) {
            throw new NullPointerException("Null string");
        }
        if (sub == null) {
            throw new NullPointerException("Null substring");
        }
        if (sub.length() == 0) {
            throw new IllegalArgumentException("Empty substring");
        }
        int count = 0;
        int len = str.length();
        int pos = 0;

        while (pos >= 0 && pos < len) {
            pos = str.indexOf(sub, pos);
            if (pos >= 0) {
                count++;
                pos += sub.length();
            }
        }
        return count;
    }

    /**
     * Merge the list items to the single string
     *
     * @param list - list of items
     * @param delimiter - optional delimiter
     * @return result string
     */
    @NotNull
    @Contract(pure = true, value = "null, _ -> fail")
    public static String join(List<?> list, @Nullable String delimiter) {
        if (list == null) {
            throw new NullPointerException("Null list");
        }
        StringBuilder builder = new StringBuilder();
        boolean withDelimiter = notEmpty(delimiter);

        for (int n = 0; n < list.size(); n++) {
            if (withDelimiter) {
                if (n > 0) {
                    builder.append(delimiter);
                }
            }
            Object item = list.get(n);
            if (item == null) {
                throw new IllegalArgumentException("Null item at index " + n);
            }
            builder.append(item);
        }
        return builder.toString();
    }

    /**
     * Split the string
     * Difference with the original split function:
     * - return list instead of array
     * - doesn't skip first/last items if they are empty
     * - doesn't support regular expression
     * *
     * Note: Java String.split(regex) can provide better performance in some cases
     *
     * @param str - string to split
     * @param delimiter - divider string
     * @param resultBuffer - buffer for performance optimization
     */
    @Contract("null, _, _ -> fail; _, null, _ -> fail; _, _, null -> fail")
    public static void split(String str, String delimiter, ArrayList<String> resultBuffer) {
        if (str == null) {
            throw new NullPointerException("Null string");
        }
        if (delimiter == null) {
            throw new NullPointerException("Null delimiter");
        }
        if (resultBuffer == null) {
            throw new NullPointerException("Null buffer");
        }

        int len = str.length();
        int delimiterLen = delimiter.length();
        if (delimiterLen == 0) {
            throw new IllegalArgumentException("Empty delimiter");
        }
        if (resultBuffer.size() > 0) {
            throw new IllegalArgumentException("Non-empty buffer");
        }

        // Case #1: optimization for single character delimiter
        if (delimiterLen == 1) {
            char delimiterSymbol = delimiter.charAt(0);
            int startPos = 0;

            for (int n = 0; n < len; n++) {
                if (str.charAt(n) == delimiterSymbol) {
                    resultBuffer.add(str.substring(startPos, n));
                    startPos = n + 1;
                }
            }
            if (startPos < len) {
                resultBuffer.add(str.substring(startPos));
            }
            else {
                resultBuffer.add("");
            }
        }
        // Case #2: multi-character delimiter string
        else {
            for (int n = 0; n < len; n++) {
                int pos = str.indexOf(delimiter, n);
                if (pos == -1) {
                    resultBuffer.add(str.substring(n));
                    break;
                }
                else {
                    resultBuffer.add(str.substring(n, pos));
                    n = pos + delimiterLen - 1;
                }
            }
            if (str.lastIndexOf(delimiter) == len - delimiterLen) {
                resultBuffer.add("");
            }
        }
    }

    /**
     * Split the string
     *
     * @param str - string to split
     * @param delimiter - divider string
     * @return result list of the substrings
     * @see club.smarti.java.Strings#split(String, String, ArrayList) for details
     */
    @NotNull
    @Contract(pure = true, value = "null, _ -> fail; _, null -> fail")
    public static ArrayList<String> split(String str, String delimiter) {
        ArrayList<String> result = new ArrayList<>();
        split(str, delimiter, result);
        return result;
    }

    /**
     * Convert the string for further usage in web URL
     *
     * @param str - url string to convert
     * @return result string
     */
    @NotNull
    @Contract("null -> fail")
    public static String webSafe(String str) throws UnsupportedEncodingException {
        return URLEncoder.encode(str, StandardCharsets.UTF_8).replace("+", "%20");
    }

    /**
     * Generate random string
     * Note: it doesn't support symbols combining 2 or more characters
     *
     * @param charSet - string representing set of allowed symbols
     * @param length - result string length
     * @return generated string
     */
    @NotNull
    @Contract("null, _ -> fail")
    public static String random(String charSet, int length) {
        if (charSet == null) {
            throw new NullPointerException("Null char set, len=" + length);
        }
        if (charSet.length() == 0) {
            throw new IllegalArgumentException("Empty char set, len=" + length);
        }
        if (length <= 0) {
            throw new IllegalArgumentException("Incorrect length " + length);
        }
        char[] result = new char[length];
        for (int n = 0; n < length; n++) {
            int rnd = Randoms.get(charSet.length());
            result[n] = charSet.charAt(rnd);
        }
        return String.valueOf(result);
    }
}