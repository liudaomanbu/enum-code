package org.caotc.code.model;

import lombok.NonNull;
import lombok.Value;
import org.caotc.code.Dictionary;
import org.caotc.code.common.ReaderConstant;

/**
 * @author caotc
 * @date 2021-09-02
 */
@Value
public class DictionaryImpl implements Dictionary<Integer, DictionaryImpl> {
    public static final DictionaryImpl INSTANCE = new DictionaryImpl(0);
    @NonNull
    Integer value;

    public @NonNull Integer code() {
        return value;
    }

    @Override
    public @NonNull String group() {
        return ReaderConstant.DEFAULT_GROUP;
    }

    @Override
    public @NonNull DictionaryImpl unwrap() {
        return this;
    }
}
