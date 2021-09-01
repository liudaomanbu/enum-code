package org.caotc.code.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.caotc.code.annotation.Code;

/**
 * @author caotc
 * @date 2021-08-31
 */
@AllArgsConstructor
@FieldDefaults(makeFinal = true)
@Getter
public enum CodeFieldAnnotatedEnum {
    A(1);
    @Code
    int value;
}