package org.caotc.code.factory;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSetMultimap;
import lombok.NonNull;

import java.util.function.Function;

/**
 * @author caotc
 * @date 2021-08-17
 */
public interface EnumerableAdapteeConstantFactory<E> {
    @NonNull
    ImmutableSet<String> groups(@NonNull Class<?> type);

    default boolean support(@NonNull Class<?> type) {
        return !groups(type).isEmpty();
    }

    default boolean support(@NonNull Class<?> type, @NonNull String group) {
        return groups(type).contains(group);
    }

    @NonNull <F extends E> ImmutableSet<F> create(@NonNull Class<F> type, @NonNull String group);

    default @NonNull <F extends E> ImmutableSetMultimap<String, F> createAll(@NonNull Class<F> type) {
        return groups(type).stream()
                .collect(ImmutableSetMultimap.flatteningToImmutableSetMultimap(Function.identity(), group -> create(type, group).stream()));
    }
}
