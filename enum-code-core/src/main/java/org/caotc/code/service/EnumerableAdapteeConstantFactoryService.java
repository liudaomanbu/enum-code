package org.caotc.code.service;

import com.google.common.collect.ImmutableSet;
import lombok.NonNull;
import org.caotc.code.factory.EnumerableAdapteeConstantFactory;

/**
 * @author caotc
 * @date 2021-10-08
 */
public interface EnumerableAdapteeConstantFactoryService {

     boolean support(@NonNull Class<?> type);

    boolean support(@NonNull Class<?> type, String group);

    @NonNull
    default <E> ImmutableSet<E> create(@NonNull Class<E> type) {
        return create(type, null);
    }

    @NonNull <E> ImmutableSet<E> create(@NonNull Class<E> type, String group);

    void addFactory(@NonNull EnumerableAdapteeConstantFactory<?> factory);

    void removeFactory(@NonNull EnumerableAdapteeConstantFactory<?> factory);
}
