package org.caotc.code;

import lombok.NonNull;
import org.caotc.code.util.EnumerableUtil;

import java.util.function.Function;

/**
 * @author caotc
 * @date 2021-08-20
 */
public class CodeAnnotationParser {
    @NonNull
    public static <E,C> Function<E,C> parse(@NonNull Class<E> type){
        EnumerableUtil.checkEnumerable(type);
        return null;
    }
}
