package org.caotc.code.util;

import com.google.common.collect.Lists;
import com.google.common.reflect.TypeToken;
import lombok.NonNull;
import lombok.experimental.UtilityClass;
import org.caotc.code.CodeFieldReader;
import org.caotc.code.CodeMethodReader;
import org.caotc.code.CodeReader;
import org.caotc.code.Enumerable;
import org.caotc.code.annotation.Code;
import org.caotc.code.factory.CodeReaderEnumerableAdapterFactory;
import org.caotc.code.factory.EnumConstantFactory;
import org.caotc.code.factory.EnumerableAdapteeConstantsFactoryToEnumerableConstantFactoryAdapter;
import org.caotc.code.factory.EnumerableConstantFactory;
import org.caotc.code.service.EnumerableAdapteeConstantFactoryService;
import org.caotc.code.service.EnumerableAdapterFactoryService;
import org.caotc.code.service.EnumerableConstantFactoryService;
import org.caotc.code.service.EnumerableService;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

/**
 * 基于{@link Code}注解的枚举工具类
 *
 * @author caotc
 * @date 2021-08-01
 * @see Code
 * @since 1.0.0
 **/
@SuppressWarnings("UnstableApiUsage")
@UtilityClass
public class EnumerableUtil {
    private static final EnumerableAdapteeConstantFactoryService ENUMERABLE_ADAPTEE_CONSTANT_FACTORY_SERVICE = new EnumerableAdapteeConstantFactoryService(Lists.newArrayList(new EnumConstantFactory()));
    private static final EnumerableAdapterFactoryService ENUMERABLE_ADAPTER_FACTORY_SERVICE = new EnumerableAdapterFactoryService(Lists.newArrayList(new CodeReaderEnumerableAdapterFactory()));
    private static final EnumerableConstantFactory<Object> ENUMERABLE_CONSTANTS_FACTORY = new EnumerableAdapteeConstantsFactoryToEnumerableConstantFactoryAdapter(ENUMERABLE_ADAPTEE_CONSTANT_FACTORY_SERVICE, ENUMERABLE_ADAPTER_FACTORY_SERVICE);
    private static final EnumerableConstantFactoryService ENUMERABLE_CONSTANTS_FACTORY_SERVICE = new EnumerableConstantFactoryService(Lists.newArrayList(ENUMERABLE_CONSTANTS_FACTORY));
    private static final EnumerableService ENUMERABLE_SERVICE = new EnumerableService(ENUMERABLE_ADAPTER_FACTORY_SERVICE, ENUMERABLE_CONSTANTS_FACTORY_SERVICE);

    public static boolean isEnumerable(@NonNull Class<?> type) {
        return TypeToken.of(type).isSubtypeOf(Enumerable.class)
                || ((type.isEnum() || type.isAnnotationPresent(org.caotc.code.annotation.Enumerable.class))
                && findCodeReader(type).isPresent());
    }

    public static void checkEnumerable(@NonNull Class<?> type) {
        if (!isEnumerable(type)) {
            throw new IllegalArgumentException(type + "is not a Enumerable class");//todo
        }
    }

    @SuppressWarnings("unchecked")
    public static <E, C> CodeReader<E, C> findCodeReaderExact(@NonNull E enumerableAdaptee) {
        return findCodeReaderExact((Class<E>) enumerableAdaptee.getClass());
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public static <E, C> CodeReader<E, C> findCodeReaderExact(@NonNull Class<E> enumClass) {
        return EnumerableUtil.<E, C>findCodeReader(enumClass).get();
    }

    public static <E, C> Optional<CodeReader<E, C>> findCodeReader(@NonNull Class<E> enumClass) {
        Optional<CodeReader<E, C>> codeReader = findAnnotatedCodeMethod(enumClass)
                .map(CodeMethodReader::new);
        if (codeReader.isPresent()) {
            return codeReader;
        }
        codeReader = findAnnotatedCodeField(enumClass)
                .map(CodeFieldReader::new);
        if (codeReader.isPresent()) {
            return codeReader;
        }
        codeReader = findCodeMethod(enumClass)
                .map(CodeMethodReader::new);
        if (codeReader.isPresent()) {
            return codeReader;
        }
        codeReader = findCodeField(enumClass)
                .map(CodeFieldReader::new);
        return codeReader;
    }

    private static <E> Optional<Method> findAnnotatedCodeMethod(Class<E> enumClass) {
        return Arrays.stream(enumClass.getDeclaredMethods())
                //过滤出有EnumSimpleValue注解的属性
                .filter(method -> Objects.nonNull(method.getAnnotation(Code.class)))
                .findAny();
    }

    private static <E> Optional<Field> findAnnotatedCodeField(Class<E> enumClass) {
        return Arrays.stream(enumClass.getDeclaredFields())
                //过滤出有EnumSimpleValue注解的属性
                .filter(field -> Objects.nonNull(field.getAnnotation(Code.class)))
                //将私有属性设为可以获取值
                .peek(field -> field.setAccessible(Boolean.TRUE)).findAny();
    }

    private static <E> Optional<Method> findCodeMethod(Class<E> enumClass) {
        return Arrays.stream(enumClass.getDeclaredMethods())
                .filter(method -> method.getParameterCount() == 0 && ("code".equals(method.getName()) || "getCode".equals(method.getName())))
                .findAny();
    }

    private static <E> Optional<Field> findCodeField(Class<E> enumClass) {
        return Arrays.stream(enumClass.getDeclaredFields())
                .filter(field -> "code".equals(field.getName()))
                //将私有属性设为可以获取值
                .peek(field -> field.setAccessible(Boolean.TRUE)).findAny();
    }

    /**
     * <p>
     * 值映射为枚举
     * </p>
     *
     * @param enumerableClass 枚举类
     * @param code            枚举值
     * @param <E>             对应枚举
     * @throws IllegalArgumentException 如果该枚举类没有{@link Code}注解的属性和方法
     * @author caotc
     * @date 2021-08-01
     * @since 1.0.0
     */
    @NonNull
    public static <C, E> Optional<E> valueOf(@NonNull Class<E> enumerableClass, @NonNull C code) {
        return ENUMERABLE_SERVICE.valueOf(enumerableClass, code);
    }

    @NonNull
    public static <C, E> E valueOfExact(@NonNull Class<E> enumerableClass, @NonNull C code) {
        return ENUMERABLE_SERVICE.valueOfExact(enumerableClass, code);
    }

    @NonNull
    public static <C, E> Optional<E> valueOfNullable(@NonNull Class<E> enumerableClass, C code) {
        return ENUMERABLE_SERVICE.valueOfNullable(enumerableClass, code);
    }

    /**
     * 获取枚举对应的值
     *
     * @param e 枚举
     * @return 枚举对应的值
     * @throws IllegalArgumentException 如果该枚举类没有{@link Code}注解的属性和方法
     * @author caotc
     * @date 2021-08-01
     * @since 1.0.0
     */
    @NonNull
    public static <C> C toCode(@NonNull Object e) {
        return ENUMERABLE_SERVICE.toCode(e);
    }

    public static <C> C toCodeNullable(Object e) {
        return ENUMERABLE_SERVICE.toCodeNullable(e);
    }
}
