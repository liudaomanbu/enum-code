package org.caotc.code.factory;

import lombok.NonNull;
import org.caotc.code.Enumerable;

/**
 * @author caotc
 * @date 2021-08-25
 */
public interface EnumerableAdapterFactory<T> {
    boolean canAdapt(@NonNull Class<?> type);
    @NonNull
    <C> Enumerable<C> adapt(@NonNull T adaptee);
}
