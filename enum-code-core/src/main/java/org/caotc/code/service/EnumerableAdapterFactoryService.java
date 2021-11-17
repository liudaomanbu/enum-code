package org.caotc.code.service;

import lombok.NonNull;
import org.caotc.code.Enumerable;
import org.caotc.code.factory.EnumerableAdapterFactory;

import java.util.function.Function;

/**
 * @author caotc
 * @date 2021-10-08
 */

public interface EnumerableAdapterFactoryService {

    boolean canAdapt(@NonNull Class<?> type);

    @NonNull
    default <C, E> Enumerable<C, E> adapt(@NonNull E adaptee) {
        return adapt(adaptee, null);
    }

    @NonNull <C, E> Enumerable<C, E> adapt(@NonNull E adaptee, Function<? super E, String> groupReader);

    void addFactory(@NonNull EnumerableAdapterFactory<?> factory);

    void removeFactory(@NonNull EnumerableAdapterFactory<?> factory);

}
