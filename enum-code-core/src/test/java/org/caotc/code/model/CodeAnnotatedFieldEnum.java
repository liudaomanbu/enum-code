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
public enum CodeAnnotatedFieldEnum {
    A(0);
    @Enumerable.Code
    int value;
}
