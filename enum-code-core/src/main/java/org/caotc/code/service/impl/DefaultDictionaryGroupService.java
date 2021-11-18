package org.caotc.code.service.impl;

import com.google.common.collect.ImmutableSet;
import lombok.NonNull;
import lombok.Value;
import org.caotc.code.DictionaryGroup;
import org.caotc.code.service.DictionaryGroupService;

/**
 * @author caotc
 * @date 2021-11-12
 */
@Value
public class DefaultDictionaryGroupService implements DictionaryGroupService {


    @Override
    public <T> DictionaryGroup<T> findByName(@NonNull String groupName) {
        return null;
    }

    @Override
    public <T> ImmutableSet<DictionaryGroup<T>> listByType(@NonNull Class<T> type) {
        return null;
    }
}
