import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

/**
 * 基于EnumSimpleValue注解的枚举工具类
 *
 * @author caotc
 * @date 2018-10-09
 * @see com.guahao.convention.EnumSimpleValue
 * @since 1.0.8
 **/
public class EnumSimpleValueUtils {

    private static final Map<Class<?>, Map<Object, Enum<?>>> ENUM_TYPE_TO_ENUM_VALUE_TO_ENUM_TABLE = new HashMap<>();
    private static final Map<Enum<?>, Object> ENUM_TO_ENUM_VALUE_MAP = new HashMap<>();

    /**
     * <p>
     * 值映射为枚举
     * </p>
     *
     * @param enumClass 枚举类
     * @param value     枚举值
     * @param <E>       对应枚举
     * @throws IllegalArgumentException 如果该枚举类没有EnumSimpleValue注解的属性和方法
     * @author caotc
     * @date 2018-11-27
     * @since 1.0.8
     */
    public static <E extends Enum<E>> E valueOf(Class<E> enumClass,Object value) {
        if(Objects.isNull(value)){
            return null;
        }
        if(Objects.isNull(enumClass)){
            throw new IllegalArgumentException("enumClass can't be null");
        }
        if (!ENUM_TYPE_TO_ENUM_VALUE_TO_ENUM_TABLE.containsKey(enumClass)) {
            initEnumClass(enumClass);
        }
        //put only,check in put
        @SuppressWarnings("unchecked")
        E e = (E) ENUM_TYPE_TO_ENUM_VALUE_TO_ENUM_TABLE.get(enumClass).get(value);
        if (Objects.isNull(e)) {
            throw new IllegalArgumentException(value + " can't convert to " + enumClass);
        }
        return e;
    }

    /**
     * 获取枚举对应的值
     *
     * @param e 枚举
     * @return 枚举对应的值
     * @throws IllegalArgumentException 如果该枚举类没有EnumSimpleValue注解的属性和方法
     * @author caotc
     * @date 2018-11-27
     * @since 1.0.8
     */
    @SuppressWarnings("unchecked")
    public static <E extends Enum<E>,R> R toSimpleValue(E e) {
        if(Objects.isNull(e)){
            return null;
        }
        if (!ENUM_TO_ENUM_VALUE_MAP.containsKey(e)) {
            //noinspection unchecked
            Class<? extends Enum> enumClass = e.getClass();
            //当该枚举有重写方法时,该枚举的类型为匿名子类,而不是真正的枚举类,所以需要再获取父类
            if(enumClass.isAnonymousClass()){
                enumClass= (Class<? extends Enum>) enumClass.getSuperclass();
            }
            initEnumClass(enumClass);
        }
        //调用方应该知道结果类型,由调用方决定返回类型,无需调用方强转
        return (R) ENUM_TO_ENUM_VALUE_MAP.get(e);
    }

    private synchronized static <E extends Enum<E>> void initEnumClass(Class<E> enumClass) {
        //double check
        if(!ENUM_TYPE_TO_ENUM_VALUE_TO_ENUM_TABLE.containsKey(enumClass)){
            Optional<Method> getValueMethodOptional = findSimpleValueMethod(enumClass);
            Optional<Field> getValueFieldOptional = findSimpleValueField(enumClass);
            if (!getValueMethodOptional.isPresent() && !getValueFieldOptional.isPresent()) {
                throw new IllegalArgumentException(
                        enumClass + " must have a method or field with the annotation " + EnumSimpleValue.class);
            }
            getValueMethodOptional.ifPresent(method -> register(enumClass, enumConstant -> {
                try {
                    return method.invoke(enumConstant);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }));
            getValueFieldOptional.ifPresent(field -> register(enumClass, enumConstant -> {
                try {
                    return field.get(enumConstant);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }));
        }
    }

    private static <E extends Enum<E>> void register(Class<E> enumClass, Function<E, Object> getValueFunction) {
        E[] enumConstants = enumClass.getEnumConstants();
        Map<Object, Enum<?>> valueToEnumMap = new HashMap<>();
        for (E enumConstant : enumConstants) {
            Object enumConstantValue = getValueFunction.apply(enumConstant);
            valueToEnumMap.put(enumConstantValue, enumConstant);
            ENUM_TO_ENUM_VALUE_MAP.put(enumConstant, enumConstantValue);
        }
        ENUM_TYPE_TO_ENUM_VALUE_TO_ENUM_TABLE.put(enumClass, Collections.unmodifiableMap(valueToEnumMap));
    }

    private static <E extends Enum<E>> Optional<Method> findSimpleValueMethod(Class<E> enumClass) {
        return Arrays.stream(enumClass.getDeclaredMethods())
                //过滤出有EnumSimpleValue注解的属性
                .filter(method -> Objects.nonNull(method.getAnnotation(EnumSimpleValue.class)))
                .findAny();
    }

    private static <E extends Enum<E>> Optional<Field> findSimpleValueField(Class<E> enumClass) {
        return Arrays.stream(enumClass.getDeclaredFields())
                //过滤出有EnumSimpleValue注解的属性
                .filter(field -> Objects.nonNull(field.getAnnotation(EnumSimpleValue.class)))
                //将私有属性设为可以获取值
                .peek(field -> field.setAccessible(Boolean.TRUE)).findAny();
    }

    private EnumSimpleValueUtils() {
    }
}
