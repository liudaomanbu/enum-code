package org.caotc.code.model;

import lombok.Value;
import org.caotc.code.annotation.Enumerable;

/**
 * @author caotc
 * @date 2021-08-31
 */
@Value
@Enumerable
public class CodeAnnotatedFieldEnumerableAnnotatedObject {
    @Enumerable.Code
    public Integer value;
}
