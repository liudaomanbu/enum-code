package org.caotc.code.service.impl;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;
import lombok.NonNull;
import lombok.Value;
import org.caotc.code.DictionaryConstant;
import org.caotc.code.DictionaryConverter;
import org.caotc.code.annotation.Dictionary;
import org.caotc.code.service.DictionaryConverterFactoryService;
import org.caotc.code.service.DictionaryGroupService;
import org.caotc.code.service.DictionaryService;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

/**
 * @author caotc
 * @date 2021-10-08
 */
@Value
public class DefaultDictionaryService implements DictionaryService {
    DictionaryConverterFactoryService dictionaryConverterFactoryService;
    DictionaryGroupService dictionaryGroupService;
    Map<String, DictionaryConverter<?, ?>> groupToDictionaryConverter = Maps.newHashMap();

    synchronized public void evict(@NonNull Class<?> type) {
        ImmutableSet<String> groups = dictionaryGroupService.groups(type);
        dictionaryGroupService.removeAllGroup(type);
        if (!groups.isEmpty()) {
            groups.forEach(groupToDictionaryConverter::remove);
        }
    }

    synchronized public void evict(@NonNull String group) {
        groupToDictionaryConverter.remove(group);
        dictionaryGroupService.removeGroup(group);
    }

    /**
     * <p>
     * 值映射为枚举
     * </p>
     *
     * @param enumerableClass 枚举类
     * @param code            枚举值
     * @param <E>             对应枚举
     * @throws IllegalArgumentException 如果该枚举类没有{@link Dictionary.Code}注解的属性和方法
     * @author caotc
     * @date 2021-08-01
     * @since 1.0.0
     */
    @SuppressWarnings("unchecked")
    @NonNull
    public <C, E> Optional<E> valueOf(@NonNull Class<E> enumerableClass, @NonNull C code) {
        initIfNecessary(enumerableClass);
        Set<String> groups = dictionaryGroupService.groups(enumerableClass);
        if (groups.size() != 1) {
            throw new IllegalArgumentException(enumerableClass + "has groups " + groups + " can't valueOf by class");//todo
        }
        DictionaryConstant<C, E> dictionaryConstant = (DictionaryConstant<C, E>) groupToDictionaryConverter.get(Iterables.getOnlyElement(groups));
        return Optional.ofNullable(dictionaryConstant)
                .flatMap(e -> e.findAndUnWarpIfNecessary(code));
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public <C, E> Optional<E> valueOf(@NonNull String group, @NonNull C code) {
        initIfNecessary(group);
        DictionaryConverter<C, E> dictionaryConverter = (DictionaryConverter<C, E>) groupToDictionaryConverter.get(group);
        return Optional.ofNullable(dictionaryConverter)
                .flatMap(e -> e.valueOf(code));
    }

    @NonNull
    public <C, E> E valueOfExact(@NonNull Class<E> enumerableClass, @NonNull C code) {
        return valueOf(enumerableClass, code)
                //todo
                .orElseThrow(() -> new IllegalStateException(enumerableClass + " DictionaryConstant not contains dictionary of code" + code));
    }

    @NonNull
    public <C, E> E valueOfExact(@NonNull String group, @NonNull C code) {
        return this.<C, E>valueOf(group, code)
                //todo
                .orElseThrow(() -> new IllegalStateException(group + " DictionaryConstant not contains dictionary of code" + code));
    }

    @NonNull
    public <C, E> Optional<E> valueOfNullable(Class<E> enumerableClass, C code) {
        return Optional.ofNullable(code)
                .flatMap(c -> valueOf(enumerableClass, c));
    }

    @NonNull
    public <C, E> Optional<E> valueOfNullable(String group, C code) {
        return Optional.ofNullable(code)
                .flatMap(c -> valueOf(group, c));
    }

    /**
     * 获取枚举对应的值
     *
     * @param enumerable 枚举
     * @return 枚举对应的值
     * @throws IllegalArgumentException 如果该枚举类没有{@link Dictionary.Code}注解的属性和方法
     * @author caotc
     * @date 2021-08-01
     * @since 1.0.0
     */
    @SuppressWarnings("unchecked")
    @NonNull
    public <C, E> C toCode(@NonNull E enumerable) {
        initIfNecessary(enumerable.getClass());
        //todo class extends
        Set<String> groups = dictionaryGroupService.groups(enumerable.getClass());
        if (groups.size() != 1) {
            throw new IllegalArgumentException(enumerable.getClass() + "has groups " + groups + " can't valueOf by class");//todo
        }
        String group = Iterables.getOnlyElement(groups);
        //调用方应该知道结果类型,由调用方决定返回类型,无需调用方强转
        DictionaryConverter<C, E> dictionaryConverter = (DictionaryConverter<C, E>) groupToDictionaryConverter.get(group);
        if (Objects.isNull(dictionaryConverter)) {
            throw new IllegalArgumentException("dictionaryConverter for group " + group + " not exist");//todo
        }
        return dictionaryConverter.toCode(enumerable);
    }

    public <C, E> C toCodeNullable(E enumerable) {
        return Optional.ofNullable(enumerable)
                .map(this::<C, E>toCode)
                .orElse(null);
    }

    private <E> void initIfNecessary(@NonNull Class<E> enumerableClass) {
        if (!dictionaryGroupService.containsGroup(enumerableClass)) {
            synchronized (this) {
                if (!dictionaryGroupService.containsGroup(enumerableClass)) {
                    dictionaryConverterFactoryService.create(enumerableClass).forEach(this::register);
                }
            }
        }
    }

    private <E> void initIfNecessary(@NonNull String group) {
        if (!dictionaryGroupService.containsGroup(group)) {
            synchronized (this) {
                if (!dictionaryGroupService.containsGroup(group)) {
                    register(dictionaryConverterFactoryService.create(group));
                }
            }
        }
    }

    private void register(@NonNull DictionaryConverter<?, ?> dictionaryConverter) {
        groupToDictionaryConverter.put(dictionaryConverter.group(), dictionaryConverter);
        //todo DictionaryConverter type
        dictionaryGroupService.addGroup(dictionaryConverter.group(), ((DictionaryConstant<?, ?>) dictionaryConverter).originalType());
    }
}
