package org.caotc.code.service;

import com.google.common.collect.ImmutableSet;
import lombok.NonNull;
import org.caotc.code.EnumerableGroup;

/**
 * @author caotc
 * @date 2021-11-12
 */
public interface EnumerableGroupService {
    <T> EnumerableGroup<T> findByName(@NonNull String groupName);

    <T> ImmutableSet<EnumerableGroup<T>> listByType(@NonNull Class<T> type);
}
