package org.caotc.code.model;

import lombok.AllArgsConstructor;
import lombok.Value;
import org.caotc.code.annotation.Code;
import org.caotc.code.annotation.Enumerable;

/**
 * @author caotc
 * @date 2021-08-31
 */
@Value
@Enumerable
public class CodeFieldAndCodeMethodAndCodeAnnotatedMethodEnumerableAnnotatedObject {
    Integer code;

    public Integer code() {
        return code;
    }

    @Code
    public Integer abc() {
        return code;
    }
}
