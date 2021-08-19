package org.caotc.code.adapter;

import lombok.*;
import org.caotc.code.Enumerable;

import java.lang.reflect.Method;

/**
 * @author caotc
 * @date 2021-08-19
 */
@Builder
@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CodeMethodToEnumerableAdapter<C> implements Enumerable<C> {
    @NonNull
    Method codeMethod;
    @NonNull
    Object adaptee;

    @SneakyThrows
    @Override
    public C code() {
        //noinspection unchecked
        return (C) codeMethod.invoke(adaptee);
    }

}