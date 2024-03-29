package org.caotc.code.service.impl;

import com.google.common.collect.ImmutableSet;
import lombok.NonNull;
import lombok.Value;
import org.caotc.code.common.GroupConstant;
import org.caotc.code.factory.EnumerableAdapteeConstantFactory;
import org.caotc.code.service.EnumerableAdapteeConstantFactoryService;

import java.util.Collection;
import java.util.Optional;

/**
 * @author caotc
 * @date 2021-10-08
 */
@Value
public class DefaultEnumerableAdapteeConstantFactoryService implements EnumerableAdapteeConstantFactoryService {
    @NonNull
    Collection<EnumerableAdapteeConstantFactory<?>> factories;

    public boolean support(@NonNull Class<?> type) {
        return support(type, null);
    }

    public boolean support(@NonNull Class<?> type, String group) {
        return factories.stream()
                .anyMatch(enumerableAdapteeConstantFactory -> enumerableAdapteeConstantFactory.support(type, Optional.ofNullable(group).orElse(GroupConstant.DEFAULT)));
    }

    @NonNull
    public <E> ImmutableSet<E> create(@NonNull Class<E> type) {
        return create(type, null);
    }

    @SuppressWarnings({"unchecked"})
    @NonNull
    public <E> ImmutableSet<E> create(@NonNull Class<E> type, String group) {
        String $group = Optional.ofNullable(group).orElse(GroupConstant.DEFAULT);
        EnumerableAdapteeConstantFactory<E> factory = (EnumerableAdapteeConstantFactory<E>) factories.stream()
                .filter(enumerableAdapteeConstantFactory -> enumerableAdapteeConstantFactory.support(type, $group))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException($group + "not support create constant"));//todo
        return factory.create(type, $group);
    }

    public void addFactory(@NonNull EnumerableAdapteeConstantFactory<?> factory) {
        factories.add(factory);
    }

    @Override
    public void removeFactory(@NonNull EnumerableAdapteeConstantFactory<?> factory) {
        factories.remove(factory);
    }
}
