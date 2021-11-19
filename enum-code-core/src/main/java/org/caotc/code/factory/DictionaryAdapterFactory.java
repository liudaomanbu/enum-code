package org.caotc.code.factory;

import lombok.NonNull;
import org.caotc.code.Dictionary;

/**
 * @author caotc
 * @date 2021-08-25
 */
public interface DictionaryAdapterFactory<T> {
    boolean canAdapt(@NonNull Class<?> type);

    @NonNull <C, F extends T> Dictionary<C, F> adapt(@NonNull F adaptee);

}
