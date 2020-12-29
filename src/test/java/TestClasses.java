import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import club.smarti.java.Classes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SuppressWarnings("ResultOfMethodCallIgnored")
public class TestClasses {

    @Test
    public void testIsPrimitive() {
        assertTrue(Classes.isPrimitive(boolean.class));
        assertTrue(Classes.isPrimitive(byte.class));
        assertTrue(Classes.isPrimitive(char.class));
        assertTrue(Classes.isPrimitive(short.class));
        assertTrue(Classes.isPrimitive(int.class));
        assertTrue(Classes.isPrimitive(long.class));
        assertTrue(Classes.isPrimitive(float.class));
        assertTrue(Classes.isPrimitive(double.class));

        assertFalse(Classes.isPrimitive(Boolean.class));
        assertFalse(Classes.isPrimitive(Byte.class));
        assertFalse(Classes.isPrimitive(Character.class));
        assertFalse(Classes.isPrimitive(Short.class));
        assertFalse(Classes.isPrimitive(Integer.class));
        assertFalse(Classes.isPrimitive(Long.class));
        assertFalse(Classes.isPrimitive(Float.class));
        assertFalse(Classes.isPrimitive(Double.class));

        assertFalse(Classes.isPrimitive(boolean[].class));
        assertFalse(Classes.isPrimitive(int[].class));
        assertFalse(Classes.isPrimitive(Float[].class));
        assertFalse(Classes.isPrimitive(Object.class));
        assertFalse(Classes.isPrimitive(Object[].class));
        assertFalse(Classes.isPrimitive(List.class));
        assertFalse(Classes.isPrimitive(HashMap.class));

        assertThrows(NullPointerException.class, () -> Classes.isPrimitive(null));
    }

    @Test
    public void isPrimitiveObject() {
        assertTrue(Classes.isPrimitiveObject(Boolean.class));
        assertTrue(Classes.isPrimitiveObject(Byte.class));
        assertTrue(Classes.isPrimitiveObject(Character.class));
        assertTrue(Classes.isPrimitiveObject(Short.class));
        assertTrue(Classes.isPrimitiveObject(Integer.class));
        assertTrue(Classes.isPrimitiveObject(Long.class));
        assertTrue(Classes.isPrimitiveObject(Float.class));
        assertTrue(Classes.isPrimitiveObject(Double.class));

        assertFalse(Classes.isPrimitiveObject(boolean.class));
        assertFalse(Classes.isPrimitiveObject(byte.class));
        assertFalse(Classes.isPrimitiveObject(char.class));
        assertFalse(Classes.isPrimitiveObject(short.class));
        assertFalse(Classes.isPrimitiveObject(int.class));
        assertFalse(Classes.isPrimitiveObject(long.class));
        assertFalse(Classes.isPrimitiveObject(float.class));
        assertFalse(Classes.isPrimitiveObject(double.class));

        assertFalse(Classes.isPrimitiveObject(boolean[].class));
        assertFalse(Classes.isPrimitiveObject(int[].class));
        assertFalse(Classes.isPrimitiveObject(Float[].class));
        assertFalse(Classes.isPrimitiveObject(Object.class));
        assertFalse(Classes.isPrimitiveObject(Object[].class));
        assertFalse(Classes.isPrimitiveObject(List.class));
        assertFalse(Classes.isPrimitiveObject(HashMap.class));

        assertThrows(NullPointerException.class, () -> Classes.isPrimitiveObject(null));
    }

    @Test
    public void testIsArray() {
        assertTrue(Classes.isArray(boolean[].class));
        assertTrue(Classes.isArray(byte[].class));
        assertTrue(Classes.isArray(char[].class));
        assertTrue(Classes.isArray(short[].class));
        assertTrue(Classes.isArray(int[].class));
        assertTrue(Classes.isArray(float[].class));
        assertTrue(Classes.isArray(double[].class));
        assertTrue(Classes.isArray(Object[].class));
        assertTrue(Classes.isArray(Boolean[].class));
        assertTrue(Classes.isArray(Integer[].class));
        assertTrue(Classes.isArray(Double[].class));
        assertTrue(Classes.isArray(List[].class));
        assertTrue(Classes.isArray(HashMap[].class));

        //noinspection InstantiatingObjectToGetClassObject
        assertTrue(Classes.isArray(new int[10].getClass()));

        assertFalse(Classes.isArray(boolean.class));
        assertFalse(Classes.isArray(int.class));
        assertFalse(Classes.isArray(double.class));
        assertFalse(Classes.isArray(Object.class));
        assertFalse(Classes.isArray(Integer.class));
        assertFalse(Classes.isArray(List.class));
        assertFalse(Classes.isArray(ArrayList.class));

        assertThrows(NullPointerException.class, () -> Classes.isArray(null));
    }

    @Test
    public void testIsStatic() {
        assertTrue(Classes.isStatic(TestClassesData.staticA.class));

        assertFalse(Classes.isStatic(boolean.class));
        assertFalse(Classes.isStatic(Integer.class));
        assertFalse(Classes.isStatic(float[].class));
        assertFalse(Classes.isStatic(String[].class));
        assertFalse(Classes.isStatic(TestClassesData.A.class));
        assertFalse(Classes.isStatic(TestClassesData.B_ext_A.class));

        assertThrows(NullPointerException.class, () -> Classes.isStatic(null));
    }

