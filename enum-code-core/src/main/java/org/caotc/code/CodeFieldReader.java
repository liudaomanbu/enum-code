package org.caotc.code;

import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.Value;

import java.lang.reflect.Field;

/**
 * @author caotc
 * @date 2021-08-24
 */
@Value
public class CodeFieldReader<E, C> extends CodeReader<E, C> {
    @NonNull
    Field field;

    public CodeFieldReader(@NonNull Field field) {
        this.field = field;
        field.setAccessible(Boolean.TRUE);
    }

    @SuppressWarnings("unchecked")
    @SneakyThrows
    @Override
    public @NonNull C read(@NonNull E enumerableAdaptee) {
        return (C) field.get(enumerableAdaptee);
    }
}
