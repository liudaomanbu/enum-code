package org.caotc.code;

import lombok.NonNull;

/**
 * @author caotc
 * @date 2021-08-17
 */
public interface Dictionary<C, S> {
    @NonNull
    C code();

    @NonNull
    String name();

    @NonNull
    String description();

    //todo object?
    @NonNull
    String group();

    @NonNull
    S unwrap();
}