    @Test
    public void getComponentType_Successful() {
        assertEquals(boolean.class, Classes.getComponentType(boolean[].class));
        assertEquals(byte.class, Classes.getComponentType(byte[].class));
        assertEquals(short.class, Classes.getComponentType(short[].class));
        assertEquals(char.class, Classes.getComponentType(char[].class));
        assertEquals(int.class, Classes.getComponentType(int[].class));
        assertEquals(long.class, Classes.getComponentType(long[].class));
        assertEquals(float.class, Classes.getComponentType(float[].class));
        assertEquals(double.class, Classes.getComponentType(double[].class));
        assertEquals(Object.class, Classes.getComponentType(Object[].class));
        assertEquals(Boolean.class, Classes.getComponentType(Boolean[].class));
        assertEquals(Integer.class, Classes.getComponentType(Integer[].class));
        assertEquals(Double.class, Classes.getComponentType(Double[].class));
        assertEquals(ArrayList.class, Classes.getComponentType(ArrayList[].class));
        assertEquals(int[].class, Classes.getComponentType(int[][].class));
        assertEquals(Map[].class, Classes.getComponentType(Map[][].class));

        //noinspection InstantiatingObjectToGetClassObject
        assertEquals(int.class, Classes.getComponentType(new int[10].getClass()));
        //noinspection InstantiatingObjectToGetClassObject
        assertEquals(Integer.class, Classes.getComponentType(new Integer[10].getClass()));

        assertThrows(NullPointerException.class, () -> Classes.getComponentType(null));
        assertThrows(IllegalArgumentException.class, () -> Classes.getComponentType(int.class));
        assertThrows(IllegalArgumentException.class, () -> Classes.getComponentType(ArrayList.class));
    }

    @Test
    public void testIsAssignable_withoutAutoboxing() {
        assertTrue(Classes.isAssignable(boolean.class, boolean.class, false));
        assertFalse(Classes.isAssignable(boolean.class, Boolean.class, false));
        assertFalse(Classes.isAssignable(boolean.class, Object.class, false));

        assertTrue(Classes.isAssignable(byte.class, byte.class, false));
        assertFalse(Classes.isAssignable(byte.class, Byte.class, false));
        assertFalse(Classes.isAssignable(byte.class, Object.class, false));

        assertTrue(Classes.isAssignable(short.class, short.class, false));
        assertFalse(Classes.isAssignable(short.class, Short.class, false));
        assertTrue(Classes.isAssignable(short.class, byte.class, false));
        assertFalse(Classes.isAssignable(short.class, Byte.class, false));
        assertFalse(Classes.isAssignable(short.class, char.class, false));
        assertFalse(Classes.isAssignable(short.class, Character.class, false));
        assertFalse(Classes.isAssignable(short.class, Object.class, false));

        assertTrue(Classes.isAssignable(char.class, char.class, false));
        assertFalse(Classes.isAssignable(char.class, Character.class, false));
        assertFalse(Classes.isAssignable(char.class, byte.class, false));
        assertFalse(Classes.isAssignable(char.class, Byte.class, false));
        assertFalse(Classes.isAssignable(char.class, short.class, false));
        assertFalse(Classes.isAssignable(char.class, Short.class, false));
        assertFalse(Classes.isAssignable(char.class, Object.class, false));

        assertTrue(Classes.isAssignable(int.class, int.class, false));
        assertFalse(Classes.isAssignable(int.class, Integer.class, false));
        assertTrue(Classes.isAssignable(int.class, byte.class, false));
        assertFalse(Classes.isAssignable(int.class, Byte.class, false));
        assertTrue(Classes.isAssignable(int.class, short.class, false));
        assertFalse(Classes.isAssignable(int.class, Short.class, false));
        assertTrue(Classes.isAssignable(int.class, char.class, false));
        assertFalse(Classes.isAssignable(int.class, Character.class, false));
        assertFalse(Classes.isAssignable(int.class, Object.class, false));

        assertTrue(Classes.isAssignable(long.class, long.class, false));
        assertFalse(Classes.isAssignable(long.class, Long.class, false));
        assertTrue(Classes.isAssignable(long.class, byte.class, false));
        assertFalse(Classes.isAssignable(long.class, Byte.class, false));
        assertTrue(Classes.isAssignable(long.class, short.class, false));
        assertFalse(Classes.isAssignable(long.class, Short.class, false));
        assertTrue(Classes.isAssignable(long.class, char.class, false));
        assertFalse(Classes.isAssignable(long.class, Character.class, false));
        assertTrue(Classes.isAssignable(long.class, int.class, false));
        assertFalse(Classes.isAssignable(long.class, Integer.class, false));
        assertFalse(Classes.isAssignable(long.class, Object.class, false));

        assertTrue(Classes.isAssignable(float.class, float.class, false));
        assertFalse(Classes.isAssignable(float.class, Float.class, false));
        assertTrue(Classes.isAssignable(float.class, byte.class, false));
        assertFalse(Classes.isAssignable(float.class, Byte.class, false));
        assertTrue(Classes.isAssignable(float.class, short.class, false));
        assertFalse(Classes.isAssignable(float.class, Short.class, false));
        assertTrue(Classes.isAssignable(float.class, char.class, false));
        assertFalse(Classes.isAssignable(float.class, Character.class, false));
        assertTrue(Classes.isAssignable(float.class, int.class, false));
        assertFalse(Classes.isAssignable(float.class, Integer.class, false));
        assertTrue(Classes.isAssignable(float.class, long.class, false));
        assertFalse(Classes.isAssignable(float.class, Long.class, false));
        assertFalse(Classes.isAssignable(float.class, Object.class, false));

        assertTrue(Classes.isAssignable(double.class, double.class, false));
        assertFalse(Classes.isAssignable(double.class, Double.class, false));
        assertTrue(Classes.isAssignable(double.class, byte.class, false));
        assertFalse(Classes.isAssignable(double.class, Byte.class, false));
        assertTrue(Classes.isAssignable(double.class, short.class, false));
        assertFalse(Classes.isAssignable(double.class, Short.class, false));
        assertTrue(Classes.isAssignable(double.class, char.class, false));
        assertFalse(Classes.isAssignable(double.class, Character.class, false));
        assertTrue(Classes.isAssignable(double.class, int.class, false));
        assertFalse(Classes.isAssignable(double.class, Integer.class, false));
        assertTrue(Classes.isAssignable(double.class, long.class, false));
        assertFalse(Classes.isAssignable(double.class, Long.class, false));
        assertTrue(Classes.isAssignable(double.class, float.class, false));
        assertFalse(Classes.isAssignable(double.class, Float.class, false));
        assertFalse(Classes.isAssignable(double.class, Object.class, false));

        assertFalse(Classes.isAssignable(Object.class, boolean.class, false));
        assertFalse(Classes.isAssignable(Object.class, byte.class, false));
        assertFalse(Classes.isAssignable(Object.class, char.class, false));
        assertFalse(Classes.isAssignable(Object.class, short.class, false));
        assertFalse(Classes.isAssignable(Object.class, int.class, false));
        assertFalse(Classes.isAssignable(Object.class, long.class, false));
        assertFalse(Classes.isAssignable(Object.class, float.class, false));
        assertFalse(Classes.isAssignable(Object.class, double.class, false));
        assertTrue(Classes.isAssignable(Object[].class, Double[].class, false));

        assertFalse(Classes.isAssignable(Integer.class, Boolean.class, false));
        assertFalse(Classes.isAssignable(Integer.class, Short.class, false));
        assertFalse(Classes.isAssignable(Integer.class, Character.class, false));
        assertTrue(Classes.isAssignable(Integer.class, Integer.class, false));
        assertFalse(Classes.isAssignable(Integer.class, Long.class, false));
        assertFalse(Classes.isAssignable(Integer.class, Float.class, false));
        assertFalse(Classes.isAssignable(Integer.class, Double.class, false));

        assertFalse(Classes.isAssignable(Long[].class, long[].class, false));

        assertTrue(Classes.isAssignable(Map.class, Map.class, false));
        assertTrue(Classes.isAssignable(Map.class, AbstractMap.class, false));
        assertTrue(Classes.isAssignable(Map.class, HashMap.class, false));
        assertTrue(Classes.isAssignable(AbstractMap.class, HashMap.class, false));
        assertFalse(Classes.isAssignable(HashMap.class, Map.class, false));

        assertThrows(NullPointerException.class, () -> Classes.isAssignable(null, Integer.class, false));
        assertThrows(NullPointerException.class, () -> Classes.isAssignable(Integer.class, null, false));
    }

