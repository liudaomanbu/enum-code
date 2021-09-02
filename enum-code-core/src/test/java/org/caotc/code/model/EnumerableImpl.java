package org.caotc.code.model;

import lombok.AllArgsConstructor;
import org.caotc.code.Enumerable;

/**
 * @author caotc
 * @date 2021-09-02
 */
@AllArgsConstructor
public class EnumerableImpl implements Enumerable<Integer> {
    Integer value;
    public Integer code() {
        return value;
    }
}
