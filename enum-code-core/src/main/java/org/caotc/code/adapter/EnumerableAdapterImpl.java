package org.caotc.code.adapter;

import lombok.*;

import java.util.function.Function;

/**
 * @author caotc≈
 * @date 2021-08-19
 */
@Builder
@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class EnumerableAdapterImpl<E, C> implements EnumerableAdapter<E, C> {
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @NonNull
    Function<E, C> codeReader;
    @NonNull
    E adaptee;
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @NonNull
    Function<E, String> groupReader;

    @EqualsAndHashCode.Include
    @ToString.Include
    @Override
    public @NonNull C code() {
        return codeReader.apply(adaptee);
    }

    @EqualsAndHashCode.Include
    @ToString.Include
    @Override
    public @NonNull String group() {
        return groupReader.apply(adaptee);
    }

}
