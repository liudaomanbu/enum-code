package org.caotc.code.factory;

import com.google.common.collect.ImmutableSet;
import lombok.NonNull;
import lombok.Value;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author caotc
 * @date 2021-08-20
 */
@Value
public class EnumConstantsFactory<E extends Enum<E>> implements EnumerableAdapteeConstantsFactory<E>{
    @NonNull
    Class<E> enumType;

    @Override
    public Collection<E> constants() {
        return Arrays.stream(enumType.getEnumConstants())
                .collect(ImmutableSet.toImmutableSet());
    }
}
