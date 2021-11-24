package org.caotc.code.service;

import com.google.common.collect.ImmutableSet;
import lombok.NonNull;

/**
 * @author caotc
 * @date 2021-11-12
 */
public interface DictionaryGroupService {

    boolean containsGroup(@NonNull String group);

    default boolean containsGroup(@NonNull Class<?> type) {
        return !groups(type).isEmpty();
    }

    @NonNull
    ImmutableSet<String> groups(@NonNull Class<?> type);

    boolean addGroupIfAbsent(@NonNull String group, @NonNull Class<?> type);

    void addGroup(@NonNull String group, @NonNull Class<?> type);

    void removeGroup(@NonNull String group);

    void removeAllGroup(@NonNull Class<?> type);


}
