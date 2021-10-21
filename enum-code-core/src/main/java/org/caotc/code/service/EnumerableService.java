package org.caotc.code.service;

import lombok.NonNull;

import java.util.Optional;

/**
 * @author caotc
 * @date 2021-10-08
 */
public interface EnumerableService {

    default void evict(@NonNull Class<?> type) {
        evict(type, null);
    }

    void evict(@NonNull Class<?> type, String group);

    @NonNull
    default <C, E> Optional<E> valueOf(@NonNull Class<E> enumerableClass, @NonNull C code) {
        return valueOf(enumerableClass, code, null);
    }

    @NonNull <C, E> Optional<E> valueOf(@NonNull Class<E> enumerableClass, @NonNull C code, String group);

    @NonNull
    default <C, E> E valueOfExact(@NonNull Class<E> enumerableClass, @NonNull C code) {
        return valueOf(enumerableClass, code)
                //todo
                .orElseThrow(() -> new IllegalStateException(enumerableClass + " EnumerableConstant not contains enumerable of code" + code));
    }

    @NonNull
    default <C, E> E valueOfExact(@NonNull Class<E> enumerableClass, @NonNull C code, String group) {
        return valueOf(enumerableClass, code, group)
                //todo
                .orElseThrow(() -> new IllegalStateException(enumerableClass + " EnumerableConstant not contains enumerable of code" + code));
    }

    @NonNull
    default <C, E> Optional<E> valueOfNullable(Class<E> enumerableClass, C code) {
        return Optional.ofNullable(code)
                .flatMap(c -> valueOf(enumerableClass, c));
    }

    @NonNull
    default <C, E> Optional<E> valueOfNullable(Class<E> enumerableClass, C code, String group) {
        return Optional.ofNullable(code)
                .flatMap(c -> valueOf(enumerableClass, c, group));
    }

    @NonNull <C> C toCode(@NonNull Object enumerable);

    <C> C toCodeNullable(Object enumerable);
}
