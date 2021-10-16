package org.caotc.code.factory;

import lombok.NonNull;
import org.caotc.code.EnumerableConstant;

/**
 * @author caotc
 * @date 2021-08-17
 */
public interface EnumerableConstantFactory<E> {
    @NonNull
    default <C> EnumerableConstant<C> create(@NonNull Class<? extends E> type) {
        return create(type, null);
    }

    @NonNull <C> EnumerableConstant<C> create(@NonNull Class<? extends E> type,  String group);

    boolean support(@NonNull Class<?> type,  String group);

    default boolean support(@NonNull Class<?> type) {
        return support(type, null);
    }
}
