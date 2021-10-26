package org.caotc.code.common;

import com.google.common.collect.ImmutableSet;
import lombok.experimental.UtilityClass;

import java.util.function.Function;

/**
 * @author caotc
 * @date 2021-10-16
 */
@UtilityClass
public class GroupConstant {
    public static final String DEFAULT = "default";
    public static final ImmutableSet<String> DEFAULTS = ImmutableSet.of(GroupConstant.DEFAULT);
    public static final Function<Object, String> DEFAULT_READER = t -> DEFAULT;
}
