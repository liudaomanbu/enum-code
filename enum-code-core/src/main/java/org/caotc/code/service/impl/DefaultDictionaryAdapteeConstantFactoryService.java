package org.caotc.code.service.impl;

import com.google.common.collect.ImmutableSet;
import lombok.NonNull;
import lombok.Value;
import org.caotc.code.factory.DictionaryAdapteeConstantFactory;
import org.caotc.code.service.DictionaryAdapteeConstantFactoryService;

import java.util.Collection;

/**
 * @author caotc
 * @date 2021-10-08
 */
@Value
public class DefaultDictionaryAdapteeConstantFactoryService implements DictionaryAdapteeConstantFactoryService {
    @NonNull
    Collection<DictionaryAdapteeConstantFactory<?>> factories;

    public boolean support(@NonNull Class<?> type) {
        return factories.stream()
                .anyMatch(enumerableAdapteeConstantFactory -> enumerableAdapteeConstantFactory.support(type));
    }

    @SuppressWarnings({"unchecked"})
    @NonNull
    public <E> ImmutableSet<E> create(@NonNull Class<E> type) {
        DictionaryAdapteeConstantFactory<E> factory = (DictionaryAdapteeConstantFactory<E>) factories.stream()
                .filter(enumerableAdapteeConstantFactory -> enumerableAdapteeConstantFactory.support(type))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(type + "not support create constant"));//todo
        return factory.create(type);
    }

    public void addFactory(@NonNull DictionaryAdapteeConstantFactory<?> factory) {
        factories.add(factory);
    }

    @Override
    public void removeFactory(@NonNull DictionaryAdapteeConstantFactory<?> factory) {
        factories.remove(factory);
    }
}
