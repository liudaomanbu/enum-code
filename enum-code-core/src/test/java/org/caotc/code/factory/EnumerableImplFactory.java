package org.caotc.code.factory;

import com.google.common.collect.ImmutableSet;
import lombok.NonNull;
import lombok.Value;
import org.caotc.code.model.EnumerableImpl;

import java.util.Objects;

/**
 * @author caotc
 * @date 2021-10-08
 */
@Value
public class EnumerableImplFactory implements EnumerableAdapteeConstantFactory<EnumerableImpl> {

    @SuppressWarnings("unchecked")
    @Override
    public @NonNull <F extends EnumerableImpl> ImmutableSet<F> create(@NonNull Class<F> type, String group) {
        return ImmutableSet.of((F) EnumerableImpl.INSTANCE);
    }

    @Override
    public boolean support(@NonNull Class<?> type, String group) {
        return Objects.equals(EnumerableImpl.class, type);
    }
}
