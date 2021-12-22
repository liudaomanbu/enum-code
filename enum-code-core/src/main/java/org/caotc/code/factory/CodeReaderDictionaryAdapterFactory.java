package org.caotc.code.factory;

import lombok.NonNull;
import lombok.Value;
import org.caotc.code.Dictionary;
import org.caotc.code.adapter.DictionaryAdapterImpl;
import org.caotc.code.common.ReaderConstant;
import org.caotc.code.util.DictionaryUtil;
import org.caotc.code.util.ReflectUtil;

import java.util.Optional;
import java.util.function.Function;

/**
 * @author caotc
 * @date 2021-08-25
 */
@Value
public class CodeReaderDictionaryAdapterFactory implements DictionaryAdapterFactory<Object> {

    @Override
    public boolean canAdapt(@NonNull Class<?> type) {
        return DictionaryUtil.isEnumerable(type);
    }

    @Override
    public @NonNull <C, F> Dictionary<C, F> adapt(@NonNull F adaptee) {
        DictionaryUtil.checkEnumerable(adaptee.getClass());
        return DictionaryAdapterImpl.<F, C>builder()
                .adaptee(adaptee)
                .codeReader(ReflectUtil.findReaderExact(adaptee, org.caotc.code.annotation.Dictionary.Code.class))
                .nameReader(ReflectUtil.<F, String>findReader(adaptee, org.caotc.code.annotation.Dictionary.Name.class)
                        .orElseGet(ReaderConstant::defaultNameReader))
                .descriptionReader(ReflectUtil.<F, String>findReader(adaptee, org.caotc.code.annotation.Dictionary.Description.class)
                        .orElseGet(ReaderConstant::defaultDescriptionReader))
                .groupReader(ReflectUtil.<F, String>findReader(adaptee, org.caotc.code.annotation.Dictionary.Group.class)
                        .orElseGet(() -> groupReader(adaptee)))
                .build();
    }

    private static <E> Function<E, String> groupReader(E adaptee) {
        return Optional.ofNullable(adaptee.getClass().getAnnotation(org.caotc.code.annotation.Dictionary.class))
                .map(org.caotc.code.annotation.Dictionary::group)
                .map(group -> (Function<E, String>) a -> group)
                .orElseGet(ReaderConstant::defaultGroupReader);
    }
}
