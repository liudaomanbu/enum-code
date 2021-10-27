package org.caotc.code.factory;

import com.google.common.collect.ImmutableSet;
import lombok.NonNull;
import lombok.Value;
import org.caotc.code.EnumerableConstant;
import org.caotc.code.common.GroupConstant;
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
    public boolean support(@NonNull Class<?> type) {
        return enumerableAdapteeConstantFactoryService.support(type)
                && enumerableAdapterFactoryService.canAdapt(type);
    }

    @Override
    public boolean support(@NonNull Class<?> type, @NonNull String group) {
        return enumerableAdapteeConstantFactoryService.support(type, group)
                && enumerableAdapterFactoryService.canAdapt(type);
    }

    @Override
    public @NonNull ImmutableSet<String> groups(@NonNull Class<?> type) {
        return enumerableAdapteeConstantFactoryService.groups(type);
    }

    @Override
    public @NonNull <C> EnumerableConstant<C> create(@NonNull Class<?> type, @NonNull String group) {
        return EnumerableConstant.<C>builder()
                .enumerables(enumerableAdapteeConstantFactoryService.create(type, group)
                        .stream()
                        .map(adaptee -> enumerableAdapterFactoryService.<C, Object>adapt(adaptee, GroupConstant.defaultReader()))
                        .collect(ImmutableSet.toImmutableSet()))
                .build();
    }

}
