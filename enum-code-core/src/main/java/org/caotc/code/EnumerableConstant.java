package org.caotc.code;

import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableSet;
import lombok.*;

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
    @Singular
    ImmutableSet<Enumerable<C>> values;

    @Getter(value=AccessLevel.PRIVATE,lazy = true)
    ImmutableBiMap<C,Enumerable<C>> codeToEnumerableMap=values.stream()
            .collect(ImmutableBiMap.toImmutableBiMap(Enumerable::code, Function.identity()));

    @NonNull
    public Optional<Enumerable<C>> findByCode(@NonNull C code) {
        return Optional.ofNullable(codeToEnumerableMap().get(code));
    }

    @Override
    public int size() {
        return values.size();
    }

    @Override
    public boolean isEmpty() {
        return values.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return values.contains(o);
    }

    @Override
    public Iterator<Enumerable<C>> iterator() {
        return values.iterator();
    }

    @Override
    public Object[] toArray() {
        return values.toArray();
    }

    @SuppressWarnings({"NullableProblems", "SuspiciousToArrayCall"})
    @Override
    public <T> T[] toArray(T[] a) {
        return values.toArray(a);
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean add(Enumerable<C> cEnumerable) {
        return values.add(cEnumerable);
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean remove(Object o) {
        return values.remove(o);
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public boolean containsAll(Collection<?> c) {
        return values.containsAll(c);
    }

    @SuppressWarnings({"deprecation", "NullableProblems"})
    @Override
    public boolean addAll(Collection<? extends Enumerable<C>> c) {
        return values.addAll(c);
    }

    @SuppressWarnings({"deprecation", "NullableProblems"})
    @Override
    public boolean retainAll(Collection<?> c) {
        return values.retainAll(c);
    }

    @SuppressWarnings({"deprecation", "NullableProblems"})
    @Override
    public boolean removeAll(Collection<?> c) {
        return values.removeAll(c);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void clear() {
        values.clear();
    }
}
