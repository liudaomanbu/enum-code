package org.caotc.code.adapter;

import lombok.*;
import org.caotc.code.CodeReader;
import org.caotc.code.Enumerable;

import java.lang.reflect.Field;
import java.util.function.Function;

/**
 * @author caotc
 * @date 2021-08-19
 */
@Builder
@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class EnumerableAdapter<E,C> implements Enumerable<C> {
    @NonNull
    CodeReader<E,C> codeReader;
    @NonNull
    E adaptee;

    @Override
    public C code() {
        return codeReader.read(adaptee);
    }

}
