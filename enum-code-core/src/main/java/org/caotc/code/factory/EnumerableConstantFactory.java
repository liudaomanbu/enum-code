package org.caotc.code.factory;

import com.google.common.collect.ImmutableSet;
import lombok.NonNull;
import org.caotc.code.EnumerableConstant;

/**
 * @author caotc
 * @date 2021-08-17
 */
public interface EnumerableConstantFactory<E> {

    boolean support(@NonNull Class<?> type);

    boolean support(@NonNull Class<?> type, @NonNull String group);

    @NonNull
    ImmutableSet<String> groups(@NonNull Class<?> type);

    @NonNull <C> EnumerableConstant<C> create(@NonNull Class<? extends E> type, @NonNull String group);
}
