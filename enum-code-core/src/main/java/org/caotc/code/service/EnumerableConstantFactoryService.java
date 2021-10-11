package org.caotc.code.service;

import lombok.*;
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

    @SuppressWarnings("unchecked")
    @NonNull
    public <E, C> EnumerableConstant<C> create(@NonNull Class<E> enumerableClass) {
        EnumerableConstantFactory<E> factory = (EnumerableConstantFactory<E>) factories.stream()
                .filter(enumerableConstantFactory -> enumerableConstantFactory.support(enumerableClass))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(enumerableClass + " not support create EnumerableConstant"));//todo
        return factory.create(enumerableClass);
    }
}
