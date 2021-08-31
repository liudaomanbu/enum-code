package org.caotc.code;

import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

/**
 * @author caotc
 * @date 2021-08-31
 */
@AllArgsConstructor
@FieldDefaults(makeFinal = true)
public enum CodeMethodEnum {
    A(1);
    int code;

    public int code() {
        return code;
    }
}
