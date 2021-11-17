package org.caotc.code.model;

import lombok.Value;
import org.caotc.code.annotation.Enumerable;

/**
 * @author caotc
 * @date 2021-08-31
 */
@Value
public class CodeFieldAndCodeMethodAndCodeAnnotatedMethodObject {
    Integer code;

    public Integer code() {
        return code;
    }

    @Enumerable.Code
    public Integer abc() {
        return code;
    }
}
