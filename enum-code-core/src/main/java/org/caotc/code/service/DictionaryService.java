package org.caotc.code.service;

import lombok.NonNull;

import java.util.Optional;

/**
 * @author caotc
 * @date 2021-10-08
 */
public interface DictionaryService {

     void evict(@NonNull Class<?> type);

    void evict(@NonNull String group);

    @NonNull <C, E> Optional<E> valueOf(@NonNull Class<E> enumerableClass, @NonNull C code);

    @NonNull <C, E> Optional<E> valueOf(@NonNull String group, @NonNull C code);

    @NonNull
    default <C, E> E valueOfExact(@NonNull Class<E> enumerableClass, @NonNull C code) {
        return valueOf(enumerableClass, code)
                //todo
                .orElseThrow(() -> new IllegalStateException(enumerableClass + " DictionaryConstant not contains dictionary of code" + code));
    }

    @NonNull
    default <C, E> E valueOfExact(@NonNull String group, @NonNull C code) {
        return this.<C, E>valueOf(group, code)
                //todo
                .orElseThrow(() -> new IllegalStateException(group + " DictionaryConstant not contains dictionary of code" + code));
    }

    @NonNull
    default <C, E> Optional<E> valueOfNullable(Class<E> enumerableClass, C code) {
        return Optional.ofNullable(code)
                .flatMap(c -> valueOf(enumerableClass, c));
    }

    @NonNull
    default <C, E> Optional<E> valueOfNullable(String group, C code) {
        return Optional.ofNullable(code)
                .flatMap(c -> valueOf(group, c));
    }

    @NonNull <C, E> C toCode(@NonNull E enumerable);

    default <C, E> C toCodeNullable(E enumerable) {
        return Optional.ofNullable(enumerable)
                .map(this::<C, E>toCode)
                .orElse(null);
    }
}
