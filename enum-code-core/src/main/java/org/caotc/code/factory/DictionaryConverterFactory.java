package org.caotc.code.factory;

import lombok.NonNull;
import org.caotc.code.DictionaryConverter;

/**
 * @author caotc
 * @date 2021-11-08
 */
public interface DictionaryConverterFactory<E> {
    @NonNull <T extends E> DictionaryConverter<?, T> create(@NonNull Class<T> type);

    @NonNull <T extends E> DictionaryConverter<?, T> create(@NonNull String group);
}
