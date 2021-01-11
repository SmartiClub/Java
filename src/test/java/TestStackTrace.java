import org.junit.jupiter.api.Test;

import club.smarti.java.StackTrace;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestStackTrace {

    @Test
    public void testGetCurrent() {
        StackTraceElement[] stack = StackTrace.getCurrent();
        assertTrue(stack.length >= 4); // (test env) => (TestStackTrace.test) => (StackTrace.getCurrent) => (Thread.currentThread)
        assertEquals(TestStackTrace.class.getName(), stack[0].getClassName());
    }

    @Test
    public void testGet() {
        StackTraceElement[] stack = StackTrace.get(StackTrace.class);
        assertTrue(stack.length >= 2); // (test env) => (TestStackTrace.test)
        assertEquals(TestStackTrace.class.getName(), stack[0].getClassName());
    }

    @Test
    public void testGet_empty() {
        StackTraceElement[] stack = StackTrace.get(Thread.class);
        assertEquals(0, stack.length);
    }

    @Test
    public void testGet_nested() {
        new TestStackTraceData.A() {
            {
                final Class<? extends TestStackTraceData.A> outer = this.getClass();
                StackTraceElement[] stack = StackTrace.getCurrent();
                assertEquals(outer.getName(), stack[0].getClassName());

                new TestStackTraceData.A() {{
                    // Current
                    final Class<? extends TestStackTraceData.A> inner = this.getClass();
                    StackTraceElement[] stack = StackTrace.getCurrent();
                    assertEquals(inner.getName(), stack[0].getClassName());

                    // Parent A
                    stack = StackTrace.get(inner);
                    assertEquals(outer.getName(), stack[0].getClassName());

                    // Parent TestStackTrace
                    stack = StackTrace.get(outer);
                    assertEquals(TestStackTrace.class.getName(), stack[0].getClassName());
                }};
            }
        };
    }
}