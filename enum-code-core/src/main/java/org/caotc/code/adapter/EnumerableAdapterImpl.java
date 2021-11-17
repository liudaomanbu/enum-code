package org.caotc.code.adapter;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;
import lombok.Value;

import java.util.function.Function;

/**
 * @author caotc≈
 * @date 2021-08-19
 */
@Builder
@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class EnumerableAdapterImpl<E, C> implements EnumerableAdapter<E, C> {
    @NonNull
    E adaptee;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @NonNull
    Function<? super E, C> codeReader;
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @NonNull
    Function<? super E, String> nameReader;
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @NonNull
    Function<? super E, String> descriptionReader;
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @NonNull
    Function<? super E, String> groupReader;

    @EqualsAndHashCode.Include
    @ToString.Include
    @Override
    public @NonNull C code() {
        return codeReader.apply(adaptee);
    }

    @Override
    public @NonNull String name() {
        return nameReader.apply(adaptee);
    }

    @Override
    public @NonNull String description() {
        return descriptionReader.apply(adaptee);
    }

    @EqualsAndHashCode.Include
    @ToString.Include
    @Override
    public @NonNull String group() {
        return groupReader.apply(adaptee);
    }

    @Override
    public @NonNull E unwrap() {
        return adaptee;
    }

}
