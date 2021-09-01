package org.caotc.code.model;

import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

/**
 * @author caotc
 * @date 2021-08-31
 */
@AllArgsConstructor
@FieldDefaults(makeFinal = true)
public enum CodeFieldAndCodeMethodEnum {
    A(1);
    int code;

    public int code() {
        return code;
    }
}
