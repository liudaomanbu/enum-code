package org.caotc.code.factory;

import lombok.NonNull;
import lombok.Value;
import org.caotc.code.Enumerable;
import org.caotc.code.adapter.EnumerableAdapterImpl;
import org.caotc.code.util.EnumerableUtil;

/**
 * @author caotc
 * @date 2021-08-25
 */
@Value
public class CodeReaderEnumerableAdapterFactory implements EnumerableAdapterFactory<Object>{

    @Override
    public boolean canAdapt(@NonNull Class<?> type) {
        return EnumerableUtil.isEnumerable(type);
    }

    @Override
    public @NonNull <C> Enumerable<C> adapt(@NonNull Object adaptee) {
        return EnumerableAdapterImpl.<Object,C>builder()
                .adaptee(adaptee)
                .codeReader(EnumerableUtil.findCodeReaderExact(adaptee))
                .build();
    }

}
