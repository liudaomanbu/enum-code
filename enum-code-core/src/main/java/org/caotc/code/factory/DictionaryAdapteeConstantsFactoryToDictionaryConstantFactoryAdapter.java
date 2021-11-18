package org.caotc.code.factory;

import com.google.common.collect.ImmutableSet;
import lombok.NonNull;
import lombok.Value;
import org.caotc.code.DictionaryConstant;
import org.caotc.code.service.DictionaryAdapteeConstantFactoryService;
import org.caotc.code.service.DictionaryAdapterFactoryService;

/**
 * @author caotc
 * @date 2021-08-17
 */
@Value
public class DictionaryAdapteeConstantsFactoryToDictionaryConstantFactoryAdapter implements DictionaryConstantFactory<Object> {
    @NonNull
    DictionaryAdapteeConstantFactoryService dictionaryAdapteeConstantFactoryService;
    @NonNull
    DictionaryAdapterFactoryService dictionaryAdapterFactoryService;

    @Override
    public boolean support(@NonNull Class<?> type) {
        return dictionaryAdapteeConstantFactoryService.support(type)
                && dictionaryAdapterFactoryService.canAdapt(type);
    }

    @Override
    public boolean support(@NonNull Class<?> type, @NonNull String group) {
        return dictionaryAdapteeConstantFactoryService.support(type, group)
                && dictionaryAdapterFactoryService.canAdapt(type);
    }

    @Override
    public @NonNull ImmutableSet<String> groups(@NonNull Class<?> type) {
        return dictionaryAdapteeConstantFactoryService.groups(type);
    }

    @Override
    public @NonNull <C, F> DictionaryConstant<C, F> create(@NonNull Class<F> type, @NonNull String group) {
        return DictionaryConstant.<C, F>builder()
                .dictionaries(dictionaryAdapteeConstantFactoryService.create(type, group)
                        .stream()
                        .map(adaptee -> dictionaryAdapterFactoryService.<C, F>adapt(adaptee, t -> group))
                        .collect(ImmutableSet.toImmutableSet()))
                .originalType(type)
                .group(group)
                .build();
    }

}
