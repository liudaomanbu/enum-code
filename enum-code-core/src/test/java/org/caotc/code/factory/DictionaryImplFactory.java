package org.caotc.code.factory;

import com.google.common.collect.ImmutableSet;
import lombok.NonNull;
import lombok.Value;
import org.caotc.code.model.DictionaryImpl;

/**
 * @author caotc
 * @date 2021-10-08
 */
@Value
public class DictionaryImplFactory implements DictionaryAdapteeConstantFactory<DictionaryImpl> {

    @Override
    public boolean support(@NonNull Class<?> type) {
        return DictionaryImpl.class.equals(type);
    }

    @SuppressWarnings("unchecked")
    @Override
    public @NonNull <F extends DictionaryImpl> ImmutableSet<F> create(@NonNull Class<F> type) {
        return ImmutableSet.of((F) DictionaryImpl.INSTANCE);
    }
}
