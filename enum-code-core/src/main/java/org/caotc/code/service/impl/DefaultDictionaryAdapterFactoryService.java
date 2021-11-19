package org.caotc.code.service.impl;

import lombok.NonNull;
import lombok.Value;
import org.caotc.code.Dictionary;
import org.caotc.code.factory.DictionaryAdapterFactory;
import org.caotc.code.service.DictionaryAdapterFactoryService;

import java.util.Collection;

/**
 * @author caotc
 * @date 2021-10-21
 */
@Value
public class DefaultDictionaryAdapterFactoryService implements DictionaryAdapterFactoryService {
    @NonNull
    Collection<DictionaryAdapterFactory<?>> factories;

    public boolean canAdapt(@NonNull Class<?> type) {
        return factories.stream()
                .anyMatch(dictionaryAdapterFactory -> dictionaryAdapterFactory.canAdapt(type));
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public <C, E> Dictionary<C, E> adapt(@NonNull E adaptee) {
        DictionaryAdapterFactory<E> factory = (DictionaryAdapterFactory<E>) factories.stream()
                .filter(enumerableAdapteeConstantFactory -> enumerableAdapteeConstantFactory.canAdapt(adaptee.getClass()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(adaptee + "not support adapt to Dictionary"));//todo
        return factory.adapt(adaptee);
    }

    @Override
    public void addFactory(@NonNull DictionaryAdapterFactory<?> factory) {
        factories.add(factory);
    }

    @Override
    public void removeFactory(@NonNull DictionaryAdapterFactory<?> factory) {
        factories.remove(factory);
    }
}
