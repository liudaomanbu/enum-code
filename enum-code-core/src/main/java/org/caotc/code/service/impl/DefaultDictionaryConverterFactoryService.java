package org.caotc.code.service.impl;

import com.google.common.collect.ImmutableSet;
import lombok.NonNull;
import lombok.Value;
import org.caotc.code.DictionaryConverter;
import org.caotc.code.factory.DictionaryConverterFactory;
import org.caotc.code.service.DictionaryConverterFactoryService;

import java.util.Collection;

/**
 * @author caotc
 * @date 2021-09-03
 */
@Value
public class DefaultDictionaryConverterFactoryService implements DictionaryConverterFactoryService {
    @NonNull
    Collection<DictionaryConverterFactory<?>> factories;

    @SuppressWarnings("unchecked")
    @Override
    public @NonNull <E, C> ImmutableSet<DictionaryConverter<C, E>> create(@NonNull Class<E> enumerableClass) {
        DictionaryConverterFactory<E> factory = (DictionaryConverterFactory<E>) factories.stream()
                .filter(enumerableConstantFactory -> enumerableConstantFactory.support(enumerableClass))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(enumerableClass + ":" + " not support create DictionaryConstant"));//todo
        ImmutableSet<DictionaryConverter<C, E>> dictionaryConverters = factory.create(enumerableClass);
        if (dictionaryConverters.isEmpty()) {
            throw new IllegalArgumentException(factory + " create " + enumerableClass + " converters result is empty");//todo
        }
        return dictionaryConverters;
    }

    @Override
    public boolean support(@NonNull Class<?> type) {
        return factories.stream()
                .anyMatch(factory -> factory.support(type));
    }

    @Override
    public void addFactory(@NonNull DictionaryConverterFactory<?> factory) {
        factories.add(factory);
    }

    @Override
    public void removeFactory(@NonNull DictionaryConverterFactory<?> factory) {
        factories.remove(factory);
    }
}
