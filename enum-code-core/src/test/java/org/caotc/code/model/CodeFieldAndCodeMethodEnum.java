package org.caotc.code.model;

import lombok.AllArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

/**
 * @author caotc
 * @date 2021-08-31
 */
@AllArgsConstructor
public enum CodeFieldAndCodeMethodEnum {
    A(0);
    int code;

    public int code() {
        return code;
    }
}
