package org.caotc.code.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.caotc.code.annotation.Enumerable;

/**
 * @author caotc
 * @date 2021-08-31
 */
@AllArgsConstructor
@Getter
public enum MultipleCodeAnnotatedFieldEnum {
    A(0,1);
    @Enumerable.Code
    int value1;
    @Enumerable.Code
    int value2;
}
