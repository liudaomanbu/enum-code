package org.caotc.code.service;

import lombok.NonNull;

import java.util.Optional;

/**
 * @author caotc
 * @date 2021-10-08
 */
public interface EnumerableConverter {

    boolean support(@NonNull Class<?> type);

    @NonNull <C, E> Optional<E> valueOf(@NonNull Class<E> enumerableClass, @NonNull C code, @NonNull String group);

    @NonNull <C> C toCode(@NonNull Object enumerable);

}
