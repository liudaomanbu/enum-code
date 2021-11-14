package org.caotc.code.service.impl;

import com.google.common.collect.ImmutableSet;
import lombok.NonNull;
import lombok.Value;
import org.caotc.code.EnumerableGroup;
import org.caotc.code.service.EnumerableGroupService;

/**
 * @author caotc
 * @date 2021-11-12
 */
@Value
public class DefaultEnumerableGroupService implements EnumerableGroupService {


    @Override
    public <T> EnumerableGroup<T> findByName(@NonNull String groupName) {
        return null;
    }

    @Override
    public <T> ImmutableSet<EnumerableGroup<T>> listByType(@NonNull Class<T> type) {
        return null;
    }
}
