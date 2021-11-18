package org.caotc.code;

import lombok.NonNull;

import java.util.Optional;

/**
 * @author caotc
 * @date 2021-11-12
 */
public interface DictionaryConverter<C, E> {
    @NonNull
    String group();

    @NonNull
    Optional<E> valueOf(@NonNull C code);

    @NonNull
    default E valueOfExact(@NonNull C code) {
        return valueOf(code)
                //todo
                .orElseThrow(() -> new IllegalStateException(group() + " Dictionary not contains dictionary of code" + code));
    }

    @NonNull C toCode(@NonNull E enumerable);

}
