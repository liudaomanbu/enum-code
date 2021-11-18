package org.caotc.code.service;

import com.google.common.collect.ImmutableSet;
import lombok.NonNull;
import org.caotc.code.factory.DictionaryAdapteeConstantFactory;

/**
 * @author caotc
 * @date 2021-10-08
 */
public interface DictionaryAdapteeConstantFactoryService {

    @NonNull
    ImmutableSet<String> groups(@NonNull Class<?> type);

    boolean support(@NonNull Class<?> type);

    boolean support(@NonNull Class<?> type, String group);

    @NonNull
    default <E> ImmutableSet<E> create(@NonNull Class<E> type) {
        return create(type, null);
    }

    @NonNull <E> ImmutableSet<E> create(@NonNull Class<E> type, String group);

    void addFactory(@NonNull DictionaryAdapteeConstantFactory<?> factory);

    void removeFactory(@NonNull DictionaryAdapteeConstantFactory<?> factory);
}
