package org.caotc.code.adapter;

import lombok.SneakyThrows;
import lombok.Value;
import org.caotc.code.Enumerable;

import java.lang.reflect.Method;

/**
 * @author caotc
 * @date 2021-08-19
 */
@Value
public class CodeMethodToEnumerableAdapter<C> implements Enumerable<C> {
    Method codeMethod;
    Object adaptee;

    @SneakyThrows
    @Override
    public C code() {
        return (C) codeMethod.invoke(adaptee);
    }
}
