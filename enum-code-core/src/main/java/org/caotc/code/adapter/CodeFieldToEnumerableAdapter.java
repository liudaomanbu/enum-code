package org.caotc.code.adapter;

import lombok.*;
import org.caotc.code.Enumerable;

import java.lang.reflect.Field;

/**
 * @author caotc
 * @date 2021-08-19
 */
@Builder
@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CodeFieldToEnumerableAdapter<C> implements Enumerable<C> {
    @NonNull
    Field codeField;
    @NonNull
    Object adaptee;

    @SneakyThrows
    @Override
    public C code() {
        //noinspection unchecked
        return (C) codeField.get(adaptee);
    }

}
