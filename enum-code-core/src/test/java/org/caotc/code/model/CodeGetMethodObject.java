package org.caotc.code.model;

import lombok.AllArgsConstructor;
import org.caotc.code.annotation.Enumerable;

/**
 * @author caotc
 * @date 2021-08-31
 */
@AllArgsConstructor
public class CodeGetMethodObject {
    Integer value;

    public Integer getCode(){
        return value;
    }
}
