package org.caotc.code.service;

import com.google.common.collect.ImmutableSet;
import lombok.NonNull;
import org.caotc.code.EnumerableConstant;
import org.caotc.code.factory.EnumerableConstantFactory;

/**
 * @author caotc
 * @date 2021-09-03
 */

public interface EnumerableConstantFactoryService {

    @NonNull
    ImmutableSet<String> groups(@NonNull Class<?> type);

    boolean support(@NonNull Class<?> type);

    boolean support(@NonNull Class<?> type, String group);

    @NonNull <E, C> EnumerableConstant<C, E> create(@NonNull Class<E> enumerableClass, String group);

    @NonNull
    default <E, C> ImmutableSet<EnumerableConstant<C, E>> createAll(@NonNull Class<E> enumerableClass) {
        if (groups(enumerableClass).isEmpty()) {
            throw new IllegalArgumentException(enumerableClass + " not support create EnumerableConstant");
        }
        return groups(enumerableClass).stream()
                .map(group -> this.<E, C>create(enumerableClass, group))
                .collect(ImmutableSet.toImmutableSet());
    }

    void addFactory(@NonNull EnumerableConstantFactory<?> factory);

    void removeFactory(@NonNull EnumerableConstantFactory<?> factory);

}
