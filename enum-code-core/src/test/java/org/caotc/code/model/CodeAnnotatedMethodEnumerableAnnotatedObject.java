package org.caotc.code.model;

import lombok.Getter;
import lombok.Value;
import org.caotc.code.annotation.Dictionary;

/**
 * @author caotc
 * @date 2021-08-31
 */
@Value
@Dictionary
public class CodeAnnotatedMethodEnumerableAnnotatedObject {
    @Getter(onMethod_ = {@Dictionary.Code})
    int value;
}
