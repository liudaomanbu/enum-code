package org.caotc.code.util;

import com.google.common.base.CaseFormat;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.reflect.TypeToken;
import lombok.NonNull;
import lombok.experimental.UtilityClass;
import org.caotc.code.CodeFieldReader;
import org.caotc.code.CodeMethodReader;
import org.caotc.code.Dictionary;
import org.caotc.code.factory.CodeReaderEnumerableAdapterFactory;
import org.caotc.code.factory.EnumConstantFactory;
import org.caotc.code.factory.EnumerableAdapteeConstantFactory;
import org.caotc.code.factory.EnumerableAdapteeConstantsFactoryToEnumerableConstantFactoryAdapter;
import org.caotc.code.factory.EnumerableConstantFactory;
import org.caotc.code.service.EnumerableAdapteeConstantFactoryService;
import org.caotc.code.service.EnumerableAdapterFactoryService;
import org.caotc.code.service.EnumerableConstantFactoryService;
import org.caotc.code.service.EnumerableService;
import org.caotc.code.service.impl.DefaultEnumerableAdapteeConstantFactoryService;
import org.caotc.code.service.impl.DefaultEnumerableAdapterFactoryService;
import org.caotc.code.service.impl.DefaultEnumerableConstantFactoryService;
import org.caotc.code.service.impl.DefaultEnumerableService;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

/**
 * 基于{@link org.caotc.code.annotation.Dictionary.Code}注解的枚举工具类
 *
 * @author caotc
 * @date 2021-08-01
 * @see org.caotc.code.annotation.Dictionary.Code
 * @since 1.0.0
 **/
@SuppressWarnings("UnstableApiUsage")
@UtilityClass
public class EnumerableUtil {
    //todo can replace
    private static final EnumerableAdapteeConstantFactoryService ENUMERABLE_ADAPTEE_CONSTANT_FACTORY_SERVICE = new DefaultEnumerableAdapteeConstantFactoryService(Lists.newArrayList(new EnumConstantFactory()));
    private static final EnumerableAdapterFactoryService ENUMERABLE_ADAPTER_FACTORY_SERVICE = new DefaultEnumerableAdapterFactoryService(Lists.newArrayList(new CodeReaderEnumerableAdapterFactory()));
    private static final EnumerableConstantFactory<Object> ENUMERABLE_CONSTANTS_FACTORY = new EnumerableAdapteeConstantsFactoryToEnumerableConstantFactoryAdapter(ENUMERABLE_ADAPTEE_CONSTANT_FACTORY_SERVICE, ENUMERABLE_ADAPTER_FACTORY_SERVICE);
    private static final EnumerableConstantFactoryService ENUMERABLE_CONSTANTS_FACTORY_SERVICE = new DefaultEnumerableConstantFactoryService(Lists.newArrayList(ENUMERABLE_CONSTANTS_FACTORY));
    private static final EnumerableService ENUMERABLE_SERVICE = new DefaultEnumerableService(ENUMERABLE_CONSTANTS_FACTORY_SERVICE);

    public static boolean isEnumerable(@NonNull Class<?> type) {
        //todo super sub
        return (TypeToken.of(type).isSubtypeOf(Dictionary.class)
                || type.isEnum()
                || type.isAnnotationPresent(org.caotc.code.annotation.Dictionary.class))
                && findReader(type, org.caotc.code.annotation.Dictionary.Code.class).isPresent();
    }

    public static void checkEnumerable(@NonNull Class<?> type) {
        if (!isEnumerable(type)) {
            throw new IllegalArgumentException(type + "is not a Dictionary class");//todo
        }

        ImmutableSet<Method> annotatedCodeMethods = findAnnotatedMethod(type, org.caotc.code.annotation.Dictionary.Code.class);
        ImmutableSet<Field> annotatedCodeFields = findAnnotatedField(type, org.caotc.code.annotation.Dictionary.Code.class);
        if (annotatedCodeMethods.size() + annotatedCodeFields.size() > 1) {
            throw new IllegalArgumentException(type + "is a illegal Dictionary class");//todo
        }
    }