    @Test
    public void testIsAssignable_withAutoboxing() {
        assertTrue(Classes.isAssignable(boolean.class, boolean.class, true));
        assertTrue(Classes.isAssignable(boolean.class, Boolean.class, true));
        assertFalse(Classes.isAssignable(boolean.class, int.class, true));
        assertFalse(Classes.isAssignable(boolean.class, Object.class, true));

        assertTrue(Classes.isAssignable(byte.class, byte.class, true));
        assertTrue(Classes.isAssignable(byte.class, Byte.class, true));
        assertFalse(Classes.isAssignable(byte.class, Object.class, true));

        assertTrue(Classes.isAssignable(short.class, short.class, true));
        assertTrue(Classes.isAssignable(short.class, Short.class, true));
        assertTrue(Classes.isAssignable(short.class, byte.class, true));
        assertTrue(Classes.isAssignable(short.class, Byte.class, true));
        assertFalse(Classes.isAssignable(short.class, char.class, true));
        assertFalse(Classes.isAssignable(short.class, Object.class, true));

        assertTrue(Classes.isAssignable(char.class, char.class, true));
        assertTrue(Classes.isAssignable(char.class, Character.class, true));
        assertFalse(Classes.isAssignable(char.class, byte.class, true));
        assertFalse(Classes.isAssignable(char.class, Byte.class, true));
        assertFalse(Classes.isAssignable(char.class, short.class, true));
        assertFalse(Classes.isAssignable(char.class, Short.class, true));
        assertFalse(Classes.isAssignable(char.class, short.class, true));
        assertFalse(Classes.isAssignable(char.class, Object.class, true));

        assertTrue(Classes.isAssignable(int.class, int.class, true));
        assertTrue(Classes.isAssignable(int.class, Integer.class, true));
        assertFalse(Classes.isAssignable(int.class, boolean.class, true));
        assertTrue(Classes.isAssignable(int.class, byte.class, true));
        assertTrue(Classes.isAssignable(int.class, Byte.class, true));
        assertTrue(Classes.isAssignable(int.class, short.class, true));
        assertTrue(Classes.isAssignable(int.class, Short.class, true));
        assertTrue(Classes.isAssignable(int.class, char.class, true));
        assertTrue(Classes.isAssignable(int.class, Character.class, true));
        assertFalse(Classes.isAssignable(int.class, Object.class, true));

        assertTrue(Classes.isAssignable(long.class, long.class, true));
        assertTrue(Classes.isAssignable(long.class, Long.class, true));
        assertTrue(Classes.isAssignable(long.class, byte.class, true));
        assertTrue(Classes.isAssignable(long.class, Byte.class, true));
        assertTrue(Classes.isAssignable(long.class, short.class, true));
        assertTrue(Classes.isAssignable(long.class, Short.class, true));
        assertTrue(Classes.isAssignable(long.class, char.class, true));
        assertTrue(Classes.isAssignable(long.class, Character.class, true));
        assertTrue(Classes.isAssignable(long.class, int.class, true));
        assertTrue(Classes.isAssignable(long.class, Integer.class, true));
        assertFalse(Classes.isAssignable(long.class, float.class, true));
        assertFalse(Classes.isAssignable(long.class, Float.class, true));
        assertFalse(Classes.isAssignable(long.class, Object.class, true));

        assertTrue(Classes.isAssignable(float.class, float.class, true));
        assertTrue(Classes.isAssignable(float.class, Float.class, true));
        assertTrue(Classes.isAssignable(float.class, byte.class, true));
        assertTrue(Classes.isAssignable(float.class, Byte.class, true));
        assertTrue(Classes.isAssignable(float.class, short.class, true));
        assertTrue(Classes.isAssignable(float.class, Short.class, true));
        assertTrue(Classes.isAssignable(float.class, char.class, true));
        assertTrue(Classes.isAssignable(float.class, Character.class, true));
        assertTrue(Classes.isAssignable(float.class, int.class, true));
        assertTrue(Classes.isAssignable(float.class, Integer.class, true));
        assertTrue(Classes.isAssignable(float.class, long.class, true));
        assertTrue(Classes.isAssignable(float.class, Long.class, true));
        assertFalse(Classes.isAssignable(float.class, double.class, true));
        assertFalse(Classes.isAssignable(float.class, Double.class, true));
        assertFalse(Classes.isAssignable(float.class, Object.class, true));

        assertTrue(Classes.isAssignable(double.class, double.class, true));
        assertTrue(Classes.isAssignable(double.class, Double.class, true));
        assertTrue(Classes.isAssignable(double.class, byte.class, true));
        assertTrue(Classes.isAssignable(double.class, Byte.class, true));
        assertTrue(Classes.isAssignable(double.class, short.class, true));
        assertTrue(Classes.isAssignable(double.class, Short.class, true));
        assertTrue(Classes.isAssignable(double.class, char.class, true));
        assertTrue(Classes.isAssignable(double.class, Character.class, true));
        assertTrue(Classes.isAssignable(double.class, int.class, true));
        assertTrue(Classes.isAssignable(double.class, Integer.class, true));
        assertTrue(Classes.isAssignable(double.class, long.class, true));
        assertTrue(Classes.isAssignable(double.class, Long.class, true));
        assertTrue(Classes.isAssignable(double.class, float.class, true));
        assertTrue(Classes.isAssignable(double.class, Float.class, true));
        assertFalse(Classes.isAssignable(double.class, Object.class, true));

        assertTrue(Classes.isAssignable(Boolean.class, Boolean.class, true));
        assertTrue(Classes.isAssignable(Boolean.class, boolean.class, true));
        assertTrue(Classes.isAssignable(Byte.class, Byte.class, true));
        assertTrue(Classes.isAssignable(Byte.class, byte.class, true));
        assertTrue(Classes.isAssignable(Character.class, Character.class, true));
        assertTrue(Classes.isAssignable(Character.class, char.class, true));
        assertTrue(Classes.isAssignable(Short.class, Short.class, true));
        assertTrue(Classes.isAssignable(Short.class, short.class, true));
        assertTrue(Classes.isAssignable(Integer.class, Integer.class, true));
        assertTrue(Classes.isAssignable(Integer.class, int.class, true));
        assertTrue(Classes.isAssignable(Long.class, Long.class, true));
        assertTrue(Classes.isAssignable(Long.class, long.class, true));
        assertTrue(Classes.isAssignable(Float.class, Float.class, true));
        assertTrue(Classes.isAssignable(Float.class, float.class, true));
        assertTrue(Classes.isAssignable(Double.class, Double.class, true));
        assertTrue(Classes.isAssignable(Double.class, double.class, true));

        assertTrue(Classes.isAssignable(Object.class, boolean.class, true));
        assertTrue(Classes.isAssignable(Object.class, byte.class, true));
        assertTrue(Classes.isAssignable(Object.class, char.class, true));
        assertTrue(Classes.isAssignable(Object.class, short.class, true));
        assertTrue(Classes.isAssignable(Object.class, int.class, true));
        assertTrue(Classes.isAssignable(Object.class, long.class, true));
        assertTrue(Classes.isAssignable(Object.class, float.class, true));
        assertTrue(Classes.isAssignable(Object.class, double.class, true));
        assertTrue(Classes.isAssignable(Object.class, Character.class, true));
        assertTrue(Classes.isAssignable(Object.class, Integer.class, true));
        assertTrue(Classes.isAssignable(Object.class, Float[].class, true));
        assertTrue(Classes.isAssignable(Object[].class, Double[].class, true));

        assertFalse(Classes.isAssignable(Integer.class, Boolean.class, true));
        assertFalse(Classes.isAssignable(Integer.class, Short.class, true));
        assertFalse(Classes.isAssignable(Integer.class, Character.class, true));
        assertTrue(Classes.isAssignable(Integer.class, Integer.class, true));
        assertFalse(Classes.isAssignable(Integer.class, Long.class, true));
        assertFalse(Classes.isAssignable(Integer.class, Float.class, true));
        assertFalse(Classes.isAssignable(Integer.class, Double.class, true));

        assertFalse(Classes.isAssignable(Integer.class, byte.class, true));
        assertFalse(Classes.isAssignable(Long.class, int.class, true));
        assertFalse(Classes.isAssignable(Double.class, float.class, true));

        assertFalse(Classes.isAssignable(Boolean[].class, boolean[].class, true));
        assertFalse(Classes.isAssignable(Long[].class, long[].class, true));
        assertFalse(Classes.isAssignable(Float[].class, float[].class, true));
        assertFalse(Classes.isAssignable(int[].class, Integer[].class, true));
        assertFalse(Classes.isAssignable(double[].class, Double[].class, true));

        assertTrue(Classes.isAssignable(Map.class, Map.class, true));
        assertTrue(Classes.isAssignable(Map.class, AbstractMap.class, true));
        assertTrue(Classes.isAssignable(Map.class, HashMap.class, true));
        assertTrue(Classes.isAssignable(AbstractMap.class, HashMap.class, true));
        assertFalse(Classes.isAssignable(HashMap.class, Map.class, true));

        assertThrows(NullPointerException.class, () -> Classes.isAssignable(null, Integer.class, true));
        assertThrows(NullPointerException.class, () -> Classes.isAssignable(Integer.class, null, true));
    }

