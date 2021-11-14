package org.caotc.code.factory;

import lombok.NonNull;
import lombok.Value;
import org.caotc.code.EnumerableGroup;

/**
 * @author caotc
 * @date 2021-11-08
 */
@Value
public class EnumEnumerableGroupFactory<E extends Enum<?>> implements EnumerableGroupFactory<E> {

    @Override
    public boolean support(@NonNull String group) {
//        Class.forName()
//        ClassLoader.getSystemClassLoader().loadClass()
        return false;
    }

    @Override
    public @NonNull <T extends E> EnumerableGroup<T> create(@NonNull String group) {
        return EnumerableGroup.<T>builder().name(type.getName()).type(type).build();
    }
}
