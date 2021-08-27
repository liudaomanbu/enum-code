package org.caotc.code.factory;

import com.google.common.collect.ImmutableSet;
import lombok.NonNull;
import lombok.Value;
import org.caotc.code.CodeAnnotationParser;
import org.caotc.code.CodeReader;
import org.caotc.code.Enumerable;
import org.caotc.code.EnumerableConstants;
import org.caotc.code.adapter.CodeAnnotationToEnumerableAdapter;

/**
 * @author caotc
 * @date 2021-08-17
 */
@Value
public class EnumerableAdapteeConstantsFactoryToEnumerableConstantsFactoryAdapter<E,C> implements EnumerableConstantsFactory<Enumerable<C>,C> {
    @NonNull
    EnumerableAdapteeConstantsFactory<E> enumerableAdapteeConstantsFactory;
    @NonNull
    EnumerableAdapterFactory<E> enumerableAdapterFactory;
    @Override
    public EnumerableConstants<Enumerable<C>,C> constants() {
        return EnumerableConstants.<Enumerable<C>,C>builder().values(enumerableAdapteeConstantsFactory.constants()
                .stream()
                .map(enumerableAdapterFactory::<C>adapt)
                .collect(ImmutableSet.toImmutableSet()))
                .build();
    }
}
