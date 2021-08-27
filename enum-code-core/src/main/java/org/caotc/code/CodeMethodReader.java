package org.caotc.code;

import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.Value;

import java.lang.reflect.Method;

/**
 * @author caotc
 * @date 2021-08-24
 */
@Value
public class CodeMethodReader<E, C> extends CodeReader<E, C> {
    @NonNull
    Method method;

    @SuppressWarnings("unchecked")
    @SneakyThrows
    @Override
    public @NonNull C read(@NonNull E enumerableAdaptee) {
        return (C) method.invoke(enumerableAdaptee);
    }
}
