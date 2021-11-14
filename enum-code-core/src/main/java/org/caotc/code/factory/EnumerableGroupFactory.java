package org.caotc.code.factory;

import lombok.NonNull;
import org.caotc.code.EnumerableGroup;

/**
 * @author caotc
 * @date 2021-11-08
 */
public interface EnumerableGroupFactory<E> {
    boolean support(@NonNull String group);

    @NonNull <T extends E> EnumerableGroup<T> create(@NonNull String group);
}
