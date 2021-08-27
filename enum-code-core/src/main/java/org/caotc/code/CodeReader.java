package org.caotc.code;

import lombok.NonNull;

/**
 * @author caotc
 * @date 2021-08-24
 */
public abstract class CodeReader<E,C> {
    @NonNull
    public abstract C read(@NonNull E enumerableAdaptee);
}
