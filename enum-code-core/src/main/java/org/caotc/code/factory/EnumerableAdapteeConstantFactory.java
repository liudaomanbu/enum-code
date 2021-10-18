package org.caotc.code.factory;

import com.google.common.collect.ImmutableSet;
import lombok.NonNull;

/**
 * @author caotc
 * @date 2021-08-17
 */
public interface EnumerableAdapteeConstantFactory<E> {

    @NonNull <F extends E> ImmutableSet<F> create(@NonNull Class<F> type, @NonNull String group);

    boolean support(@NonNull Class<?> type, @NonNull String group);
}
