package org.caotc.code.model;

import lombok.Value;
import org.caotc.code.annotation.Enumerable;

/**
 * @author caotc
 * @date 2021-08-31
 */
@Value
@Enumerable
public class CodeFieldAndCodeMethodAndCodeAnnotatedFieldEnumerableAnnotatedObject {
    Integer code;
    @Enumerable.Code
    public Integer value;

    public Integer code() {
        return code;
    }
}
