package org.caotc.code.factory;

import com.google.common.collect.ImmutableSet;
import lombok.NonNull;
import lombok.Value;
import org.caotc.code.common.ReaderConstant;

import java.util.Arrays;

/**
 * @author caotc
 * @date 2021-08-20
 */
@Value
public class EnumConstantFactory implements DictionaryAdapteeConstantFactory<Enum<?>> {

    @Override
    public @NonNull <F extends Enum<?>> ImmutableSet<F> create(@NonNull Class<F> type, @NonNull String group) {
        return Arrays.stream(type.getEnumConstants())
                .collect(ImmutableSet.toImmutableSet());
    }

    @Override
    public @NonNull ImmutableSet<String> groups(@NonNull Class<?> type) {
        if (type.isEnum()) {
            return ReaderConstant.DEFAULT_GROUPS;
        }
        return ImmutableSet.of();
    }
}
