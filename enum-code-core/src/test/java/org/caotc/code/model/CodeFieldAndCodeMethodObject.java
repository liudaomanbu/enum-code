package org.caotc.code.model;

import lombok.Value;

/**
 * @author caotc
 * @date 2021-08-31
 */
@Value
public class CodeFieldAndCodeMethodObject {
    Integer code;

    public Integer code() {
        return code;
    }
}
