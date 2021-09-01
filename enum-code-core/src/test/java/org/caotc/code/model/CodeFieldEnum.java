package org.caotc.code.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

/**
 * @author caotc
 * @date 2021-08-31
 */
@AllArgsConstructor
@FieldDefaults(makeFinal = true)
public enum CodeFieldEnum {
    A(1);
    public int code;
}
