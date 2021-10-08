package org.caotc.code.service;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Lists;
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
    EnumerableConstantsFactoryService enumerableConstantsFactoryService;
    Map<Object, Object> enumerableToCodeMap = new HashMap<>();
    Table<Class<?>, Object, Enumerable<?>> enumerableClassToCodeToEnumerableTable = HashBasedTable.create();

    /**
     * <p>
     * 值映射为枚举
     * </p>
     *
     * @param enumClass 枚举类
     * @param value     枚举值
     * @param <E>       对应枚举
     * @throws IllegalArgumentException 如果该枚举类没有{@link Code}注解的属性和方法
     * @author caotc
     * @date 2021-08-01
     * @since 1.0.0
     */
    @NonNull
    public <C,E> Optional<E> findByClassAndCode(@NonNull Class<E> enumClass,@NonNull C value) {
        if (!enumerableClassToCodeToEnumerableTable.containsRow(enumClass)) {
            register(enumClass);
        }
        //put only,check in put
        @SuppressWarnings("unchecked")
        E e = (E) enumerableClassToCodeToEnumerableTable.get(enumClass,value);
        return Optional.ofNullable(e);
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
        EnumerableAdapterFactoryService enumerableAdapterFactoryService=null;
        enumerableAdapterFactoryService.adapt(e);
        if (!enumerableToCodeMap.containsKey(e)) {
            register(e.getClass());//todo class or superclass?
        }
        //调用方应该知道结果类型,由调用方决定返回类型,无需调用方强转
        return (C) enumerableToCodeMap.get(e);
    }

    private <E> void register(Class<E> enumerableClass) {
        EnumerableConstant<?> enumerableConstant = enumerableConstantsFactoryService.create(enumerableClass);
        for (Enumerable<?> enumerable : enumerableConstant) {
            enumerableClassToCodeToEnumerableTable.put(enumerableClass, enumerable.code(),enumerable);
            enumerableToCodeMap.put(enumerable, enumerable.code());
        }
    }
}
