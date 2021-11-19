package org.caotc.code.service;

import com.google.common.collect.ImmutableSet;
import lombok.NonNull;

/**
 * @author caotc
 * @date 2021-11-12
 */
public interface DictionaryConverterService {

    boolean containsGroup(@NonNull String group);

    //todo 不允许group重复
    boolean addGroup(@NonNull String group, @NonNull Class<?> type);

    @NonNull
    ImmutableSet<String> groups(@NonNull Class<?> type);
}
