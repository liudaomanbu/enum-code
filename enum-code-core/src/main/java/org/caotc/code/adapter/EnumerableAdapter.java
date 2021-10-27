package org.caotc.code.adapter;

import lombok.NonNull;
import org.caotc.code.Enumerable;

/**
 * @author caotc
 * @date 2021-10-09
 */
public interface EnumerableAdapter<E, C> extends Enumerable<C> {
    @NonNull
    E adaptee();
}
