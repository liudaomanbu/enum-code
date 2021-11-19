package org.caotc.code.service.impl;

import com.google.common.collect.ImmutableSet;
import lombok.NonNull;
import lombok.Value;
import org.caotc.code.service.DictionaryConverterService;

/**
 * @author caotc
 * @date 2021-11-19
 */
@Value
public class DefaultDictionaryConverterService implements DictionaryConverterService {
    @Override
    public boolean containsGroup(@NonNull String group) {
        return false;
    }

    @Override
    public boolean addGroup(@NonNull String group, @NonNull Class<?> type) {
        return false;
    }

    @Override
    public @NonNull ImmutableSet<String> groups(@NonNull Class<?> type) {
        return null;
    }
}
