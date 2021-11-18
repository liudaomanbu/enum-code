package org.caotc.code.service.impl;

import com.google.common.collect.ImmutableSet;
import lombok.NonNull;
import lombok.Value;
import org.caotc.code.common.ReaderConstant;
import org.caotc.code.factory.DictionaryAdapteeConstantFactory;
import org.caotc.code.service.DictionaryAdapteeConstantFactoryService;

import java.util.Collection;
import java.util.Optional;

/**
 * @author caotc
 * @date 2021-10-08
 */
@Value
public class DefaultDictionaryAdapteeConstantFactoryService implements DictionaryAdapteeConstantFactoryService {
    @NonNull
    Collection<DictionaryAdapteeConstantFactory<?>> factories;

    @Override
    public @NonNull ImmutableSet<String> groups(@NonNull Class<?> type) {
        return factories.stream()
                .map(factory -> factory.groups(type))
                .filter(groups -> !groups.isEmpty())
                .findFirst()
                .orElseGet(ImmutableSet::of);
    }

    public boolean support(@NonNull Class<?> type) {
        return factories.stream()
                .anyMatch(enumerableAdapteeConstantFactory -> enumerableAdapteeConstantFactory.support(type));
    }

    public boolean support(@NonNull Class<?> type, String group) {
        return factories.stream()
                .anyMatch(enumerableAdapteeConstantFactory -> enumerableAdapteeConstantFactory.support(type, Optional.ofNullable(group).orElse(ReaderConstant.DEFAULT_GROUP)));
    }

    @NonNull
    public <E> ImmutableSet<E> create(@NonNull Class<E> type) {
        return create(type, null);
    }

    @SuppressWarnings({"unchecked"})
    @NonNull
    public <E> ImmutableSet<E> create(@NonNull Class<E> type, String group) {
        String $group = Optional.ofNullable(group).orElse(ReaderConstant.DEFAULT_GROUP);
        DictionaryAdapteeConstantFactory<E> factory = (DictionaryAdapteeConstantFactory<E>) factories.stream()
                .filter(enumerableAdapteeConstantFactory -> enumerableAdapteeConstantFactory.support(type, $group))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException($group + "not support create constant"));//todo
        return factory.create(type, $group);
    }

    public void addFactory(@NonNull DictionaryAdapteeConstantFactory<?> factory) {
        factories.add(factory);
    }

    @Override
    public void removeFactory(@NonNull DictionaryAdapteeConstantFactory<?> factory) {
        factories.remove(factory);
    }
}
