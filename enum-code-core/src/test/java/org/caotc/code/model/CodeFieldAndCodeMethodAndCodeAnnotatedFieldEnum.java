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
public enum CodeFieldAndCodeMethodAndCodeAnnotatedFieldEnum {
    A(1,0);
    int code;
    @Code
    public int value;

    public int code() {
        return code;
    }
}