    @SuppressWarnings({"UnnecessaryBoxing", "ConstantConditions"})
    @Test
    public void testIsAssignable_withInstance() {
        final Object NULL = null;

        assertFalse(Classes.isAssignable(boolean.class, NULL));
        assertTrue(Classes.isAssignable(boolean.class, true));
        assertTrue(Classes.isAssignable(boolean.class, Boolean.TRUE));
        assertFalse(Classes.isAssignable(boolean.class, 1));
        assertFalse(Classes.isAssignable(boolean.class, new Object()));

        assertFalse(Classes.isAssignable(byte.class, NULL));
        assertTrue(Classes.isAssignable(byte.class, (byte) 1));
        assertTrue(Classes.isAssignable(byte.class, Byte.valueOf((byte) 1)));
        assertFalse(Classes.isAssignable(byte.class, 1)); // int
        assertFalse(Classes.isAssignable(byte.class, 0b1)); // int
        assertFalse(Classes.isAssignable(byte.class, new Object()));

        assertFalse(Classes.isAssignable(short.class, NULL));
        assertTrue(Classes.isAssignable(short.class, (short) 1));
        assertTrue(Classes.isAssignable(short.class, Short.valueOf((short) 1)));
        assertTrue(Classes.isAssignable(short.class, (byte) 1));
        assertTrue(Classes.isAssignable(short.class, Byte.valueOf((byte) 1)));
        assertFalse(Classes.isAssignable(short.class, (char) 1));
        assertFalse(Classes.isAssignable(short.class, Character.valueOf((char) 1)));
        assertFalse(Classes.isAssignable(short.class, new Object()));

        assertFalse(Classes.isAssignable(char.class, NULL));
        assertTrue(Classes.isAssignable(char.class, (char) 1));
        assertTrue(Classes.isAssignable(char.class, Character.valueOf((char) 1)));
        assertFalse(Classes.isAssignable(char.class, (byte) 1));
        assertFalse(Classes.isAssignable(char.class, Byte.valueOf((byte) 1)));
        assertFalse(Classes.isAssignable(char.class, (short) 1));
        assertFalse(Classes.isAssignable(char.class, Short.valueOf((short) 1)));
        assertFalse(Classes.isAssignable(char.class, new Object()));

        assertFalse(Classes.isAssignable(int.class, NULL));
        assertTrue(Classes.isAssignable(int.class, 1));
        assertTrue(Classes.isAssignable(int.class, Integer.valueOf(1)));
        assertFalse(Classes.isAssignable(int.class, true));
        assertFalse(Classes.isAssignable(int.class, Boolean.valueOf(true)));
        assertTrue(Classes.isAssignable(int.class, (byte) 1));
        assertTrue(Classes.isAssignable(int.class, Byte.valueOf((byte) 1)));
        assertTrue(Classes.isAssignable(int.class, (short) 1));
        assertTrue(Classes.isAssignable(int.class, Short.valueOf((short) 1)));
        assertTrue(Classes.isAssignable(int.class, (char) 1));
        assertTrue(Classes.isAssignable(int.class, Character.valueOf((char) 1)));
        assertFalse(Classes.isAssignable(int.class, new Object()));

        assertFalse(Classes.isAssignable(long.class, NULL));
        assertTrue(Classes.isAssignable(long.class, 1L));
        assertTrue(Classes.isAssignable(long.class, Long.valueOf(1L)));
        assertTrue(Classes.isAssignable(long.class, (byte) 1));
        assertTrue(Classes.isAssignable(long.class, Byte.valueOf((byte) 1)));
        assertTrue(Classes.isAssignable(long.class, (short) 1));
        assertTrue(Classes.isAssignable(long.class, Short.valueOf((short) 1)));
        assertTrue(Classes.isAssignable(long.class, (char) 1));
        assertTrue(Classes.isAssignable(long.class, Character.valueOf((char) 1)));
        assertTrue(Classes.isAssignable(long.class, 1));
        assertTrue(Classes.isAssignable(long.class, Integer.valueOf(1)));
        assertFalse(Classes.isAssignable(long.class, 1f));
        assertFalse(Classes.isAssignable(long.class, Float.valueOf(1f)));
        assertFalse(Classes.isAssignable(long.class, new Object()));

        assertFalse(Classes.isAssignable(float.class, NULL));
        assertTrue(Classes.isAssignable(float.class, 1f));
        assertTrue(Classes.isAssignable(float.class, Float.valueOf(1f)));
        assertTrue(Classes.isAssignable(float.class, (byte) 1));
        assertTrue(Classes.isAssignable(float.class, Byte.valueOf((byte) 1)));
        assertTrue(Classes.isAssignable(float.class, (short) 1));
        assertTrue(Classes.isAssignable(float.class, Short.valueOf((short) 1)));
        assertTrue(Classes.isAssignable(float.class, (char) 1));
        assertTrue(Classes.isAssignable(float.class, Character.valueOf((char) 1)));
        assertTrue(Classes.isAssignable(float.class, 1));
        assertTrue(Classes.isAssignable(float.class, Integer.valueOf(1)));
        assertTrue(Classes.isAssignable(float.class, 1L));
        assertTrue(Classes.isAssignable(float.class, Long.valueOf(1L)));
        assertFalse(Classes.isAssignable(float.class, 1.0));
        assertFalse(Classes.isAssignable(float.class, Double.valueOf(1.0)));
        assertFalse(Classes.isAssignable(float.class, new Object()));

        assertFalse(Classes.isAssignable(double.class, NULL));
        assertTrue(Classes.isAssignable(double.class, 1.0));
        assertTrue(Classes.isAssignable(double.class, Double.valueOf(1.0)));
        assertTrue(Classes.isAssignable(double.class, (byte) 1));
        assertTrue(Classes.isAssignable(double.class, Byte.valueOf((byte) 1)));
        assertTrue(Classes.isAssignable(double.class, (short) 1));
        assertTrue(Classes.isAssignable(double.class, Short.valueOf((short) 1)));
        assertTrue(Classes.isAssignable(double.class, (char) 1));
        assertTrue(Classes.isAssignable(double.class, Character.valueOf((char) 1)));
        assertTrue(Classes.isAssignable(double.class, 1));
        assertTrue(Classes.isAssignable(double.class, Integer.valueOf(1)));
        assertTrue(Classes.isAssignable(double.class, 1L));
        assertTrue(Classes.isAssignable(double.class, Long.valueOf(1L)));
        assertTrue(Classes.isAssignable(double.class, 1f));
        assertTrue(Classes.isAssignable(double.class, Float.valueOf(1f)));
        assertFalse(Classes.isAssignable(double.class, new Object()));

        assertTrue(Classes.isAssignable(Boolean.class, NULL));
        assertTrue(Classes.isAssignable(Boolean.class, true));
        assertTrue(Classes.isAssignable(Boolean.class, false));
        assertTrue(Classes.isAssignable(Byte.class, (byte) 1));
        assertTrue(Classes.isAssignable(Byte.class, Byte.valueOf((byte) 1)));
        assertTrue(Classes.isAssignable(Character.class, (char) 1));
        assertTrue(Classes.isAssignable(Character.class, Character.valueOf((char) 1)));
        assertTrue(Classes.isAssignable(Short.class, (short) 1));
        assertTrue(Classes.isAssignable(Short.class, Short.valueOf((short) 1)));
        assertTrue(Classes.isAssignable(Integer.class, 1));
        assertTrue(Classes.isAssignable(Integer.class, Integer.valueOf(1)));
        assertTrue(Classes.isAssignable(Long.class, 1L));
        assertTrue(Classes.isAssignable(Long.class, Long.valueOf(1L)));
        assertTrue(Classes.isAssignable(Float.class, 1f));
        assertTrue(Classes.isAssignable(Float.class, Float.valueOf(1f)));
        assertTrue(Classes.isAssignable(Double.class, 1.0));
        assertTrue(Classes.isAssignable(Double.class, Double.valueOf(1.0)));

        assertTrue(Classes.isAssignable(Object.class, NULL));
        assertTrue(Classes.isAssignable(Object.class, true));
        assertTrue(Classes.isAssignable(Object.class, (byte) 1));
        assertTrue(Classes.isAssignable(Object.class, (char) 1));
        assertTrue(Classes.isAssignable(Object.class, (short) 1));
        assertTrue(Classes.isAssignable(Object.class, 1));
        assertTrue(Classes.isAssignable(Object.class, 1L));
        assertTrue(Classes.isAssignable(Object.class, 1f));
        assertTrue(Classes.isAssignable(Object.class, 1.0));
        assertTrue(Classes.isAssignable(Object.class, Byte.valueOf((byte) 1)));
        assertTrue(Classes.isAssignable(Object.class, Character.valueOf((char) 1)));
        assertTrue(Classes.isAssignable(Object.class, Integer.valueOf(1)));
        assertTrue(Classes.isAssignable(Object.class, Float[].class));

        assertTrue(Classes.isAssignable(Integer.class, NULL));
        assertFalse(Classes.isAssignable(Integer.class, Boolean.valueOf(true)));
        assertFalse(Classes.isAssignable(Integer.class, Short.valueOf((short) 1)));
        assertFalse(Classes.isAssignable(Integer.class, Character.valueOf((char) 1)));
        assertTrue(Classes.isAssignable(Integer.class, Integer.valueOf(1)));
        assertFalse(Classes.isAssignable(Integer.class, Long.valueOf(1L)));
        assertFalse(Classes.isAssignable(Integer.class, Float.valueOf(1f)));
        assertFalse(Classes.isAssignable(Integer.class, Double.valueOf(1.0)));
        assertFalse(Classes.isAssignable(Integer.class, new Object()));

        assertFalse(Classes.isAssignable(Boolean.class, 0));
        assertFalse(Classes.isAssignable(Short.class, '\u0001'));
        assertFalse(Classes.isAssignable(Integer.class, true));
        assertFalse(Classes.isAssignable(Integer.class, (byte) 1));
        assertFalse(Classes.isAssignable(Integer.class, '\u0001'));
        assertFalse(Classes.isAssignable(Long.class, 1)); // int
        assertFalse(Classes.isAssignable(Float.class, 1.0));
        assertFalse(Classes.isAssignable(Double.class, 1f));

        assertTrue(Classes.isAssignable(boolean[].class, new boolean[10]));
        assertFalse(Classes.isAssignable(boolean[].class, new Boolean[10]));
        assertTrue(Classes.isAssignable(int[].class, new int[10]));
        assertFalse(Classes.isAssignable(int[].class, new Integer[10]));
        assertTrue(Classes.isAssignable(double[].class, new double[10]));
        assertFalse(Classes.isAssignable(double[].class, new Double[10]));

        assertTrue(Classes.isAssignable(Boolean[].class, NULL));
        assertFalse(Classes.isAssignable(Boolean[].class, new boolean[10]));
        assertTrue(Classes.isAssignable(Boolean[].class, new Boolean[10]));
        assertFalse(Classes.isAssignable(Integer[].class, new int[10]));
        assertTrue(Classes.isAssignable(Integer[].class, new Integer[10]));
        assertFalse(Classes.isAssignable(Long[].class, new long[10]));
        assertTrue(Classes.isAssignable(Long[].class, new Long[10]));
        assertFalse(Classes.isAssignable(Float[].class, new float[10]));
        assertTrue(Classes.isAssignable(Float[].class, new Float[10]));
        assertFalse(Classes.isAssignable(Object[].class, new boolean[10]));
        assertTrue(Classes.isAssignable(Object[].class, new Boolean[10]));
        assertFalse(Classes.isAssignable(Object[].class, new int[10]));
        assertTrue(Classes.isAssignable(Object[].class, new Integer[10]));
        assertFalse(Classes.isAssignable(Object[].class, new double[10]));
        assertTrue(Classes.isAssignable(Object[].class, new Double[10]));

        assertTrue(Classes.isAssignable(Map.class, new HashMap<>()));
        assertTrue(Classes.isAssignable(Map.class, new AbstractMap<>() {
            @NotNull
            @Override
            public Set<Entry<Object, Object>> entrySet() {
                return null;
            }
        }));

        assertThrows(NullPointerException.class, () -> Classes.isAssignable(null, Integer.valueOf(1)));
    }

