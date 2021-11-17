package org.caotc.code.service.impl;

import lombok.NonNull;
import lombok.Value;
import org.caotc.code.Dictionary;
import org.caotc.code.common.ReaderConstant;
import org.caotc.code.factory.EnumerableAdapterFactory;
import org.caotc.code.service.EnumerableAdapterFactoryService;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Function;

/**
 * @author caotc
 * @date 2021-10-21
 */
@Value
public class DefaultEnumerableAdapterFactoryService implements EnumerableAdapterFactoryService {
    @NonNull
    Collection<EnumerableAdapterFactory<?>> factories;

    public boolean canAdapt(@NonNull Class<?> type) {
        return factories.stream()
                .anyMatch(enumerableAdapterFactory -> enumerableAdapterFactory.canAdapt(type));
    }

    @NonNull
    public <C, E> Dictionary<C, E> adapt(@NonNull E adaptee) {
        return adapt(adaptee, null);
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public <C, E> Dictionary<C, E> adapt(@NonNull E adaptee, Function<? super E, String> groupReader) {
        EnumerableAdapterFactory<E> factory = (EnumerableAdapterFactory<E>) factories.stream()
                .filter(enumerableAdapteeConstantFactory -> enumerableAdapteeConstantFactory.canAdapt(adaptee.getClass()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(adaptee + "not support adapt to Dictionary"));//todo
        return factory.adapt(adaptee, Optional.ofNullable(groupReader).orElse(ReaderConstant.defaultGroupReader()));
    }

    @Override
    public void addFactory(@NonNull EnumerableAdapterFactory<?> factory) {
        factories.add(factory);
    }

    @Override
    public void removeFactory(@NonNull EnumerableAdapterFactory<?> factory) {
        factories.remove(factory);
    }
}
