package org.caotc.code.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.caotc.code.annotation.Code;

/**
 * @author caotc
 * @date 2021-08-31
 */
@AllArgsConstructor
public enum CodeAnnotatedMethodEnum {
    A(0);
    @Getter(onMethod_={@Code})
    int value;
}
