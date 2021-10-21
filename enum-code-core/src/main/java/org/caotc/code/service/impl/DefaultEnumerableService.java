package org.caotc.code.service.impl;

import com.google.common.collect.Maps;
import lombok.NonNull;
import lombok.Value;
import org.caotc.code.Enumerable;
import org.caotc.code.EnumerableConstant;
import org.caotc.code.annotation.Code;
import org.caotc.code.common.GroupConstant;
import org.caotc.code.service.EnumerableAdapterFactoryService;
import org.caotc.code.service.EnumerableConstantFactoryService;
import org.caotc.code.service.EnumerableService;

import java.util.Map;
import java.util.Optional;

/**
 * @author caotc
 * @date 2021-10-08
 */
@Value
public class DefaultEnumerableService implements EnumerableService {
    EnumerableAdapterFactoryService enumerableAdapterFactoryService;
    EnumerableConstantFactoryService enumerableConstantFactoryService;
    Map<EnumerablePair<?>, EnumerableConstant<?>> classToEnumerableConstants = Maps.newConcurrentMap();

    public void evict(@NonNull Class<?> type) {
        evict(type, null);
    }

    public void evict(@NonNull Class<?> type, String group) {
        classToEnumerableConstants.remove(EnumerablePair.create(type, Optional.ofNullable(group).orElse(GroupConstant.DEFAULT)));
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
    public <C, E> Optional<E> valueOf(@NonNull Class<E> enumerableClass, @NonNull C code) {
        return valueOf(enumerableClass, code, null);
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public <C, E> Optional<E> valueOf(@NonNull Class<E> enumerableClass, @NonNull C code, String group) {
        EnumerablePair<E> pair = EnumerablePair.create(enumerableClass, Optional.ofNullable(group).orElse(GroupConstant.DEFAULT));
        if (!classToEnumerableConstants.containsKey(pair)) {
            classToEnumerableConstants.put(pair, enumerableConstantFactoryService.create(enumerableClass, group));
        }
        EnumerableConstant<C> enumerableConstant = (EnumerableConstant<C>) classToEnumerableConstants.get(pair);
        return enumerableConstant.findAndUnWarpIfNecessary(code);
    }

    @NonNull
    public <C, E> E valueOfExact(@NonNull Class<E> enumerableClass, @NonNull C code) {
        return valueOf(enumerableClass, code)
                //todo
                .orElseThrow(() -> new IllegalStateException(enumerableClass + " EnumerableConstant not contains enumerable of code" + code));
    }

    @NonNull
    public <C, E> E valueOfExact(@NonNull Class<E> enumerableClass, @NonNull C code, String group) {
        return valueOf(enumerableClass, code, group)
                //todo
                .orElseThrow(() -> new IllegalStateException(enumerableClass + " EnumerableConstant not contains enumerable of code" + code));
    }

    @NonNull
    public <C, E> Optional<E> valueOfNullable(Class<E> enumerableClass, C code) {
        return Optional.ofNullable(code)
                .flatMap(c -> valueOf(enumerableClass, c));
    }

    @NonNull
    public <C, E> Optional<E> valueOfNullable(Class<E> enumerableClass, C code, String group) {
        return Optional.ofNullable(code)
                .flatMap(c -> valueOf(enumerableClass, c, group));
    }

    /**
     * 获取枚举对应的值
     *
     * @param enumerable 枚举
     * @return 枚举对应的值
     * @throws IllegalArgumentException 如果该枚举类没有{@link Code}注解的属性和方法
     * @author caotc
     * @date 2021-08-01
     * @since 1.0.0
     */
    @SuppressWarnings("unchecked")
    @NonNull
    public <C> C toCode(@NonNull Object enumerable) {
        //调用方应该知道结果类型,由调用方决定返回类型,无需调用方强转
        if (enumerable instanceof Enumerable) {
            return ((Enumerable<C>) enumerable).code();
        }
        return enumerableAdapterFactoryService.<C>adapt(enumerable).code();
    }

    public <C> C toCodeNullable(Object enumerable) {
        return Optional.ofNullable(enumerable)
                .map(this::<C>toCode)
                .orElse(null);
    }

    /**
     * @author caotc
     * @date 2021-08-20
     */
    @Value(staticConstructor = "create")
    static class EnumerablePair<T> {
        @NonNull
        Class<T> type;
        @NonNull
        String group;
    }
}
