package org.caotc.code.model;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.Value;
import org.caotc.code.Enumerable;

/**
 * @author caotc
 * @date 2021-09-02
 */
@Value
public class EnumerableImpl implements Enumerable<Integer> {
    public static final EnumerableImpl INSTANCE=new EnumerableImpl(0);
    @NonNull
    Integer value;
    public @NonNull Integer code() {
        return value;
    }
}
