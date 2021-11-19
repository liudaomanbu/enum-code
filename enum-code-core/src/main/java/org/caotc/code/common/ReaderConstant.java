package org.caotc.code.common;

import lombok.experimental.UtilityClass;

import java.util.function.Function;

/**
 * @author caotc
 * @date 2021-10-16
 */
@UtilityClass
public class ReaderConstant {
    public static final String DEFAULT_GROUP = "default";
    public static final Function<Object, String> DEFAULT_GROUP_READER = t -> t.getClass().getName();
    public static final Function<Object, String> DEFAULT_NAME_READER = t -> "";
    public static final Function<Object, String> DEFAULT_DESCRIPTION_READER = t -> "";

    @SuppressWarnings("unchecked")
    public static <E> Function<E, String> defaultGroupReader() {
        return (Function<E, String>) DEFAULT_GROUP_READER;
    }

    @SuppressWarnings("unchecked")
    public static <E> Function<E, String> defaultNameReader() {
        return (Function<E, String>) DEFAULT_NAME_READER;
    }

    @SuppressWarnings("unchecked")
    public static <E> Function<E, String> defaultDescriptionReader() {
        return (Function<E, String>) DEFAULT_DESCRIPTION_READER;
    }
}
