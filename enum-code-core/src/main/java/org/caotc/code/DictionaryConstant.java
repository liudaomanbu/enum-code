package org.caotc.code;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Singular;
import lombok.ToString;
import lombok.Value;
import org.caotc.code.adapter.DictionaryAdapter;

import java.util.Optional;
import java.util.function.Function;

/**
 * @author caotc
 * @date 2021-08-19
 */
@Value
@Builder
public class DictionaryConstant<C, E> implements DictionaryConverter<C, E> {
    @NonNull
    @Singular
    ImmutableSet<Dictionary<C, E>> dictionaries;

    @NonNull
    Class<E> originalType;

    @NonNull
    @Getter(value = AccessLevel.PUBLIC, lazy = true)
    String group = initGroup();

    @NonNull
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @Getter(value = AccessLevel.PUBLIC, lazy = true)
    ImmutableBiMap<C, E> codeToEnumerableAdaptee = dictionaries().stream()
            .collect(ImmutableBiMap.toImmutableBiMap(Dictionary::code, this::unWarpIfNecessary));

    @NonNull
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @Getter(value = AccessLevel.PUBLIC, lazy = true)
    ImmutableBiMap<E, C> enumerableAdapteeToCode = codeToEnumerableAdaptee().inverse();

    @NonNull
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @Getter(value = AccessLevel.PRIVATE, lazy = true)
    ImmutableBiMap<C, Dictionary<C, E>> codeToEnumerable = dictionaries().stream()
            .collect(ImmutableBiMap.toImmutableBiMap(Dictionary::code, Function.identity()));

    @NonNull
    public Optional<Dictionary<C, E>> find(@NonNull C code) {
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
        if (enumerable instanceof DictionaryAdapter) {
            return ((DictionaryAdapter<E, ?>) enumerable).adaptee();
        }
        //todo
        throw new IllegalStateException(originalType() + " DictionaryConstant dictionary class is " + enumerable.getClass());
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

    private String initGroup() {
        ImmutableSet<String> groups = dictionaries().stream().map(Dictionary::group).collect(ImmutableSet.toImmutableSet());
        Preconditions.checkArgument(groups.size() == 1, "groups %s is not only", groups);
        return Iterables.getOnlyElement(groups);
    }
}
