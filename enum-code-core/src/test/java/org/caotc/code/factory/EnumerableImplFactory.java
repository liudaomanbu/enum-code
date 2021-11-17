package org.caotc.code.factory;

import com.google.common.collect.ImmutableSet;
import lombok.NonNull;
import lombok.Value;
import org.caotc.code.common.ReaderConstant;
import org.caotc.code.model.DictionaryImpl;

import java.util.Objects;

/**
 * @author caotc
 * @date 2021-10-08
 */
@Value
public class EnumerableImplFactory implements EnumerableAdapteeConstantFactory<DictionaryImpl> {

    @SuppressWarnings("unchecked")
    @Override
    public @NonNull <F extends DictionaryImpl> ImmutableSet<F> create(@NonNull Class<F> type, @NonNull String group) {
        return ImmutableSet.of((F) DictionaryImpl.INSTANCE);
    }

    @Override
    public @NonNull ImmutableSet<String> groups(@NonNull Class<?> type) {
        if (Objects.equals(DictionaryImpl.class, type)) {
            return ReaderConstant.DEFAULT_GROUPS;
        }
        return ImmutableSet.of();
    }

}
