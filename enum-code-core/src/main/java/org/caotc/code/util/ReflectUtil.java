package org.caotc.code.util;

import com.google.common.base.CaseFormat;
import com.google.common.collect.ImmutableSet;
import lombok.NonNull;
import lombok.experimental.UtilityClass;
import org.caotc.code.CodeFieldReader;
import org.caotc.code.CodeMethodReader;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

/**
 * @author caotc
 * @date 2021-12-15
 */
@UtilityClass
public class ReflectUtil {

    @SuppressWarnings("unchecked")
    public static <E, C> Function<E, C> findReaderExact(@NonNull E enumerableAdaptee, @NonNull Class<? extends Annotation> annotationType) {
        return findReaderExact((Class<E>) enumerableAdaptee.getClass(), annotationType);
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public static <E, C> Function<E, C> findReaderExact(@NonNull Class<E> enumClass, @NonNull Class<? extends Annotation> annotationType) {
        return ReflectUtil.<E, C>findReader(enumClass, annotationType).get();
    }

    @SuppressWarnings("unchecked")
    public static <E, C> Optional<Function<E, C>> findReader(@NonNull E enumerableAdaptee, @NonNull Class<? extends Annotation> annotationType) {
        return findReader((Class<E>) enumerableAdaptee.getClass(), annotationType);
    }

    public static <E, C> Optional<Function<E, C>> findReader(@NonNull Class<E> enumClass, @NonNull Class<? extends Annotation> annotationType) {
        Optional<Function<E, C>> reader = findAnnotatedMethod(enumClass, annotationType)
                .stream()
                .<Function<E, C>>map(CodeMethodReader::new)
                .findAny();
        if (reader.isPresent()) {
            return reader;
        }
        reader = findAnnotatedField(enumClass, annotationType)
                .stream()
                .<Function<E, C>>map(CodeFieldReader::new)
                .findAny();
        if (reader.isPresent()) {
            return reader;
        }
        reader = findPropertyMethod(enumClass, annotationType.getSimpleName())
                .map(CodeMethodReader::new);
        if (reader.isPresent()) {
            return reader;
        }
        reader = findPropertyField(enumClass, annotationType.getSimpleName())
                .map(CodeFieldReader::new);
        return reader;
    }

    public static <E> ImmutableSet<Method> findAnnotatedMethod(Class<E> enumClass, Class<? extends Annotation> annotationType) {
        return Arrays.stream(enumClass.getDeclaredMethods())//todo
                //过滤出有EnumSimpleValue注解的属性
                .filter(method -> Objects.nonNull(method.getAnnotation(annotationType)))
                .collect(ImmutableSet.toImmutableSet());
    }

    public static <E> ImmutableSet<Field> findAnnotatedField(Class<E> enumClass, Class<? extends Annotation> annotationType) {
        return Arrays.stream(enumClass.getDeclaredFields())//todo
                //过滤出有EnumSimpleValue注解的属性
                .filter(field -> Objects.nonNull(field.getAnnotation(annotationType)))
                //将私有属性设为可以获取值
                .peek(field -> field.setAccessible(Boolean.TRUE))
                .collect(ImmutableSet.toImmutableSet());
    }

    public static <E> Optional<Method> findPropertyMethod(Class<E> enumClass, String fieldName) {
        return Arrays.stream(enumClass.getDeclaredMethods())
                .filter(method -> method.getParameterCount() == 0 && (CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, fieldName).equals(method.getName()) || ("get" + fieldName).equals(method.getName())))
                .findAny();
    }

    public static <E> Optional<Field> findPropertyField(Class<E> enumClass, String fieldName) {
        return Arrays.stream(enumClass.getDeclaredFields())
                .filter(field -> CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, fieldName).equals(field.getName()))
                //将私有属性设为可以获取值
                .peek(field -> field.setAccessible(Boolean.TRUE)).findAny();
    }
}
