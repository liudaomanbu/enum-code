package org.caotc.code.service.impl;

import lombok.NonNull;
import lombok.Value;
import org.caotc.code.EnumerableConstant;
import org.caotc.code.common.GroupConstant;
import org.caotc.code.factory.EnumerableConstantFactory;
import org.caotc.code.service.EnumerableConstantFactoryService;

import java.util.Collection;
import java.util.Optional;

/**
 * @author caotc
 * @date 2021-09-03
 */
@Value
public class DefaultEnumerableConstantFactoryService implements EnumerableConstantFactoryService {
    @NonNull
    Collection<EnumerableConstantFactory<?>> factories;

    @SuppressWarnings("unchecked")
    @NonNull
    public <E, C> EnumerableConstant<C> create(@NonNull Class<E> enumerableClass) {
        String $group = Optional.ofNullable(group).orElse(GroupConstant.DEFAULT);
        EnumerableConstantFactory<E> factory = (EnumerableConstantFactory<E>) factories.stream()
                .filter(enumerableConstantFactory -> enumerableConstantFactory.support(enumerableClass, $group))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(enumerableClass + ":" + $group + " not support create EnumerableConstant"));//todo
        return factory.create(enumerableClass, $group);
    }

    @Override
    public void addFactory(@NonNull EnumerableConstantFactory<?> factory) {
        factories.add(factory);
    }

    @Override
    public void removeFactory(@NonNull EnumerableConstantFactory<?> factory) {
        factories.remove(factory);
    }
}
