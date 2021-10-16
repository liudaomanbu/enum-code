package org.caotc.code.service;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.caotc.code.EnumerableConstant;
import org.caotc.code.factory.EnumerableConstantFactory;

import java.util.Collection;

/**
 * @author caotc
 * @date 2021-09-03
 */
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class EnumerableConstantFactoryService {
    @NonNull
    Collection<EnumerableConstantFactory<?>> factories;

    @NonNull
    public <E, C> EnumerableConstant<C> create(@NonNull Class<E> enumerableClass) {
        return create(enumerableClass, null);
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public <E, C> EnumerableConstant<C> create(@NonNull Class<E> enumerableClass,String group) {
        EnumerableConstantFactory<E> factory = (EnumerableConstantFactory<E>) factories.stream()
                .filter(enumerableConstantFactory -> enumerableConstantFactory.support(enumerableClass, group))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(enumerableClass + ":" + group + " not support create EnumerableConstant"));//todo
        return factory.create(enumerableClass, group);
    }
}
