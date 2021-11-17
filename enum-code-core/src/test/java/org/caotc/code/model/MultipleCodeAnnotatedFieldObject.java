package org.caotc.code.model;

import lombok.Value;
import org.caotc.code.annotation.Dictionary;

/**
 * @author caotc
 * @date 2021-08-31
 */
@Value
public class MultipleCodeAnnotatedFieldObject {
    @Dictionary.Code
    public Integer value1;
    @Dictionary.Code
    public Integer value2;
}
