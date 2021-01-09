package club.smarti.java;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Modifier;

/**
 * Utils to simplify work with class types
 * *
 * Advantages:
 * – support primitive types
 * – avoid client code duplication
 * – embedded asserts
 */
@SuppressWarnings("RedundantIfStatement")
public class Classes {

    /**
     * Check if the class is a primitive Java type (e.g. int, float, boolean, ...)
     *
     * @param cls - class to check
     * @return true if it's a primitive type, false otherwise
     * @throws NullPointerException if the class is null
     */
    @Contract(pure = true, value = "null -> fail")
    public static boolean isPrimitive(Class<?> cls) {
        if (cls == null) {
            throw new NullPointerException("Null class");
        }
        return (cls == boolean.class || cls == byte.class || cls == short.class || cls == char.class || cls == int.class || cls == long.class || cls == float.class || cls == double.class);
    }

    /**
     * Check if the class is a primitive Java type wrapper (e.g. Integer, Float, Boolean, ...)
     *
     * @param cls - class to check
     * @return true if it's a Object wrapper for primitive type, false otherwise
     * @throws NullPointerException if the class is null
     */
    @Contract(pure = true, value = "null -> fail")
    public static boolean isPrimitiveObject(Class<?> cls) {
        if (cls == null) {
            throw new NullPointerException("Null class");
        }
        return (cls == Boolean.class || cls == Byte.class || cls == Short.class || cls == Character.class || cls == Integer.class || cls == Long.class || cls == Float.class || cls == Double.class);
    }

    /**
     * Check if the class is java array
     * In contradistinction to {@link Class#isArray()} it supports primitive types
     *
     * @param cls - class to check
     * @return true if it's an array, false otherwise
     * @throws NullPointerException if the class is null
     */
    @Contract(pure = true, value = "null -> fail")
    public static boolean isArray(Class<?> cls) {
        if (cls == null) {
            throw new NullPointerException("Null class");
        }
        if (cls == boolean[].class || cls == byte[].class || cls == short[].class || cls == char[].class || cls == int[].class || cls == long[].class || cls == float[].class || cls == double[].class) {
            return true;
        }
        return cls.isArray();
    }

    /**
     * Check if the class is defined as static
     *
     * @param cls - class to check
     * @return true if it's static, false otherwise
     * @throws NullPointerException if the class is null
     */
    @Contract(pure = true, value = "null -> fail")
    public static boolean isStatic(Class<?> cls) {
        if (cls == null) {
            throw new NullPointerException("Null class");
        }
        return (Modifier.isStatic(cls.getModifiers()));
    }

    /**
     * Check if one class can be assigned from another class
     * In contradistinction to {@link Class#isAssignableFrom(Class)} it supports primitive data types
     *
     * @param dest - destination class
     * @param src - source class
     * @param boxing - allow autoboxing/unboxing for primitive types
     * @return true if 'destination' class can be initialized with 'source' class, false otherwise
     * @throws NullPointerException if source class is null
     * @throws NullPointerException if destination class is null
     */
    @Contract(pure = true, value = "null, _, _ -> fail; _, null, _ -> fail")
    public static boolean isAssignable(Class<?> dest, Class<?> src, boolean boxing) {
        if (dest == null) {
            throw new NullPointerException("Null destination class");
        }
        if (src == null) {
            throw new NullPointerException("Null source class");
        }
        if (isPrimitive(src) && isPrimitive(dest)) {
            if (dest == boolean.class) {
                if (src == boolean.class) {
                    return true;
                }
            }
            else if (dest == byte.class) {
                if (src == byte.class) {
                    return true;
                }
            }
            else if (dest == char.class) {
                if (src == char.class) {
                    return true;
                }
            }
            else if (dest == short.class) {
                if (src == short.class || src == byte.class) {
                    return true;
                }
            }
            else if (dest == int.class) {
                if (src == int.class || src == short.class || src == char.class || src == byte.class) {
                    return true;
                }
            }
            else if (dest == long.class) {
                if (src == long.class || src == int.class || src == short.class || src == char.class || src == byte.class) {
                    return true;
                }
            }
            else if (dest == float.class) {
                if (src == float.class || src == long.class || src == int.class || src == short.class || src == char.class || src == byte.class) {
                    return true;
                }
            }
            else if (dest == double.class) {
                if (src == double.class || src == float.class || src == long.class || src == int.class || src == short.class || src == char.class || src == byte.class) {
                    return true;
                }
            }
            return false;
        }

        if (!boxing) {
            if (isPrimitive(src) || isPrimitive(dest)) {
                return false;
            }
        }
        else {
            if (isPrimitive(src)) {
                return isAssignable(dest, autoboxing(src), false);
            }
            else if (isPrimitiveObject(src) && isPrimitive(dest)) {
                return isAssignable(dest, unboxing(src), false);
            }
        }
        // Default checker (it doesn't support primitives):
        return dest.isAssignableFrom(src);
    }

    /**
     * Check if one class can be assigned from another class with autoboxing enabled
     *
     * @param dest - destination class
     * @param src - source class
     * @return true if 'destination' class can be initialized with 'source' class, false otherwise
     * @throws NullPointerException if source class is null
     * @throws NullPointerException if destination class is null
     * @see #isAssignable(Class, Object, boolean)
     */
    @Contract(pure = true, value = "null, _ -> fail; _, null -> fail")
    public static boolean isAssignable(Class<?> dest, Class<?> src) {
        return isAssignable(dest, src, true);
    }

