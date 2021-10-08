package org.caotc.code.service;

import lombok.*;
import org.caotc.code.EnumerableConstant;
import org.caotc.code.factory.EnumerableConstantsFactory;

import java.util.Collection;
import java.util.Set;

/**
 * @author caotc
 * @date 2021-09-03
 */
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class EnumerableConstantsFactoryService {
    @NonNull
    Collection<EnumerableConstantsFactory<?>> enumerableConstantsFactories;

    @SuppressWarnings("unchecked")
    @NonNull
    public <E, C> EnumerableConstant<C> create(@NonNull Class<E> enumerableClass) {
        EnumerableConstantsFactory<E> factory = (EnumerableConstantsFactory<E>) enumerableConstantsFactories.stream()
                .filter(enumerableConstantsFactory -> enumerableConstantsFactory.support(enumerableClass))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(enumerableClass + "not support create EnumerableConstant"));//todo
        return factory.create(enumerableClass);
    }
}
