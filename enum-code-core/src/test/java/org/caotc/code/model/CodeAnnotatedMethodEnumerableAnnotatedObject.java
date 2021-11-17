package org.caotc.code.model;

import lombok.Getter;
import lombok.Value;
import org.caotc.code.annotation.Enumerable;

/**
 * @author caotc
 * @date 2021-08-31
 */
@Value
@Enumerable
public class CodeAnnotatedMethodEnumerableAnnotatedObject {
    @Getter(onMethod_ = {@Enumerable.Code})
    int value;
}
