package org.caotc.code.common;

import lombok.experimental.UtilityClass;

import java.util.function.Function;

/**
 * @author caotc
 * @date 2021-10-16
 */
@UtilityClass
public class GroupConstant {
    public static final String DEFAULT = "default";
    public static final Function<Object, String> DEFAULT_READER = t -> DEFAULT;
}
