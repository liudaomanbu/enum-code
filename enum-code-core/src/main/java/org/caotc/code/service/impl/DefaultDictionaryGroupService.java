package org.caotc.code.service.impl;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.google.common.collect.SetMultimap;
import lombok.NonNull;
import lombok.Value;
import org.caotc.code.service.DictionaryGroupService;

import java.util.Map;
import java.util.Optional;

/**
 * @author caotc
 * @date 2021-11-12
 */
@Value
public class DefaultDictionaryGroupService implements DictionaryGroupService {
    Map<String, Class<?>> groupToClass = Maps.newHashMap();
    SetMultimap<Class<?>, String> classToGroup = HashMultimap.create();

    @Override
    public boolean containsGroup(@NonNull String group) {
        return groupToClass.containsKey(group);
    }

    @Override
    public boolean addGroupIfAbsent(@NonNull String group, @NonNull Class<?> type) {
        if (!groupToClass.containsKey(group)) {
            synchronized (this) {
                if (!groupToClass.containsKey(group)) {
                    addGroup(group, type);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    synchronized public void addGroup(@NonNull String group, @NonNull Class<?> type) {
        groupToClass.put(group, type);
        classToGroup.put(type, group);
    }

    @Override
    synchronized public void removeGroup(@NonNull String group) {
        Optional.ofNullable(groupToClass.remove(group))
                .ifPresent(type -> classToGroup.remove(type, group));
    }

    @Override
    synchronized public void removeAllGroup(@NonNull Class<?> type) {
        classToGroup.removeAll(type)
                .forEach(groupToClass::remove);
    }

    @Override
    public @NonNull ImmutableSet<String> groups(@NonNull Class<?> type) {
        return classToGroup.get(type).stream().collect(ImmutableSet.toImmutableSet());
    }
}