    @Test
    public void testAutoboxing() {
        assertEquals(Boolean.class, Classes.autoboxing(boolean.class));
        assertEquals(Byte.class, Classes.autoboxing(byte.class));
        assertEquals(Character.class, Classes.autoboxing(char.class));
        assertEquals(Short.class, Classes.autoboxing(short.class));
        assertEquals(Integer.class, Classes.autoboxing(int.class));
        assertEquals(Long.class, Classes.autoboxing(long.class));
        assertEquals(Float.class, Classes.autoboxing(float.class));
        assertEquals(Double.class, Classes.autoboxing(double.class));

        assertThrows(NullPointerException.class, () -> Classes.autoboxing(null));
        assertThrows(IllegalArgumentException.class, () -> Classes.autoboxing(Boolean.class));
        assertThrows(IllegalArgumentException.class, () -> Classes.autoboxing(Byte.class));
        assertThrows(IllegalArgumentException.class, () -> Classes.autoboxing(Character.class));
        assertThrows(IllegalArgumentException.class, () -> Classes.autoboxing(Short.class));
        assertThrows(IllegalArgumentException.class, () -> Classes.autoboxing(Integer.class));
        assertThrows(IllegalArgumentException.class, () -> Classes.autoboxing(Float.class));
        assertThrows(IllegalArgumentException.class, () -> Classes.autoboxing(Double.class));
        assertThrows(IllegalArgumentException.class, () -> Classes.autoboxing(Object.class));
        assertThrows(IllegalArgumentException.class, () -> Classes.autoboxing(int[].class));
        assertThrows(IllegalArgumentException.class, () -> Classes.autoboxing(double[].class));
        assertThrows(IllegalArgumentException.class, () -> Classes.autoboxing(List.class));
    }

