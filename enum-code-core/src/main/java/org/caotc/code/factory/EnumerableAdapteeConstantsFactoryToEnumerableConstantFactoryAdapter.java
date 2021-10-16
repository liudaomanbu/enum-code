package org.caotc.code.factory;

import com.google.common.collect.ImmutableSet;
import lombok.NonNull;
import lombok.Value;
import org.caotc.code.EnumerableConstant;
import org.caotc.code.service.EnumerableAdapteeConstantFactoryService;
import org.caotc.code.service.EnumerableAdapterFactoryService;

/**
 * @author caotc
 * @date 2021-08-17
 */
@Value
public class EnumerableAdapteeConstantsFactoryToEnumerableConstantFactoryAdapter implements EnumerableConstantFactory<Object> {
    @NonNull
    EnumerableAdapteeConstantFactoryService enumerableAdapteeConstantFactoryService;
    @NonNull
    EnumerableAdapterFactoryService enumerableAdapterFactoryService;

    @Override
    public @NonNull <C> EnumerableConstant<C> create(@NonNull Class<?> type, String group) {
        return EnumerableConstant.<C>builder()
                .enumerables(enumerableAdapteeConstantFactoryService.create(type, group)
                        .stream()
                        .map(enumerableAdapterFactoryService::<C>adapt)
                        .collect(ImmutableSet.toImmutableSet()))
                .build();
    }

    @Override
    public boolean support(@NonNull Class<?> type, String group) {
        return enumerableAdapteeConstantFactoryService.support(type, group)
                && enumerableAdapterFactoryService.canAdapt(type);
    }

}
