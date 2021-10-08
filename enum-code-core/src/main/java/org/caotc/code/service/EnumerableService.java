package org.caotc.code.service;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.caotc.code.Enumerable;
import org.caotc.code.EnumerableConstant;
import org.caotc.code.annotation.Code;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
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
    EnumerableConstantsFactoryService enumerableConstantsFactoryService;
    Map<Class<?>, EnumerableConstant<?>> classToEnumerableConstants = Maps.newConcurrentMap();

    /**
     * <p>
     * 值映射为枚举
     * </p>
     *
     * @param enumerableClass 枚举类
     * @param code     枚举值
     * @param <E>       对应枚举
     * @throws IllegalArgumentException 如果该枚举类没有{@link Code}注解的属性和方法
     * @author caotc
     * @date 2021-08-01
     * @since 1.0.0
     */
    @SuppressWarnings("unchecked")
    @NonNull
    public <C,E> Optional<E> find(@NonNull Class<E> enumerableClass, @NonNull C code) {
        if (!classToEnumerableConstants.containsKey(enumerableClass)) {
            classToEnumerableConstants.put(enumerableClass,enumerableConstantsFactoryService.create(enumerableClass));
        }
        return Optional.ofNullable((EnumerableConstant<C>)classToEnumerableConstants.get(enumerableClass))
                .flatMap(enumerableConstant -> enumerableConstant.findByCode(code))
                .map(enumerable -> (E)enumerable);
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
    @SuppressWarnings("unchecked")
    @NonNull
    public <C> C toCode(@NonNull Object e) {
        //调用方应该知道结果类型,由调用方决定返回类型,无需调用方强转
        Enumerable<C> enumerable=(e instanceof Enumerable)?(Enumerable<C>) e:enumerableAdapterFactoryService.adapt(e);
        return enumerable.code();
    }

}
