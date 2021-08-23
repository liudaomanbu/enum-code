package org.caotc.code;

import com.google.common.collect.ImmutableSet;
import lombok.Builder;
import lombok.NonNull;
import lombok.Singular;
import lombok.Value;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/**
 * @author caotc
 * @date 2021-08-19
 */
@Value
@Builder
public class EnumerableConstants<E extends Enumerable<C>,C> implements Set<E> {
    @NonNull
    @Singular
    ImmutableSet<E> values;

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
    public Iterator<E> iterator() {
        return values.iterator();
    }

    @Override
    public Object[] toArray() {
        return values.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return values.toArray(a);
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean add(E e) {
        return values.add(e);
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean remove(Object o) {
        return values.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return values.containsAll(c);
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean addAll(Collection<? extends E> c) {
        return values.addAll(c);
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean retainAll(Collection<?> c) {
        return values.retainAll(c);
    }

    @SuppressWarnings("deprecation")
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