    /**
     * Check if one class can be assigned with the object
     *
     * @param dest - destination class
     * @param src - source class
     * @param boxing - allow autoboxing/unboxing for primitive types
     * @return true if 'destination' class can be initialized with 'source' class, false otherwise
     * @throws NullPointerException if destination class is null
     * @see #isAssignable(Class, Object, boolean)
     */
    @Contract(pure = true, value = "null, _, _ -> fail")
    public static boolean isAssignable(Class<?> dest, Object src, boolean boxing) {
        if (dest == null) {
            throw new NullPointerException("Null destination class");
        }
        return (src == null ? !isPrimitive(dest) : isAssignable(dest, src.getClass(), boxing));
    }

    /**
     * Check if one class can be assigned with the object (unboxing enabled)
     *
     * @param dest - destination class
     * @param src - source class
     * @return true if 'destination' class can be initialized with 'source' class, false otherwise
     * @throws NullPointerException if destination class is null
     * @see #isAssignable(Class, Object, boolean)
     */
    @Contract(pure = true, value = "null, _ -> fail")
    public static boolean isAssignable(Class<?> dest, Object src) {
        return isAssignable(dest, src, true);
    }

    /**
     * Convert primitive type to corresponding Object wrapper.
     * E.g. int -> Integer
     *
     * @param cls - source class
     * @return wrapper class, never null
     * @throws NullPointerException if the class is null
     * @throws IllegalArgumentException if it's unsupported class
     */
    @NotNull
    @Contract(pure = true, value = "null -> fail")
    public static Class<?> autoboxing(Class<?> cls) {
        if (cls == null) {
            throw new NullPointerException("Null class");
        }
        if (!isPrimitive(cls)) {
            throw new IllegalArgumentException(String.valueOf(cls));
        }

        if (cls == boolean.class) {
            return Boolean.class;
        }
        else if (cls == byte.class) {
            return Byte.class;
        }
        else if (cls == char.class) {
            return Character.class;
        }
        else if (cls == short.class) {
            return Short.class;
        }
        else if (cls == int.class) {
            return Integer.class;
        }
        else if (cls == long.class) {
            return Long.class;
        }
        else if (cls == float.class) {
            return Float.class;
        }
        else if (cls == double.class) {
            return Double.class;
        }
        throw new IllegalArgumentException(String.valueOf(cls));
    }

    /**
     * Convert wrapper class to corresponding primitive type.
     * E.g. Integer -> int.
     *
     * @param cls - source class
     * @return primitive type, never null
     * @throws NullPointerException if the class is null
     * @throws IllegalArgumentException if it's primitive type or unsupported class
     */
    @NotNull
    @Contract(pure = true, value = "null -> fail")
    public static Class<?> unboxing(Class<?> cls) {
        if (cls == null) {
            throw new NullPointerException("Null class");
        }
        if (isPrimitive(cls)) {
            throw new IllegalArgumentException(String.valueOf(cls));
        }

        if (cls == Boolean.class) {
            return boolean.class;
        }
        else if (cls == Byte.class) {
            return byte.class;
        }
        else if (cls == Character.class) {
            return char.class;
        }
        else if (cls == Short.class) {
            return short.class;
        }
        else if (cls == Integer.class) {
            return int.class;
        }
        else if (cls == Long.class) {
            return long.class;
        }
        else if (cls == Float.class) {
            return float.class;
        }
        else if (cls == Double.class) {
            return double.class;
        }
        throw new IllegalArgumentException(String.valueOf(cls));
    }

    /**
     * Get object class name for debug purpose
     *
     * @param obj - the class instance
     * @return class name string, never null
     * @throws NullPointerException if the object is null
     */
    @NotNull
    @Contract(pure = true, value = "null -> fail")
    public static String getSimpleName(Object obj) {
        if (obj == null) {
            throw new NullPointerException("Null object");
        }
        Class<?> cls = obj.getClass();
        return getSimpleName(cls);
    }

    /**
     * Get class name for debug (logging) purpose
     * E.g. "100" -> "Integer"
     * E.g. "this" -> "Classes"
     *
     * @param cls - the target class
     * @return class name string, never null
     * @throws NullPointerException if the class is null
     */
    @NotNull
    @Contract(pure = true, value = "null -> fail")
    public static String getSimpleName(Class<?> cls) {
        if (cls == null) {
            throw new NullPointerException("Null class");
        }
        String name = cls.getSimpleName();
        if (name.isEmpty()) {
            name = cls.getName();
            int firstNamePos = name.lastIndexOf(".");
            if (firstNamePos >= 0) {
                firstNamePos++;
                name = name.substring(firstNamePos);
            }
        }
        return name;
    }

    /**
     * Get object signature for debug (logging) purpose
     * E.g. "this" -> "Classes@7d2f521a"
     *
     * @param obj - the class instance
     * @return object signature, never null
     * @throws NullPointerException if the object is null
     */
    @NotNull
    @Contract(pure = true, value = "null -> fail")
    public static String getSignature(@Nullable Object obj) {
        if (obj == null) {
            throw new NullPointerException("Null object");
        }
        String name = getSimpleName(obj);
        return String.format("%s@%08x", name, obj.hashCode());
    }
}