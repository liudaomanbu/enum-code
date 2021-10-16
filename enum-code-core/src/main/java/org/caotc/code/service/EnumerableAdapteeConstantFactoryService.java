package org.caotc.code.service;

import com.google.common.collect.ImmutableSet;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.caotc.code.factory.EnumerableAdapteeConstantFactory;

import java.util.Collection;

/**
 * @author caotc
 * @date 2021-10-08
 */
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class EnumerableAdapteeConstantFactoryService {
    @NonNull
    Collection<EnumerableAdapteeConstantFactory<?>> factories;

    public boolean support(@NonNull Class<?> type) {
        return support(type, null);
    }

    public boolean support(@NonNull Class<?> type, String group) {
        return factories.stream()
                .anyMatch(enumerableAdapteeConstantFactory -> enumerableAdapteeConstantFactory.support(type, group));
    }

    @NonNull
    public <E> ImmutableSet<E> create(@NonNull Class<E> type) {
        return create(type, null);
    }

    @SuppressWarnings({"unchecked"})
    @NonNull
    public <E> ImmutableSet<E> create(@NonNull Class<E> type, String group) {
        EnumerableAdapteeConstantFactory<E> factory = (EnumerableAdapteeConstantFactory<E>) factories.stream()
                .filter(enumerableAdapteeConstantFactory -> enumerableAdapteeConstantFactory.support(type, group))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(group + "not support create constant"));//todo
        return factory.create(type, group);
    }

    public void addFactory(@NonNull EnumerableAdapteeConstantFactory<?> factory) {
        factories.add(factory);
    }
}
