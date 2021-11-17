package org.caotc.code.factory;

import lombok.NonNull;
import lombok.Value;
import org.caotc.code.EnumerableConverter;

/**
 * @author caotc
 * @date 2021-11-12
 */
@Value
public class EnumEnumerableConverterFactory<E extends Enum<?>> implements EnumerableConverterFactory<E> {
    @Override
    public @NonNull <T extends E> EnumerableConverter<?, T> create(@NonNull Class<T> type) {
//        return EnumerableConstant.<Object,T>builder()
//                .dictionaries(Arrays.stream(type.getEnumConstants()).collect(ImmutableSet.toImmutableSet()))
//                .build();
        return null;
    }

    @Override
    public @NonNull <T extends E> EnumerableConverter<?, T> create(@NonNull String group) {
        return null;
    }
}
