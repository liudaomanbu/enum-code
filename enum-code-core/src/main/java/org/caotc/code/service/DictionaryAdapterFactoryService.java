package org.caotc.code.service;

import lombok.NonNull;
import org.caotc.code.Dictionary;
import org.caotc.code.factory.DictionaryAdapterFactory;

import java.util.function.Function;

/**
 * @author caotc
 * @date 2021-10-08
 */

public interface DictionaryAdapterFactoryService {

    boolean canAdapt(@NonNull Class<?> type);

    @NonNull
    default <C, E> Dictionary<C, E> adapt(@NonNull E adaptee) {
        return adapt(adaptee, null);
    }

    @NonNull <C, E> Dictionary<C, E> adapt(@NonNull E adaptee, Function<? super E, String> groupReader);

    void addFactory(@NonNull DictionaryAdapterFactory<?> factory);

    void removeFactory(@NonNull DictionaryAdapterFactory<?> factory);

}
