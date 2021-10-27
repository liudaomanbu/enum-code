package org.caotc.code.factory;

import com.google.common.collect.ImmutableSet;
import lombok.NonNull;
import org.caotc.code.EnumerableConstant;

/**
 * @author caotc
 * @date 2021-08-17
 */
public interface EnumerableConstantFactory<E> {

    @NonNull
    ImmutableSet<String> groups(@NonNull Class<?> type);

    boolean support(@NonNull Class<?> type);

    boolean support(@NonNull Class<?> type, @NonNull String group);

    @NonNull <C, F extends E> EnumerableConstant<C, F> create(@NonNull Class<F> type, @NonNull String group);
}
