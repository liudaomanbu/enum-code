package org.caotc.code.model;

import lombok.AllArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.caotc.code.annotation.Code;

/**
 * @author caotc
 * @date 2021-08-31
 */
@AllArgsConstructor
public enum CodeFieldAndCodeMethodAndCodeAnnotatedMethodEnum {
    A(0);
    int code;

    public int code() {
        return code;
    }

    @Code
    public int abc() {
        return code;
    }
}
