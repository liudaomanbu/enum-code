package org.caotc.code.model;

import lombok.AllArgsConstructor;
import lombok.Value;
import org.caotc.code.annotation.Enumerable;

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
