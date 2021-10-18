package org.caotc.code;

import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.Value;

import java.lang.reflect.Method;
import java.util.function.Function;

/**
 * @author caotc
 * @date 2021-08-24
 */
@Value
public class CodeMethodReader<E, C> implements Function<E, C> {
    @NonNull
    Method method;

    @SuppressWarnings("unchecked")
    @SneakyThrows
    @Override
    public @NonNull C apply(@NonNull E enumerableAdaptee) {
        return (C) method.invoke(enumerableAdaptee);
    }
}
