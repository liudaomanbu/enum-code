package org.caotc.code.factory;

import lombok.NonNull;
import lombok.Value;
import org.caotc.code.Enumerable;
import org.caotc.code.EnumerableConstants;

import java.util.Set;

/**
 * @author caotc
 * @date 2021-09-03
 */
@Value
public class EnumerableConstantsFactoryService {
    @NonNull
    Set<EnumerableConstantsFactory<?,?>> enumerableConstantsFactories;

    public <E extends Enumerable<C>,C> EnumerableConstants<E,C> get(@NonNull Class<E> enumerableClass){
        return null;
    }
}
