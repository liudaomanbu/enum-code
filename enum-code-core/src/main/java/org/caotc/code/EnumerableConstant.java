package org.caotc.code;

import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableSet;
import lombok.*;
import org.caotc.code.adapter.EnumerableAdapter;

import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

/**
 * @author caotc
 * @date 2021-08-19
 */
@Value
@Builder
public class EnumerableConstant<C> implements Set<Enumerable<C>> {
    @NonNull
    Class<?> originalType;

    @NonNull
    String group;

    @NonNull
    @Singular
    ImmutableSet<Enumerable<C>> enumerables;

    @NonNull
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @Getter(value = AccessLevel.PRIVATE, lazy = true)
    ImmutableBiMap<C, Enumerable<C>> codeToEnumerableMap = enumerables().stream()
            .collect(ImmutableBiMap.toImmutableBiMap(Enumerable::code
                    , Function.identity()));

    @NonNull
    public Optional<Enumerable<C>> find(@NonNull C code) {
        return Optional.ofNullable(codeToEnumerableMap().get(code));
    }

    @NonNull
    public <E> Optional<E> findAndUnWarpIfNecessary(@NonNull C code) {
        return find(code)
                .map(this::unWarpIfNecessary);
    }

    @SuppressWarnings("unchecked")
    private <E> E unWarpIfNecessary(@NonNull Object enumerable) {
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
    public int size() {
        return enumerables.size();
    }

    @Override
    public boolean isEmpty() {
        return enumerables.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return enumerables.contains(o);
    }

    @Override
    public Iterator<Enumerable<C>> iterator() {
        return enumerables.iterator();
    }

    @Override
    public Object[] toArray() {
        return enumerables.toArray();
    }

    @SuppressWarnings({"NullableProblems", "SuspiciousToArrayCall"})
    @Override
    public <T> T[] toArray(T[] a) {
        return enumerables.toArray(a);
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean add(Enumerable<C> cEnumerable) {
        return enumerables.add(cEnumerable);
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean remove(Object o) {
        return enumerables.remove(o);
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public boolean containsAll(Collection<?> c) {
        return enumerables.containsAll(c);
    }

    @SuppressWarnings({"deprecation", "NullableProblems"})
    @Override
    public boolean addAll(Collection<? extends Enumerable<C>> c) {
        return enumerables.addAll(c);
    }

    @SuppressWarnings({"deprecation", "NullableProblems"})
    @Override
    public boolean retainAll(Collection<?> c) {
        return enumerables.retainAll(c);
    }

    @SuppressWarnings({"deprecation", "NullableProblems"})
    @Override
    public boolean removeAll(Collection<?> c) {
        return enumerables.removeAll(c);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void clear() {
        enumerables.clear();
    }
}
