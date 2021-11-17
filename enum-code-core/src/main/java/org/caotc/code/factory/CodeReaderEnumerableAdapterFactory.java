package org.caotc.code.factory;

import lombok.NonNull;
import lombok.Value;
import org.caotc.code.Enumerable;
import org.caotc.code.adapter.EnumerableAdapterImpl;
import org.caotc.code.common.ReaderConstant;
import org.caotc.code.util.EnumerableUtil;

import java.util.function.Function;

/**
 * @author caotc
 * @date 2021-08-25
 */
@Value
public class CodeReaderEnumerableAdapterFactory implements EnumerableAdapterFactory<Object> {

    @Override
    public boolean canAdapt(@NonNull Class<?> type) {
        return EnumerableUtil.isEnumerable(type);
    }

    @Override
    public @NonNull <C, E> Enumerable<C, E> adapt(@NonNull E adaptee, @NonNull Function<? super E, String> groupReader) {
        EnumerableUtil.checkEnumerable(adaptee.getClass());
        return EnumerableAdapterImpl.<E, C>builder()
                .adaptee(adaptee)
                .codeReader(EnumerableUtil.findReaderExact(adaptee, org.caotc.code.annotation.Enumerable.Code.class))
                .nameReader(EnumerableUtil.<E, String>findReader(adaptee, org.caotc.code.annotation.Enumerable.Name.class)
                        .orElseGet(ReaderConstant::defaultNameReader))
                .descriptionReader(EnumerableUtil.<E, String>findReader(adaptee, org.caotc.code.annotation.Enumerable.Description.class)
                        .orElse(ReaderConstant.defaultGroupReader()))
                .groupReader(groupReader)
                .build();
    }

}
