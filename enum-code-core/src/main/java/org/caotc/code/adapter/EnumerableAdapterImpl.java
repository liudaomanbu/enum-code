package org.caotc.code.adapter;

import lombok.*;
import org.caotc.code.CodeReader;

/**
 * @author caotc
 * @date 2021-08-19
 */
@Builder
@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class EnumerableAdapterImpl<E,C> implements EnumerableAdapter<E,C> {
    @NonNull
    CodeReader<E,C> codeReader;
    @NonNull
    E adaptee;

    @Override
    public @NonNull C code() {
        return codeReader.read(adaptee);
    }

}
