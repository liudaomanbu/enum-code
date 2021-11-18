package org.caotc.code;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

/**
 * @author caotc
 * @date 2021-11-08
 */
@Value
@Builder
public class DictionaryGroup<T> {
    @NonNull
    String name;
    @NonNull
    Class<T> type;
}
