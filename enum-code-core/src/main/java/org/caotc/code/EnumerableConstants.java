package org.caotc.code;

import java.util.Set;

/**
 * @author caotc
 * @date 2021-08-19
 */
public interface EnumerableConstants<E extends Enumerable<?>> {
    Set<E> values();
}
