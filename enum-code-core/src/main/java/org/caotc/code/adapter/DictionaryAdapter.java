package org.caotc.code.adapter;

import lombok.NonNull;
import org.caotc.code.Dictionary;

/**
 * @author caotc
 * @date 2021-10-09
 */
public interface DictionaryAdapter<E, C> extends Dictionary<C, E> {
    @NonNull
    E adaptee();
}
