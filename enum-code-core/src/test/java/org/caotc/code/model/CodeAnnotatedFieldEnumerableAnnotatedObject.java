package org.caotc.code.model;

import lombok.AllArgsConstructor;
import org.caotc.code.annotation.Code;
import org.caotc.code.annotation.Enumerable;

/**
 * @author caotc
 * @date 2021-08-31
 */
@AllArgsConstructor
@Enumerable
public class CodeAnnotatedFieldEnumerableAnnotatedObject {
    @Code
    public Integer value;
}
