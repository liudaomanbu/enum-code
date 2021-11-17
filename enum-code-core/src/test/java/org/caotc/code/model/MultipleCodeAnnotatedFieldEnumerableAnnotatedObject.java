package org.caotc.code.model;

import lombok.Value;
import org.caotc.code.annotation.Dictionary;

/**
 * @author caotc
 * @date 2021-08-31
 */
@Value
@Dictionary
public class MultipleCodeAnnotatedFieldEnumerableAnnotatedObject {
    @Dictionary.Code
    public Integer value1;
    @Dictionary.Code
    public Integer value2;
}
