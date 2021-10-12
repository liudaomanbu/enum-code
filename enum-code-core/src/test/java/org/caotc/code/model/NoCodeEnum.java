package org.caotc.code.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

/**
 * @author caotc
 * @date 2021-08-31
 */
@AllArgsConstructor
@Getter
public enum NoCodeEnum {
    A(0);
    int value;
}