    @Test
    public void testUnboxing() {
        assertEquals(boolean.class, Classes.unboxing(Boolean.class));
        assertEquals(byte.class, Classes.unboxing(Byte.class));
        assertEquals(char.class, Classes.unboxing(Character.class));
        assertEquals(short.class, Classes.unboxing(Short.class));
        assertEquals(int.class, Classes.unboxing(Integer.class));
        assertEquals(long.class, Classes.unboxing(Long.class));
        assertEquals(float.class, Classes.unboxing(Float.class));
        assertEquals(double.class, Classes.unboxing(Double.class));

        assertThrows(NullPointerException.class, () -> Classes.unboxing(null));
        assertThrows(IllegalArgumentException.class, () -> Classes.unboxing(boolean.class));
        assertThrows(IllegalArgumentException.class, () -> Classes.unboxing(byte.class));
        assertThrows(IllegalArgumentException.class, () -> Classes.unboxing(char.class));
        assertThrows(IllegalArgumentException.class, () -> Classes.unboxing(short.class));
        assertThrows(IllegalArgumentException.class, () -> Classes.unboxing(int.class));
        assertThrows(IllegalArgumentException.class, () -> Classes.unboxing(float.class));
        assertThrows(IllegalArgumentException.class, () -> Classes.unboxing(double.class));
        assertThrows(IllegalArgumentException.class, () -> Classes.unboxing(Object.class));
        assertThrows(IllegalArgumentException.class, () -> Classes.unboxing(int[].class));
        assertThrows(IllegalArgumentException.class, () -> Classes.unboxing(double[].class));
        assertThrows(IllegalArgumentException.class, () -> Classes.unboxing(List.class));
    }

