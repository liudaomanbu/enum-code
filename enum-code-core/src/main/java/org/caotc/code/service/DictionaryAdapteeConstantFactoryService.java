package org.caotc.code.service;

import com.google.common.collect.ImmutableSet;
import lombok.NonNull;
import org.caotc.code.factory.DictionaryAdapteeConstantFactory;

/**
 * @author caotc
 * @date 2021-10-08
 */
public interface DictionaryAdapteeConstantFactoryService {

    boolean support(@NonNull Class<?> type);

    @NonNull <E> ImmutableSet<E> create(@NonNull Class<E> type);

    void addFactory(@NonNull DictionaryAdapteeConstantFactory<?> factory);

    void removeFactory(@NonNull DictionaryAdapteeConstantFactory<?> factory);
}
