package org.caotc.code.factory;

import com.google.common.collect.ImmutableSet;
import lombok.NonNull;

/**
 * @author caotc
 * @date 2021-08-17
 */
public interface EnumerableAdapteeConstantFactory<E> {
    @NonNull
    default <F extends E> ImmutableSet<F> create(@NonNull Class<F> type) {
        return create(type, null);
    }

    @NonNull <F extends E> ImmutableSet<F> create(@NonNull Class<F> type, String group);

    default boolean support(@NonNull Class<?> type) {
        return support(type, null);
    }

    boolean support(@NonNull Class<?> type, String group);
}
