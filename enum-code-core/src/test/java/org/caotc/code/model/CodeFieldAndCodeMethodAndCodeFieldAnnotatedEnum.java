package org.caotc.code.model;

import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.caotc.code.annotation.Code;

/**
 * @author caotc
 * @date 2021-08-31
 */
@AllArgsConstructor
@FieldDefaults(makeFinal = true)
public enum CodeFieldAndCodeMethodAndCodeFieldAnnotatedEnum {
    A(1,2);
    int code;
    @Code
    public int value;

    public int code() {
        return code;
    }
}
