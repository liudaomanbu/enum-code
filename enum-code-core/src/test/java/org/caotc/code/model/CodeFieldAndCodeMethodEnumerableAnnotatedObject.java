package org.caotc.code.model;

import lombok.Value;
import org.caotc.code.annotation.Dictionary;

/**
 * @author caotc
 * @date 2021-08-31
 */
@Value
@Dictionary
public class CodeFieldAndCodeMethodEnumerableAnnotatedObject {
    Integer code;

    public Integer code() {
        return code;
    }
}
