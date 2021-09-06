package org.caotc.code;

import lombok.NonNull;
import lombok.Value;

/**
 * @author caotc
 * @date 2021-08-20
 */
@Value(staticConstructor = "create")
public class EnumerableType {
    @NonNull
    String value;
}
