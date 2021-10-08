package org.caotc.code.factory;

import lombok.NonNull;
import org.caotc.code.Enumerable;
import org.caotc.code.EnumerableConstant;

/**
 * @author caotc
 * @date 2021-08-17
 */
public interface EnumerableConstantsFactory<E> {
    @NonNull
    <C> EnumerableConstant<C> create(@NonNull Class<? extends E> type);

    //    boolean support(@NonNull EnumerableType type);
    boolean support(@NonNull Class<?> type);
}
