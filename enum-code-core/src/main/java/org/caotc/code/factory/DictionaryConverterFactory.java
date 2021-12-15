package org.caotc.code.factory;

import lombok.NonNull;
import org.caotc.code.DictionaryConverter;

/**
 * @author caotc
 * @date 2021-08-17
 */
public interface DictionaryConverterFactory<E> {

    boolean support(@NonNull String group);

    @NonNull <C, F extends E> DictionaryConverter<C, F> create(@NonNull String group);
}
