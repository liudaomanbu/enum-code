package org.caotc.code.service;

import lombok.*;
import org.caotc.code.Enumerable;
import org.caotc.code.common.GroupConstant;
import org.caotc.code.factory.EnumerableAdapterFactory;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Function;

/**
 * @author caotc
 * @date 2021-10-08
 */
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class EnumerableAdapterFactoryService {
    @NonNull
    Collection<EnumerableAdapterFactory<?>> factories;

    public boolean canAdapt(@NonNull Class<?> type){
        return factories.stream()
                .anyMatch(enumerableAdapterFactory -> enumerableAdapterFactory.canAdapt(type));
    }

    @NonNull
    public <C> Enumerable<C> adapt(@NonNull Object adaptee){
        return adapt(adaptee,null);
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public <C> Enumerable<C> adapt(@NonNull Object adaptee, Function<Object, String> groupReader){
        EnumerableAdapterFactory<Object> factory = (EnumerableAdapterFactory<Object>) factories.stream()
                .filter(enumerableAdapteeConstantFactory -> enumerableAdapteeConstantFactory.canAdapt(adaptee.getClass()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(adaptee + "not support adapt to Enumerable"));//todo
        return factory.adapt(adaptee, Optional.ofNullable(groupReader).orElse(GroupConstant.DEFAULT_READER));
    }
}
