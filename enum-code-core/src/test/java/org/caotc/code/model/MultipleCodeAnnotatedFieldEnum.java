package org.caotc.code.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.caotc.code.annotation.Dictionary;

/**
 * @author caotc
 * @date 2021-08-31
 */
@AllArgsConstructor
@Getter
public enum MultipleCodeAnnotatedFieldEnum {
    A(0,1);
    @Dictionary.Code
    int value1;
    @Dictionary.Code
    int value2;
}
