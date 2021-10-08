package org.caotc.code.service;

import com.google.common.collect.ImmutableSet;
import lombok.*;
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
    Collection<EnumerableAdapteeConstantFactory<?>> enumerableAdapteeConstantsFactories;

    public boolean support(@NonNull Class<?> type){
        return enumerableAdapteeConstantsFactories.stream()
                .anyMatch(enumerableAdapteeConstantFactory -> enumerableAdapteeConstantFactory.support(type));
    }

    @SuppressWarnings({"unchecked"})
    @NonNull
    public <E> ImmutableSet<E> create(@NonNull Class<E> type) {
        EnumerableAdapteeConstantFactory<E> factory = (EnumerableAdapteeConstantFactory<E>) enumerableAdapteeConstantsFactories.stream()
                .filter(enumerableAdapteeConstantFactory -> enumerableAdapteeConstantFactory.support(type))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(type + "not support create constant"));//todo
        return factory.create(type);
    }

}
