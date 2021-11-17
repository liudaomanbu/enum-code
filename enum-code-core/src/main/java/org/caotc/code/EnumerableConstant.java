package org.caotc.code;

import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableSet;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Singular;
import lombok.ToString;
import lombok.Value;
import org.caotc.code.adapter.EnumerableAdapter;

import java.util.Optional;
import java.util.function.Function;

/**
 * @author caotc
 * @date 2021-08-19
 */
@Value
@Builder
public class EnumerableConstant<C, E> implements EnumerableConverter<C, E> {
    @NonNull
    Class<E> originalType;

    @NonNull
    String group;

    @NonNull
    @Singular
    ImmutableSet<Enumerable<C, E>> enumerables;

    @NonNull
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @Getter(value = AccessLevel.PUBLIC, lazy = true)
    ImmutableBiMap<C, E> codeToEnumerableAdaptee = enumerables().stream()
            .collect(ImmutableBiMap.toImmutableBiMap(Enumerable::code, this::unWarpIfNecessary));

    @NonNull
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @Getter(value = AccessLevel.PUBLIC, lazy = true)
    ImmutableBiMap<E, C> enumerableAdapteeToCode = codeToEnumerableAdaptee().inverse();

    @NonNull
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @Getter(value = AccessLevel.PRIVATE, lazy = true)
    ImmutableBiMap<C, Enumerable<C, E>> codeToEnumerable = enumerables().stream()
            .collect(ImmutableBiMap.toImmutableBiMap(Enumerable::code, Function.identity()));

    @NonNull
    public Optional<Enumerable<C, E>> find(@NonNull C code) {
        return Optional.ofNullable(codeToEnumerable().get(code));
    }

    @NonNull
    public Optional<E> findAndUnWarpIfNecessary(@NonNull C code) {
        return find(code)
                .map(this::unWarpIfNecessary);
    }

    @SuppressWarnings("unchecked")
    private E unWarpIfNecessary(@NonNull Object enumerable) {
        if (originalType().isInstance(enumerable)) {
            return (E) enumerable;
        }
        if (enumerable instanceof EnumerableAdapter) {
            return ((EnumerableAdapter<E, ?>) enumerable).adaptee();
        }
        //todo
        throw new IllegalStateException(originalType() + " EnumerableConstant enumerable class is " + enumerable.getClass());
    }

    @Override
    public @NonNull Optional<E> valueOf(@NonNull C code) {
        return find(code)
                .map(this::unWarpIfNecessary);
    }

    @Override
    public @NonNull C toCode(@NonNull E enumerable) {
        return enumerableAdapteeToCode().get(enumerable);
    }
}
