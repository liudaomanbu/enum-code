package org.caotc.code.model;

import lombok.AllArgsConstructor;
import org.caotc.code.annotation.Enumerable;

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

    @Enumerable.Code
    public int abc() {
        return code;
    }
}
