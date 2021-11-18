package org.caotc.code.factory;

import lombok.NonNull;
import lombok.Value;
import org.caotc.code.DictionaryConverter;

/**
 * @author caotc
 * @date 2021-11-12
 */
@Value
public class EnumDictionaryConverterFactory<E extends Enum<?>> implements DictionaryConverterFactory<E> {
    @Override
    public @NonNull <T extends E> DictionaryConverter<?, T> create(@NonNull Class<T> type) {
//        return DictionaryConstant.<Object,T>builder()
//                .dictionaries(Arrays.stream(type.getEnumConstants()).collect(ImmutableSet.toImmutableSet()))
//                .build();
        return null;
    }

    @Override
    public @NonNull <T extends E> DictionaryConverter<?, T> create(@NonNull String group) {
        return null;
    }
}
