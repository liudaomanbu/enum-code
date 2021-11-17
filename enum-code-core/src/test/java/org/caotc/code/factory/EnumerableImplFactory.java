package org.caotc.code.factory;

import com.google.common.collect.ImmutableSet;
import lombok.NonNull;
import lombok.Value;
import org.caotc.code.common.ReaderConstant;
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
    public @NonNull <F extends EnumerableImpl> ImmutableSet<F> create(@NonNull Class<F> type, @NonNull String group) {
        return ImmutableSet.of((F) EnumerableImpl.INSTANCE);
    }

    @Override
    public @NonNull ImmutableSet<String> groups(@NonNull Class<?> type) {
        if (Objects.equals(EnumerableImpl.class, type)) {
            return ReaderConstant.DEFAULT_GROUPS;
        }
        return ImmutableSet.of();
    }

}
