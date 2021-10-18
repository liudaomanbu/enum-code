package org.caotc.code.factory;

import lombok.NonNull;
import org.caotc.code.Enumerable;

import java.util.function.Function;

/**
 * @author caotc
 * @date 2021-08-25
 */
public interface EnumerableAdapterFactory<T> {
    boolean canAdapt(@NonNull Class<?> type);

    @NonNull <C> Enumerable<C> adapt(@NonNull T adaptee, @NonNull Function<? super T, String> groupReader);
}
