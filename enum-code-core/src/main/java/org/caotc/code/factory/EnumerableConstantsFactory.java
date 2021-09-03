package org.caotc.code.factory;

import lombok.NonNull;
import org.caotc.code.Enumerable;
import org.caotc.code.EnumerableConstants;

/**
 * @author caotc
 * @date 2021-08-17
 */
public interface EnumerableConstantsFactory<E extends Enumerable<C>,C> {
    @NonNull
    EnumerableConstants<E,C> constants();
}
