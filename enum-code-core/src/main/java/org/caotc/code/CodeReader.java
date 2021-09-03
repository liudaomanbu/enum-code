package org.caotc.code;

import lombok.EqualsAndHashCode;
import lombok.NonNull;

/**
 * @author caotc
 * @date 2021-08-24
 */
@EqualsAndHashCode
public abstract class CodeReader<E,C> {
    @NonNull
    public abstract C read(@NonNull E enumerableAdaptee);
}
