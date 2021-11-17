package org.caotc.code.service.impl;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;
import lombok.NonNull;
import lombok.Value;
import org.caotc.code.EnumerableConstant;
import org.caotc.code.annotation.Enumerable;
import org.caotc.code.common.ReaderConstant;
import org.caotc.code.service.EnumerableConstantFactoryService;
import org.caotc.code.service.EnumerableService;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * @author caotc
 * @date 2021-10-08
 */
@Value
public class DefaultEnumerableService implements EnumerableService {
    EnumerableConstantFactoryService enumerableConstantFactoryService;
    Table<Class<?>, String, EnumerableConstant<?, ?>> classToGroupToEnumerableConstant = HashBasedTable.create();
    Map<Object, Object> enumerableToCode = Maps.newHashMap();

    public void evict(@NonNull Class<?> type) {
        evict(type, null);
    }

    public void evict(@NonNull Class<?> type, String group) {
        String $group = Optional.ofNullable(group).orElse(ReaderConstant.DEFAULT_GROUP);
        if (classToGroupToEnumerableConstant.contains(type, $group)) {
            synchronized (this) {
                if (classToGroupToEnumerableConstant.contains(type, $group)) {
                    EnumerableConstant<?, ?> enumerableConstant = classToGroupToEnumerableConstant.remove(type, $group);
                    if (Objects.nonNull(enumerableConstant)) {
                        enumerableConstant.enumerableAdapteeToCode().keySet().forEach(enumerableToCode::remove);
                    }
                }
            }
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
     * @throws IllegalArgumentException 如果该枚举类没有{@link Enumerable.Code}注解的属性和方法
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
        String $group = Optional.ofNullable(group).orElse(ReaderConstant.DEFAULT_GROUP);
        initIfNecessary(enumerableClass, $group);
        EnumerableConstant<C, E> enumerableConstant = (EnumerableConstant<C, E>) classToGroupToEnumerableConstant.get(enumerableClass, $group);
        return Optional.ofNullable(enumerableConstant)
                .flatMap(e -> e.findAndUnWarpIfNecessary(code));
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
        initIfNecessary(enumerable.getClass());
        //调用方应该知道结果类型,由调用方决定返回类型,无需调用方强转
        return (C) enumerableToCode.get(enumerable);
    }

    public <C> C toCodeNullable(Object enumerable) {
        return Optional.ofNullable(enumerable)
                .map(this::<C>toCode)
                .orElse(null);
    }

    private <E> void initIfNecessary(@NonNull Class<E> enumerableClass) {
        if (!classToGroupToEnumerableConstant.containsRow(enumerableClass)) {
            synchronized (this) {
                if (!classToGroupToEnumerableConstant.containsRow(enumerableClass)) {
                    enumerableConstantFactoryService.createAll(enumerableClass)
                            .forEach(this::register);
                }
            }
        }
    }

    private <E> void initIfNecessary(@NonNull Class<E> enumerableClass, @NonNull String group) {
        if (!classToGroupToEnumerableConstant.contains(enumerableClass, group)) {
            synchronized (this) {
                if (!classToGroupToEnumerableConstant.contains(enumerableClass, group)) {
                    register(enumerableConstantFactoryService.create(enumerableClass, group));
                }
            }
        }
    }

    private void register(@NonNull EnumerableConstant<?, ?> enumerableConstant) {
        classToGroupToEnumerableConstant.put(enumerableConstant.originalType(), enumerableConstant.group(), enumerableConstant);
        enumerableToCode.putAll(enumerableConstant.enumerableAdapteeToCode());
    }
}
