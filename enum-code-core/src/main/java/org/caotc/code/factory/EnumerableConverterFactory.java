package org.caotc.code.factory;

import lombok.NonNull;
import org.caotc.code.EnumerableConverter;

/**
 * @author caotc
 * @date 2021-11-08
 */
public interface EnumerableConverterFactory<E> {
    @NonNull <T extends E> EnumerableConverter<?, T> create(@NonNull Class<T> type);

    @NonNull <T extends E> EnumerableConverter<?, T> create(@NonNull String group);
}
