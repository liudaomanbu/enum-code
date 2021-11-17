package org.caotc.code.model;

import lombok.Value;
import org.caotc.code.annotation.Dictionary;

/**
 * @author caotc
 * @date 2021-08-31
 */
@Value
@Dictionary
public class CodeGetMethodEnumerableAnnotatedObject {
    Integer value;

    public Integer getCode(){
        return value;
    }
}
