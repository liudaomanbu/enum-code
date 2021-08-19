package org.caotc.code.util;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import lombok.experimental.UtilityClass;
import org.caotc.code.Enumerable;
import org.caotc.code.annotation.Code;

import java.util.*;

/**
 * 基于{@link Code}注解的枚举工具类
 *
 * @author caotc
 * @date 2021-08-01
 * @see Code
 * @since 1.0.0
 **/
@UtilityClass
public class EnumerableUtil {

    private static final Map<Enumerable<?>, Object> ENUM_TO_ENUM_VALUE_MAP = new HashMap<>();
    private static final Table<Class<? extends Enumerable<?>>, Object, Enumerable<?>> ENUM_TYPE_TO_ENUM_VALUE_TO_ENUM_TABLE= HashBasedTable.create();

    /**
     * <p>
     * 值映射为枚举
     * </p>
     *
     * @param enumClass 枚举类
     * @param value     枚举值
     * @param <E>       对应枚举
     * @throws IllegalArgumentException 如果该枚举类没有{@link Code}注解的属性和方法
     * @author caotc
     * @date 2021-08-01
     * @since 1.0.0
     */
    public static <C,E extends Enumerable<C>> E valueOf(Class<E> enumClass,C value) {
        if(Objects.isNull(value)){
            return null;
        }
        if(Objects.isNull(enumClass)){
            throw new IllegalArgumentException("enumClass can't be null");
        }
        if (!ENUM_TYPE_TO_ENUM_VALUE_TO_ENUM_TABLE.containsRow(enumClass)) {
            register(enumClass);
        }
        //put only,check in put
        @SuppressWarnings("unchecked")
        E e = (E) ENUM_TYPE_TO_ENUM_VALUE_TO_ENUM_TABLE.get(enumClass,value);
        if (Objects.isNull(e)) {
            throw new IllegalArgumentException(value + " can't convert to " + enumClass);
        }
        return e;
    }

    /**
     * 获取枚举对应的值
     *
     * @param e 枚举
     * @return 枚举对应的值
     * @throws IllegalArgumentException 如果该枚举类没有{@link Code}注解的属性和方法
     * @author caotc
     * @date 2021-08-01
     * @since 1.0.0
     */
    @SuppressWarnings("unchecked")
    public static <E extends Enumerable<C>, C> C toSimpleValue(E e) {
        if(Objects.isNull(e)){
            return null;
        }
        if (!ENUM_TO_ENUM_VALUE_MAP.containsKey(e)) {
            register(e.getClass());//todo class or superclass?
        }
        //调用方应该知道结果类型,由调用方决定返回类型,无需调用方强转
        return (C) ENUM_TO_ENUM_VALUE_MAP.get(e);
    }

    private static <C,E extends Enumerable<C>> void register(Class<E> enumClass) {
        E[] enumConstants = enumClass.getEnumConstants();//todo
        for (E enumConstant : enumConstants) {
            ENUM_TYPE_TO_ENUM_VALUE_TO_ENUM_TABLE.put(enumClass, enumConstant.code(),enumConstant);
            ENUM_TO_ENUM_VALUE_MAP.put(enumConstant, enumConstant.code());
        }
    }

}
