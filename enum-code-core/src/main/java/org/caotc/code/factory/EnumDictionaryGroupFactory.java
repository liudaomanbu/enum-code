package org.caotc.code.factory;

import lombok.Value;

/**
 * @author caotc
 * @date 2021-11-08
 */
@Value
public class EnumDictionaryGroupFactory<E extends Enum<?>> implements DictionaryGroupFactory<E> {

//    @Override
//    public boolean support(@NonNull String group) {
////        Class.forName()
////        ClassLoader.getSystemClassLoader().loadClass()
//        return false;
//    }
//
//    @Override
//    public @NonNull <T extends E> DictionaryGroup<T> create(@NonNull String group) {
//        return DictionaryGroup.<T>builder().name(type.getName()).type(type).build();
//    }
}
