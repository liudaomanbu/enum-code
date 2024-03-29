package org.caotc.code.model;

import lombok.Value;
import org.caotc.code.annotation.Code;
import org.caotc.code.annotation.Enumerable;

/**
 * @author caotc
 * @date 2021-08-31
 */
@Value
public class MultipleCodeAnnotatedFieldObject {
    @Code
    public Integer value1;
    @Code
    public Integer value2;
}
