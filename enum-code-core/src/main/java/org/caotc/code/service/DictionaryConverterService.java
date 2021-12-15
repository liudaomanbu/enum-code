package org.caotc.code.service;

import lombok.NonNull;
import org.caotc.code.DictionaryConverter;

import java.util.Optional;

/**
 * @author caotc
 * @date 2021-11-12
 */
public interface DictionaryConverterService {

    boolean containsDictionaryConverter(@NonNull String group);

    boolean addDictionaryConverterIfAbsent(@NonNull DictionaryConverter<?, ?> dictionaryConverter);

    void addDictionaryConverter(@NonNull DictionaryConverter<?, ?> dictionaryConverter);

    void removeDictionaryConverter(@NonNull String group);

    @NonNull <C, E> Optional<DictionaryConverter<C, E>> findDictionaryConverter(@NonNull String group);
}
