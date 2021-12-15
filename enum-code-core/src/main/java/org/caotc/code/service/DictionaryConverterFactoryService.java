package org.caotc.code.service;

import com.google.common.collect.ImmutableSet;
import lombok.NonNull;
import org.caotc.code.DictionaryConverter;
import org.caotc.code.factory.DictionaryConverterClassFactory;
import org.caotc.code.factory.DictionaryConverterFactory;

/**
 * @author caotc
 * @date 2021-09-03
 */

public interface DictionaryConverterFactoryService {

    boolean support(@NonNull Class<?> type);

    boolean support(@NonNull String group);

    @NonNull <E, C> ImmutableSet<DictionaryConverter<C, E>> create(@NonNull Class<E> enumerableClass);

    @NonNull <E, C> DictionaryConverter<C, E> create(@NonNull String group);

    void addFactory(@NonNull DictionaryConverterClassFactory<?> factory);

    void addFactory(@NonNull DictionaryConverterFactory<?> factory);

    void removeFactory(@NonNull DictionaryConverterClassFactory<?> factory);

    void removeFactory(@NonNull DictionaryConverterFactory<?> factory);

}