    @SuppressWarnings("unchecked")
    public static <E, C> Function<E, C> findReaderExact(@NonNull E enumerableAdaptee, @NonNull Class<? extends Annotation> annotationType) {
        return findReaderExact((Class<E>) enumerableAdaptee.getClass(), annotationType);
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public static <E, C> Function<E, C> findReaderExact(@NonNull Class<E> enumClass, @NonNull Class<? extends Annotation> annotationType) {
        return EnumerableUtil.<E, C>findReader(enumClass, annotationType).get();
    }

    @SuppressWarnings("unchecked")
    public static <E, C> Optional<Function<E, C>> findReader(@NonNull E enumerableAdaptee, @NonNull Class<? extends Annotation> annotationType) {
        return findReader((Class<E>) enumerableAdaptee.getClass(), annotationType);
    }

    public static <E, C> Optional<Function<E, C>> findReader(@NonNull Class<E> enumClass, @NonNull Class<? extends Annotation> annotationType) {
        Optional<Function<E, C>> codeReader = findAnnotatedMethod(enumClass, annotationType)
                .stream()
                .<Function<E, C>>map(CodeMethodReader::new)
                .findAny();
        if (codeReader.isPresent()) {
            return codeReader;
        }
        codeReader = findAnnotatedField(enumClass, annotationType)
                .stream()
                .<Function<E, C>>map(CodeFieldReader::new)
                .findAny();
        if (codeReader.isPresent()) {
            return codeReader;
        }
        codeReader = findPropertyMethod(enumClass, annotationType.getSimpleName())
                .map(CodeMethodReader::new);
        if (codeReader.isPresent()) {
            return codeReader;
        }
        codeReader = findPropertyField(enumClass, annotationType.getSimpleName())
                .map(CodeFieldReader::new);
        return codeReader;
    }

    private static <E> ImmutableSet<Method> findAnnotatedMethod(Class<E> enumClass, Class<? extends Annotation> annotationType) {
        return Arrays.stream(enumClass.getDeclaredMethods())//todo
                //过滤出有EnumSimpleValue注解的属性
                .filter(method -> Objects.nonNull(method.getAnnotation(annotationType)))
                .collect(ImmutableSet.toImmutableSet());
    }

    private static <E> ImmutableSet<Field> findAnnotatedField(Class<E> enumClass, Class<? extends Annotation> annotationType) {
        return Arrays.stream(enumClass.getDeclaredFields())//todo
                //过滤出有EnumSimpleValue注解的属性
                .filter(field -> Objects.nonNull(field.getAnnotation(annotationType)))
                //将私有属性设为可以获取值
                .peek(field -> field.setAccessible(Boolean.TRUE))
                .collect(ImmutableSet.toImmutableSet());
    }

    private static <E> Optional<Method> findPropertyMethod(Class<E> enumClass, String fieldName) {
        return Arrays.stream(enumClass.getDeclaredMethods())
                .filter(method -> method.getParameterCount() == 0 && (CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, fieldName).equals(method.getName()) || ("get" + fieldName).equals(method.getName())))
                .findAny();
    }

    private static <E> Optional<Field> findPropertyField(Class<E> enumClass, String fieldName) {
        return Arrays.stream(enumClass.getDeclaredFields())
                .filter(field -> CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, fieldName).equals(field.getName()))
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
     * @throws IllegalArgumentException 如果该枚举类没有{@link org.caotc.code.annotation.Dictionary.Code}注解的属性和方法
     * @author caotc
     * @date 2021-08-01
     * @since 1.0.0
     */
    @NonNull
    public static <C, E> Optional<E> valueOf(@NonNull Class<E> enumerableClass, @NonNull C code) {
        return ENUMERABLE_SERVICE.valueOf(enumerableClass, code);
    }

    @NonNull
    public static <C, E> Optional<E> valueOf(@NonNull Class<E> enumerableClass, @NonNull C code, String group) {
        return ENUMERABLE_SERVICE.valueOf(enumerableClass, code, group);
    }

    @NonNull
    public static <C, E> E valueOfExact(@NonNull Class<E> enumerableClass, @NonNull C code) {
        return ENUMERABLE_SERVICE.valueOfExact(enumerableClass, code);
    }

    @NonNull
    public static <C, E> E valueOfExact(@NonNull Class<E> enumerableClass, @NonNull C code, String group) {
        return ENUMERABLE_SERVICE.valueOfExact(enumerableClass, code, group);
    }

    @NonNull
    public static <C, E> Optional<E> valueOfNullable(Class<E> enumerableClass, C code) {
        return ENUMERABLE_SERVICE.valueOfNullable(enumerableClass, code);
    }

    @NonNull
    public static <C, E> Optional<E> valueOfNullable(Class<E> enumerableClass, C code, String group) {
        return ENUMERABLE_SERVICE.valueOfNullable(enumerableClass, code, group);
    }

    /**
     * 获取枚举对应的值
     *
     * @param e 枚举
     * @return 枚举对应的值
     * @throws IllegalArgumentException 如果该枚举类没有{@link org.caotc.code.annotation.Dictionary.Code}注解的属性和方法
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

    public static void evict(@NonNull Class<?> type) {
        ENUMERABLE_SERVICE.evict(type);
    }

    public static void evict(@NonNull Class<?> type, String group) {
        ENUMERABLE_SERVICE.evict(type, group);
    }

    public static void addEnumerableAdapteeConstantFactory(@NonNull EnumerableAdapteeConstantFactory<?> factory) {
        ENUMERABLE_ADAPTEE_CONSTANT_FACTORY_SERVICE.addFactory(factory);
    }
}
