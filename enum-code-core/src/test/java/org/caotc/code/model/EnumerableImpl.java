package org.caotc.code.model;

import lombok.NonNull;
import lombok.Value;
import org.caotc.code.Enumerable;
import org.caotc.code.common.GroupConstant;

/**
 * @author caotc
 * @date 2021-09-02
 */
@Value
public class EnumerableImpl implements Enumerable<Integer> {
    public static final EnumerableImpl INSTANCE = new EnumerableImpl(0);
    @NonNull
    Integer value;

    public @NonNull Integer code() {
        return value;
    }

    @Override
    public @NonNull String group() {
        return GroupConstant.DEFAULT;
    }
}
