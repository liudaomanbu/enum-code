package org.caotc.code.service;

import com.google.common.collect.ImmutableSet;
import lombok.NonNull;
import org.caotc.code.DictionaryGroup;

/**
 * @author caotc
 * @date 2021-11-12
 */
public interface DictionaryGroupService {
    <T> DictionaryGroup<T> findByName(@NonNull String groupName);

    <T> ImmutableSet<DictionaryGroup<T>> listByType(@NonNull Class<T> type);
}
