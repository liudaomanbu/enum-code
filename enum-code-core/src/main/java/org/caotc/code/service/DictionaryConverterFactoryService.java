package org.caotc.code.service;

import com.google.common.collect.ImmutableSet;
import lombok.NonNull;
import org.caotc.code.DictionaryConverter;
import org.caotc.code.factory.DictionaryConverterFactory;

/**
 * @author caotc
 * @date 2021-09-03
 */

public interface DictionaryConverterFactoryService {

    boolean support(@NonNull Class<?> type);

    @NonNull <E, C> ImmutableSet<DictionaryConverter<C, E>> create(@NonNull Class<E> enumerableClass);

    void addFactory(@NonNull DictionaryConverterFactory<?> factory);

    void removeFactory(@NonNull DictionaryConverterFactory<?> factory);

}
