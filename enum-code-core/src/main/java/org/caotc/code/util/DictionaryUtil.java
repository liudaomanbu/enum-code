package org.caotc.code.util;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.reflect.TypeToken;
import lombok.NonNull;
import lombok.experimental.UtilityClass;
import org.caotc.code.Dictionary;
import org.caotc.code.factory.CodeReaderDictionaryAdapterFactory;
import org.caotc.code.factory.DictionaryAdapteeConstantFactory;
import org.caotc.code.factory.DictionaryAdapteeConstantsFactoryToDictionaryConverterClassFactoryAdapter;
import org.caotc.code.factory.DictionaryConverterClassFactory;
import org.caotc.code.factory.EnumConstantFactory;
import org.caotc.code.service.DictionaryAdapteeConstantFactoryService;
import org.caotc.code.service.DictionaryAdapterFactoryService;
import org.caotc.code.service.DictionaryConverterFactoryService;
import org.caotc.code.service.DictionaryService;
import org.caotc.code.service.impl.DefaultDictionaryAdapteeConstantFactoryService;
import org.caotc.code.service.impl.DefaultDictionaryAdapterFactoryService;
import org.caotc.code.service.impl.DefaultDictionaryConverterFactoryService;
import org.caotc.code.service.impl.DefaultDictionaryGroupService;
import org.caotc.code.service.impl.DefaultDictionaryService;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Optional;

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
public class DictionaryUtil {
    //todo can replace
    private static final DictionaryAdapteeConstantFactoryService ENUMERABLE_ADAPTEE_CONSTANT_FACTORY_SERVICE = new DefaultDictionaryAdapteeConstantFactoryService(Lists.newArrayList(new EnumConstantFactory()));
    private static final DictionaryAdapterFactoryService ENUMERABLE_ADAPTER_FACTORY_SERVICE = new DefaultDictionaryAdapterFactoryService(Lists.newArrayList(new CodeReaderDictionaryAdapterFactory()));
    private static final DictionaryConverterClassFactory<Object> ENUMERABLE_CONSTANTS_FACTORY = new DictionaryAdapteeConstantsFactoryToDictionaryConverterClassFactoryAdapter(ENUMERABLE_ADAPTEE_CONSTANT_FACTORY_SERVICE, ENUMERABLE_ADAPTER_FACTORY_SERVICE);
    private static final DictionaryConverterFactoryService ENUMERABLE_CONSTANTS_FACTORY_SERVICE = new DefaultDictionaryConverterFactoryService(Lists.newArrayList(ENUMERABLE_CONSTANTS_FACTORY), Lists.newArrayList());
    private static final DictionaryService ENUMERABLE_SERVICE = new DefaultDictionaryService(ENUMERABLE_CONSTANTS_FACTORY_SERVICE, new DefaultDictionaryGroupService());

    public static boolean isEnumerable(@NonNull Class<?> type) {
        //todo super sub
        return (TypeToken.of(type).isSubtypeOf(Dictionary.class)
                || type.isEnum()
                || type.isAnnotationPresent(org.caotc.code.annotation.Dictionary.class))
                && ReflectUtil.findReader(type, org.caotc.code.annotation.Dictionary.Code.class).isPresent();
    }

    public static void checkEnumerable(@NonNull Class<?> type) {
        if (!isEnumerable(type)) {
            throw new IllegalArgumentException(type + "is not a Dictionary class");//todo
        }

        ImmutableSet<Method> annotatedCodeMethods = ReflectUtil.findAnnotatedMethod(type, org.caotc.code.annotation.Dictionary.Code.class);
        ImmutableSet<Field> annotatedCodeFields = ReflectUtil.findAnnotatedField(type, org.caotc.code.annotation.Dictionary.Code.class);
        if (annotatedCodeMethods.size() + annotatedCodeFields.size() > 1) {
            throw new IllegalArgumentException(type + "is a illegal Dictionary class");//todo
        }
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
    public static <C, E> Optional<E> valueOf(@NonNull String group, @NonNull C code) {
        return ENUMERABLE_SERVICE.valueOf(group, code);
    }

    @NonNull
    public static <C, E> E valueOfExact(@NonNull Class<E> enumerableClass, @NonNull C code) {
        return ENUMERABLE_SERVICE.valueOfExact(enumerableClass, code);
    }

    @NonNull
    public static <C, E> E valueOfExact(@NonNull String group, @NonNull C code) {
        return ENUMERABLE_SERVICE.valueOfExact(group, code);
    }

    @NonNull
    public static <C, E> Optional<E> valueOfNullable(Class<E> enumerableClass, C code) {
        return ENUMERABLE_SERVICE.valueOfNullable(enumerableClass, code);
    }

    @NonNull
    public static <C, E> Optional<E> valueOfNullable(String group, C code) {
        return ENUMERABLE_SERVICE.valueOfNullable(group, code);
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

    public static void evict(@NonNull String group) {
        ENUMERABLE_SERVICE.evict(group);
    }

    public static void addEnumerableAdapteeConstantFactory(@NonNull DictionaryAdapteeConstantFactory<?> factory) {
        ENUMERABLE_ADAPTEE_CONSTANT_FACTORY_SERVICE.addFactory(factory);
    }
}
