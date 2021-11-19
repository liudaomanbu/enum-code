package org.caotc.code.factory;

import com.google.common.collect.ImmutableSet;
import lombok.NonNull;
import org.caotc.code.DictionaryConverter;

/**
 * @author caotc
 * @date 2021-08-17
 */
public interface DictionaryConverterFactory<E> {

    boolean support(@NonNull Class<?> type);

    @NonNull <C, F extends E> ImmutableSet<DictionaryConverter<C, F>> create(@NonNull Class<F> type);
}