    @Test
    public void testGetSimpleName_withInstance() {
        assertEquals("Boolean", Classes.getSimpleName(false));
        assertEquals("Boolean", Classes.getSimpleName(true));
        assertEquals("Byte", Classes.getSimpleName((byte) 1));
        assertEquals("Character", Classes.getSimpleName('\u0001'));
        assertEquals("Short", Classes.getSimpleName((short) 1));
        assertEquals("Integer", Classes.getSimpleName(0));
        assertEquals("Integer", Classes.getSimpleName(100));
        assertEquals("Long", Classes.getSimpleName(1L));
        assertEquals("Float", Classes.getSimpleName(1f));
        assertEquals("Double", Classes.getSimpleName(1.0));

        assertEquals("int[]", Classes.getSimpleName(new int[10]));
        assertEquals("Integer[]", Classes.getSimpleName(new Integer[10]));
        assertEquals("String[]", Classes.getSimpleName(new String[10]));
        assertEquals("Object[]", Classes.getSimpleName(new Object[10]));

        assertEquals("ArrayList", Classes.getSimpleName(new ArrayList<Float>()));
        assertEquals("HashMap", Classes.getSimpleName(new HashMap<String, String>()));

        assertEquals("TestClasses", Classes.getSimpleName(this));
        assertEquals("staticA", Classes.getSimpleName(new TestClassesData.staticA()));
        assertEquals("TestClasses$2", Classes.getSimpleName(new Cloneable() {
        }));

        assertThrows(NullPointerException.class, () -> Classes.getSimpleName((Object) null));
    }

