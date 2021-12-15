package org.caotc.code.service.impl;

import com.google.common.collect.Maps;
import lombok.NonNull;
import lombok.Value;
import org.caotc.code.DictionaryConverter;
import org.caotc.code.service.DictionaryConverterFactoryService;
import org.caotc.code.service.DictionaryConverterService;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * @author caotc
 * @date 2021-11-19
 */
@Value
public class DefaultDictionaryConverterService implements DictionaryConverterService {
    Map<String, DictionaryConverter<?, ?>> groupToDictionaryConverter = Maps.newHashMap();
    DictionaryConverterFactoryService dictionaryConverterFactoryService;

    @Override
    public boolean containsDictionaryConverter(@NonNull String group) {
        return groupToDictionaryConverter.containsKey(group);
    }

    @Override
    public boolean addDictionaryConverterIfAbsent(@NonNull DictionaryConverter<?, ?> dictionaryConverter) {
        return Objects.isNull(groupToDictionaryConverter.putIfAbsent(dictionaryConverter.group(), dictionaryConverter));
    }

    @Override
    public void addDictionaryConverter(@NonNull DictionaryConverter<?, ?> dictionaryConverter) {
        groupToDictionaryConverter.put(dictionaryConverter.group(), dictionaryConverter);
    }

    @Override
    public void removeDictionaryConverter(@NonNull String group) {
        groupToDictionaryConverter.remove(group);
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <C, E> Optional<DictionaryConverter<C, E>> findDictionaryConverter(@NonNull String group) {
        return Optional.ofNullable((DictionaryConverter<C, E>) groupToDictionaryConverter.getOrDefault(group, null));
    }
}
