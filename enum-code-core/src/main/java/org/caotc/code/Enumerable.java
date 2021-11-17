package org.caotc.code;

import lombok.NonNull;

/**
 * @author caotc
 * @date 2021-08-17
 */
public interface Enumerable<C, S> {
    @NonNull
    C code();

    @NonNull
    default String name() {
        return "";
    }

    @NonNull
    default String description() {
        return "";
    }

    //todo object?
    @NonNull
    String group();

    @NonNull
    S unwrap();
}
