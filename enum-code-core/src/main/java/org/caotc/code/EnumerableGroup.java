package org.caotc.code;

import lombok.NonNull;
import lombok.Value;

/**
 * @author caotc
 * @date 2021-11-08
 */
@Value
public class EnumerableGroup<T> {
    @NonNull
    String name;
    @NonNull
    Class<T> type;
}
