package org.caotc.code.factory;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSetMultimap;
import lombok.NonNull;
import lombok.Value;
import org.caotc.code.Dictionary;
import org.caotc.code.DictionaryConstant;
import org.caotc.code.DictionaryConverter;
import org.caotc.code.service.DictionaryAdapteeConstantFactoryService;
import org.caotc.code.service.DictionaryAdapterFactoryService;

import java.util.function.Function;

/**
 * @author caotc
 * @date 2021-08-17
 */
@Value
public class DictionaryAdapteeConstantsFactoryToDictionaryConverterClassFactoryAdapter implements DictionaryConverterClassFactory<Object> {
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
    public @NonNull <C, F> ImmutableSet<DictionaryConverter<C, F>> create(@NonNull Class<F> type) {
        return dictionaryAdapteeConstantFactoryService.create(type)
                .stream()
                .map(dictionaryAdapterFactoryService::<C, F>adapt)
                .collect(ImmutableSetMultimap.toImmutableSetMultimap(Dictionary::group, Function.identity()))
                .asMap()
                .values()
                .stream()
                .map(dictionaries -> DictionaryConstant.<C, F>builder()
                        .dictionaries(dictionaries)
                        .originalType(type).build())
                .collect(ImmutableSet.toImmutableSet());
    }

}