    @Test
    public void testGetSimpleName_withClass() {
        assertEquals("boolean", Classes.getSimpleName(boolean.class));
        assertEquals("byte", Classes.getSimpleName(byte.class));
        assertEquals("char", Classes.getSimpleName(char.class));
        assertEquals("short", Classes.getSimpleName(short.class));
        assertEquals("int", Classes.getSimpleName(int.class));
        assertEquals("long", Classes.getSimpleName(long.class));
        assertEquals("float", Classes.getSimpleName(float.class));
        assertEquals("double", Classes.getSimpleName(double.class));

        assertEquals("Boolean", Classes.getSimpleName(Boolean.class));
        assertEquals("Byte", Classes.getSimpleName(Byte.class));
        assertEquals("Character", Classes.getSimpleName(Character.class));
        assertEquals("Short", Classes.getSimpleName(Short.class));
        assertEquals("Integer", Classes.getSimpleName(Integer.class));
        assertEquals("Long", Classes.getSimpleName(Long.class));
        assertEquals("Float", Classes.getSimpleName(Float.class));
        assertEquals("Double", Classes.getSimpleName(Double.class));

        assertEquals("int[]", Classes.getSimpleName(int[].class));
        assertEquals("Integer[]", Classes.getSimpleName(Integer[].class));
        assertEquals("String[]", Classes.getSimpleName(String[].class));
        assertEquals("Object[]", Classes.getSimpleName(Object[].class));

        assertEquals("ArrayList", Classes.getSimpleName(ArrayList.class));
        assertEquals("HashMap", Classes.getSimpleName(HashMap.class));

        assertEquals("TestClasses", Classes.getSimpleName(TestClasses.class));
        assertEquals("A", Classes.getSimpleName(TestClassesData.A.class));
        assertEquals("staticA", Classes.getSimpleName(TestClassesData.staticA.class));
        assertEquals("Cloneable", Classes.getSimpleName(Cloneable.class));

        assertThrows(NullPointerException.class, () -> Classes.getSimpleName((Object) null));
    }

    @Test
    public void testGetSignature() {
        assertThrows(NullPointerException.class, () -> Classes.getSignature(null));
    }
}