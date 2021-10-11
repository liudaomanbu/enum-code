package org.caotc.code.service;

import com.google.common.collect.Maps;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.caotc.code.Enumerable;
import org.caotc.code.EnumerableConstant;
import org.caotc.code.adapter.EnumerableAdapter;
import org.caotc.code.annotation.Code;

import java.util.Map;
import java.util.Optional;

/**
 * @author caotc
 * @date 2021-10-08
 */
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class EnumerableService {
    EnumerableAdapterFactoryService enumerableAdapterFactoryService;
    EnumerableConstantFactoryService enumerableConstantFactoryService;
    Map<Class<?>, EnumerableConstant<?>> classToEnumerableConstants = Maps.newConcurrentMap();

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
    @SuppressWarnings("unchecked")
    @NonNull
    public <C, E> Optional<E> valueOf(@NonNull Class<E> enumerableClass, @NonNull C code) {
        if (!classToEnumerableConstants.containsKey(enumerableClass)) {
            classToEnumerableConstants.put(enumerableClass, enumerableConstantFactoryService.create(enumerableClass));
        }
        EnumerableConstant<C> enumerableConstant = (EnumerableConstant<C>) classToEnumerableConstants.get(enumerableClass);
        return enumerableConstant.find(code)
                .map(enumerable -> unWarpIfNecessary(enumerableClass,enumerable));
    }

    @NonNull
    public <C, E> E valueOfExact(@NonNull Class<E> enumerableClass, @NonNull C code) {
        return valueOf(enumerableClass, code)
                //todo
                .orElseThrow(() -> new IllegalStateException(enumerableClass + " EnumerableConstant not contains enumerable of code" + code));
    }

    @NonNull
    public <C, E> Optional<E> valueOfNullable(@NonNull Class<E> enumerableClass, C code) {
        return Optional.ofNullable(code)
                .flatMap(c -> this.valueOf(enumerableClass, c));
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

    @SuppressWarnings("unchecked")
    private <E> E unWarpIfNecessary(@NonNull Class<E> enumerableClass, @NonNull Object enumerable) {
        if (enumerableClass.isInstance(enumerable)) {
            return (E) enumerable;
        }
        if (enumerable instanceof EnumerableAdapter) {
            return ((EnumerableAdapter<E, ?>) enumerable).adaptee();
        }
        //todo
        throw new IllegalStateException(enumerableClass + " EnumerableConstant enumerable class is "+enumerable.getClass());
    }

}
