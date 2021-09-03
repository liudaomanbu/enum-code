package org.caotc.code;

import lombok.NonNull;

/**
 * @author caotc
 * @date 2021-08-17
 */
public interface Enumerable<C> {
    @NonNull
    C code();
}
