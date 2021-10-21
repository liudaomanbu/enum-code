package org.caotc.code.service;

import lombok.NonNull;
import org.caotc.code.EnumerableConstant;
import org.caotc.code.factory.EnumerableConstantFactory;

/**
 * @author caotc
 * @date 2021-09-03
 */

public interface EnumerableConstantFactoryService {

    @NonNull
    default <E, C> EnumerableConstant<C> create(@NonNull Class<E> enumerableClass) {
        return create(enumerableClass, null);
    }

    @NonNull <E, C> EnumerableConstant<C> create(@NonNull Class<E> enumerableClass, String group);

    void addFactory(@NonNull EnumerableConstantFactory<?> factory);

    void removeFactory(@NonNull EnumerableConstantFactory<?> factory);
}
