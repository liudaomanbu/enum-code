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
    default String name() {
        return "";
    }//todo

    @NonNull
    default String description() {
        return "";
    }//todo

    //todo object?
    @NonNull
    String group();

    @NonNull
    S unwrap();
}
