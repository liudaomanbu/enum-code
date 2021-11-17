package org.caotc.code.model;

import lombok.Value;

/**
 * @author caotc
 * @date 2021-08-31
 */
@Value
public class CodeMethodObject {
    Integer value;

    public Integer code(){
        return value;
    }
}
