package org.caotc.code.service.impl;

import com.google.common.collect.ImmutableSet;
import lombok.NonNull;
import lombok.Value;
import org.caotc.code.EnumerableConstant;
import org.caotc.code.common.ReaderConstant;
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
    public <E, C> EnumerableConstant<C, E> create(@NonNull Class<E> enumerableClass, String group) {
        String $group = Optional.ofNullable(group).orElse(ReaderConstant.DEFAULT_GROUP);
        EnumerableConstantFactory<E> factory = (EnumerableConstantFactory<E>) factories.stream()
                .filter(enumerableConstantFactory -> enumerableConstantFactory.support(enumerableClass, $group))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(enumerableClass + ":" + $group + " not support create EnumerableConstant"));//todo
        return factory.create(enumerableClass, $group);
    }

    @Override
    public @NonNull ImmutableSet<String> groups(@NonNull Class<?> type) {
        return factories.stream()
                .map(factory -> factory.groups(type))
                .filter(groups -> !groups.isEmpty())
                .findFirst()
                .orElseGet(ImmutableSet::of);
    }

    @Override
    public boolean support(@NonNull Class<?> type) {
        return factories.stream()
                .anyMatch(factory -> factory.support(type));
    }

    @Override
    public boolean support(@NonNull Class<?> type, String group) {
        String $group = Optional.ofNullable(group).orElse(ReaderConstant.DEFAULT_GROUP);
        return factories.stream()
                .anyMatch(factory -> factory.support(type, $group));
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
