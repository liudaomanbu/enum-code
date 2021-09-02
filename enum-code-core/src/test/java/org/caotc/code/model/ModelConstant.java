package org.caotc.code.model;

import com.google.common.collect.ImmutableSet;
import lombok.experimental.UtilityClass;

/**
 * @author caotc
 * @date 2021-09-01
 */
@UtilityClass
public class ModelConstant {
    public static final ImmutableSet<Class<?>> CLASSES = ImmutableSet.of(CodeAnnotatedFieldEnum.class, CodeAnnotatedFieldEnumerableAnnotatedObject.class
            , CodeAnnotatedFieldObject.class, CodeAnnotatedMethodEnum.class,CodeAnnotatedMethodEnumerableAnnotatedObject.class,CodeAnnotatedMethodObject.class
            ,CodeFieldAndCodeMethodAndCodeAnnotatedFieldEnum.class,CodeFieldAndCodeMethodAndCodeAnnotatedFieldEnumerableAnnotatedObject.class
            ,CodeFieldAndCodeMethodAndCodeAnnotatedFieldObject.class,CodeFieldAndCodeMethodAndCodeAnnotatedMethodEnum.class
            ,CodeFieldAndCodeMethodAndCodeAnnotatedMethodEnumerableAnnotatedObject.class,CodeFieldAndCodeMethodAndCodeAnnotatedMethodObject.class,CodeFieldAndCodeMethodEnum.class
            ,CodeFieldAndCodeMethodEnumerableAnnotatedObject.class,CodeFieldAndCodeMethodObject.class,CodeFieldEnum.class,CodeFieldEnumerableAnnotatedObject.class,CodeFieldObject.class
            ,CodeGetMethodEnum.class,CodeGetMethodEnumerableAnnotatedObject.class,CodeGetMethodObject.class,CodeMethodEnum.class,CodeMethodEnumerableAnnotatedObject.class
            ,CodeMethodObject.class,MultipleCodeAnnotatedFieldEnum.class,MultipleCodeAnnotatedFieldEnumerableAnnotatedObject.class,NoCodeEnum.class,NoCodeEnumerableAnnotatedObject.class
            , EnumerableImpl.class);
}
