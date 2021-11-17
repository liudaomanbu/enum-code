package org.caotc.code.model;

import lombok.AllArgsConstructor;
import org.caotc.code.annotation.Dictionary;

/**
 * @author caotc
 * @date 2021-08-31
 */
@AllArgsConstructor
public enum CodeFieldAndCodeMethodAndCodeAnnotatedFieldEnum {
    A(1,0);
    int code;
    @Dictionary.Code
    public int value;

    public int code() {
        return code;
    }
}
