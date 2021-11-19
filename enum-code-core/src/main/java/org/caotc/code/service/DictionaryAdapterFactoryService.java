package org.caotc.code.service;

import lombok.NonNull;
import org.caotc.code.Dictionary;
import org.caotc.code.factory.DictionaryAdapterFactory;

/**
 * @author caotc
 * @date 2021-10-08
 */

public interface DictionaryAdapterFactoryService {

    boolean canAdapt(@NonNull Class<?> type);

    @NonNull <C, E> Dictionary<C, E> adapt(@NonNull E adaptee);

    void addFactory(@NonNull DictionaryAdapterFactory<?> factory);

    void removeFactory(@NonNull DictionaryAdapterFactory<?> factory);

}
