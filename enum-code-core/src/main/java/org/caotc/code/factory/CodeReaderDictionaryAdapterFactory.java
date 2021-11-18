package org.caotc.code.factory;

import lombok.NonNull;
import lombok.Value;
import org.caotc.code.Dictionary;
import org.caotc.code.adapter.DictionaryAdapterImpl;
import org.caotc.code.common.ReaderConstant;
import org.caotc.code.util.DictionaryUtil;

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
    public @NonNull <C, E> Dictionary<C, E> adapt(@NonNull E adaptee, @NonNull Function<? super E, String> groupReader) {
        DictionaryUtil.checkEnumerable(adaptee.getClass());
        return DictionaryAdapterImpl.<E, C>builder()
                .adaptee(adaptee)
                .codeReader(DictionaryUtil.findReaderExact(adaptee, org.caotc.code.annotation.Dictionary.Code.class))
                .nameReader(DictionaryUtil.<E, String>findReader(adaptee, org.caotc.code.annotation.Dictionary.Name.class)
                        .orElseGet(ReaderConstant::defaultNameReader))
                .descriptionReader(DictionaryUtil.<E, String>findReader(adaptee, org.caotc.code.annotation.Dictionary.Description.class)
                        .orElse(ReaderConstant.defaultDescriptionReader()))
                .groupReader(groupReader)
                .build();
    }

}
