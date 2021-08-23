package org.caotc.code.factory;

import org.caotc.code.Enumerable;
import org.caotc.code.EnumerableConstants;

import java.util.Collection;

/**
 * @author caotc
 * @date 2021-08-17
 */
public interface EnumerableAdapteeConstantsFactory<E> {
    Collection<E> constants();
}
