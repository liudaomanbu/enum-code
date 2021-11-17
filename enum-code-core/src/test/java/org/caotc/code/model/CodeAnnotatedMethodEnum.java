package org.caotc.code.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.caotc.code.annotation.Dictionary;

/**
 * @author caotc
 * @date 2021-08-31
 */
@AllArgsConstructor
public enum CodeAnnotatedMethodEnum {
    A(0);
    @Getter(onMethod_ = {@Dictionary.Code})
    int value;
}
