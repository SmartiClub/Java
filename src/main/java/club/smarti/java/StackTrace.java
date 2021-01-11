package club.smarti.java;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Utils for stack trace processing
 * *
 * Advantages:
 * – auto remove the lib and system calls from the stack
 */
public class StackTrace {

    /**
     * Get stack trace for current thread
     * Note: Thread.getStackTrace() is 15-20% faster than Throwable.getStackTrace()
     *
     * @return current stack trace without this utility method
     */
    @NotNull
    @Contract(pure = true)
    public static StackTraceElement[] getCurrent() {
        StackTraceElement[] stack = Thread.currentThread().getStackTrace();
        stack = java.util.Arrays.copyOfRange(stack, 2, stack.length);
        return stack;
    }

    /**
     * Get current stack trace before the specific class
     *
     * @param exclude - top caller class to be excluded from the stack
     * @return current stack trace
     */
    @Nullable
    @Contract(pure = true, value = "null -> fail")
    public static StackTraceElement[] get(Class<?> exclude) {
        if (exclude == null) {
            throw new NullPointerException("Null class to exclude");
        }
        StackTraceElement[] stack = Thread.currentThread().getStackTrace();
        stack = removeClass(stack, exclude);
        return stack;
    }

    /**
     * Reduce the stack trace in order to remove some common header
     *
     * @param stack - stack trace to be cut
     * @param cls - top caller class to exclude
     * @return reduced stack trace
     */
    @NotNull
    @Contract(pure = true, value = "null, _ -> fail; _, null -> fail")
    public static StackTraceElement[] removeClass(StackTraceElement[] stack, Class<?> cls) {
        if (stack == null) {
            throw new NullPointerException("Null stack trace");
        }
        if (cls == null) {
            throw new NullPointerException("Null class to exclude");
        }
        String exclude = cls.getName();

        // Find the first occurrence
        for (int n = stack.length - 1; n >= 0; n--) {
            String name = stack[n].getClassName();
            if (exclude.equals(name)) {
                StackTraceElement[] subStack = new StackTraceElement[stack.length - n - 1];
                if (subStack.length > 0) {
                    Arrays.copy(stack, n + 1, subStack, 0, subStack.length);
                }
                return subStack;
            }
        }
        return stack;
    }

    /**
     * Reduce the stack trace in order to remove some common header
     * TODO: unit test it
     *
     * @param stack - stack trace to be cut
     * @param pkg - package name to exclude
     * @return reduced stack trace
     */
    @NotNull
    @Contract(pure = true, value = "null, _ -> fail; _, null -> fail")
    public static StackTraceElement[] removePackage(StackTraceElement[] stack, String pkg) {
        if (stack == null) {
            throw new NullPointerException("Null stack trace");
        }
        if (pkg == null) {
            throw new NullPointerException("Null package to exclude");
        }
        pkg += ".";

        // Find the first occurrence
        for (int n = stack.length - 1; n >= 0; n--) {
            String name = stack[n].getClassName();
            if (name.startsWith(pkg)) {
                StackTraceElement[] subStack = new StackTraceElement[stack.length - n - 1];
                if (subStack.length > 0) {
                    Arrays.copy(stack, n + 1, subStack, 0, subStack.length);
                }
                return subStack;
            }
        }
        return stack;
    }
}