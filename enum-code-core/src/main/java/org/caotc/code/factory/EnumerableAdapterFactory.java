package org.caotc.code.factory;

import lombok.NonNull;
import org.caotc.code.Dictionary;

import java.util.function.Function;

/**
 * @author caotc
 * @date 2021-08-25
 */
public interface EnumerableAdapterFactory<T> {
    boolean canAdapt(@NonNull Class<?> type);

    @NonNull <C, F extends T> Dictionary<C, F> adapt(@NonNull F adaptee, @NonNull Function<? super F, String> groupReader);
}
