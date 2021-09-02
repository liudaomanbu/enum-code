package org.caotc.code.util;

import org.caotc.code.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EnumerableUtilTest {
    @Test
    void isEnumerableCodeAnnotatedFieldEnum() {
        boolean enumerable = EnumerableUtil.isEnumerable(CodeAnnotatedFieldEnum.class);
        Assertions.assertTrue(enumerable);
    }

    @Test
    void isEnumerableCodeAnnotatedFieldEnumerableAnnotatedObject() {
        boolean enumerable = EnumerableUtil.isEnumerable(CodeAnnotatedFieldEnumerableAnnotatedObject.class);
        Assertions.assertTrue(enumerable);
    }

    @Test
    void isEnumerableCodeAnnotatedFieldObject() {
        boolean enumerable = EnumerableUtil.isEnumerable(CodeAnnotatedFieldObject.class);
        Assertions.assertFalse(enumerable);
    }

    @Test
    void isEnumerableCodeAnnotatedMethodEnum() {
        boolean enumerable = EnumerableUtil.isEnumerable(CodeAnnotatedMethodEnum.class);
        Assertions.assertTrue(enumerable);
    }

    @Test
    void isEnumerableCodeAnnotatedMethodEnumerableAnnotatedObject() {
        boolean enumerable = EnumerableUtil.isEnumerable(CodeAnnotatedMethodEnumerableAnnotatedObject.class);
        Assertions.assertTrue(enumerable);
    }

    @Test
    void isEnumerableCodeAnnotatedMethodObject() {
        boolean enumerable = EnumerableUtil.isEnumerable(CodeAnnotatedMethodObject.class);
        Assertions.assertFalse(enumerable);
    }

    @Test
    void isEnumerableCodeFieldAndCodeMethodAndCodeAnnotatedFieldEnum() {
        boolean enumerable = EnumerableUtil.isEnumerable(CodeFieldAndCodeMethodAndCodeAnnotatedFieldEnum.class);
        Assertions.assertTrue(enumerable);
    }

    @Test
    void isEnumerableCodeFieldAndCodeMethodAndCodeAnnotatedFieldEnumerableAnnotatedObject() {
        boolean enumerable = EnumerableUtil.isEnumerable(CodeFieldAndCodeMethodAndCodeAnnotatedFieldEnumerableAnnotatedObject.class);
        Assertions.assertTrue(enumerable);
    }

    @Test
    void isEnumerableCodeFieldAndCodeMethodAndCodeAnnotatedFieldObject() {
        boolean enumerable = EnumerableUtil.isEnumerable(CodeFieldAndCodeMethodAndCodeAnnotatedFieldObject.class);
        Assertions.assertFalse(enumerable);
    }

    @Test
    void isEnumerableCodeFieldAndCodeMethodAndCodeAnnotatedMethodEnum() {
        boolean enumerable = EnumerableUtil.isEnumerable(CodeFieldAndCodeMethodAndCodeAnnotatedMethodEnum.class);
        Assertions.assertTrue(enumerable);
    }

    @Test
    void isEnumerableCodeFieldAndCodeMethodAndCodeAnnotatedMethodEnumerableAnnotatedObject() {
        boolean enumerable = EnumerableUtil.isEnumerable(CodeFieldAndCodeMethodAndCodeAnnotatedMethodEnumerableAnnotatedObject.class);
        Assertions.assertTrue(enumerable);
    }

    @Test
    void isEnumerableCodeFieldAndCodeMethodAndCodeAnnotatedMethodObject() {
        boolean enumerable = EnumerableUtil.isEnumerable(CodeFieldAndCodeMethodAndCodeAnnotatedMethodObject.class);
        Assertions.assertFalse(enumerable);
    }

    @Test
    void isEnumerableCodeFieldAndCodeMethodEnum() {
        boolean enumerable = EnumerableUtil.isEnumerable(CodeFieldAndCodeMethodEnum.class);
        Assertions.assertTrue(enumerable);
    }

    @Test
    void isEnumerableCodeFieldAndCodeMethodEnumerableAnnotatedObject() {
        boolean enumerable = EnumerableUtil.isEnumerable(CodeFieldAndCodeMethodEnumerableAnnotatedObject.class);
        Assertions.assertTrue(enumerable);
    }

    @Test
    void isEnumerableCodeFieldAndCodeMethodObject() {
        boolean enumerable = EnumerableUtil.isEnumerable(CodeFieldAndCodeMethodObject.class);
        Assertions.assertFalse(enumerable);
    }

    @Test
    void isEnumerableCodeFieldEnum() {
        boolean enumerable = EnumerableUtil.isEnumerable(CodeFieldEnum.class);
        Assertions.assertTrue(enumerable);
    }

    @Test
    void isEnumerableCodeFieldEnumerableAnnotatedObject() {
        boolean enumerable = EnumerableUtil.isEnumerable(CodeFieldEnumerableAnnotatedObject.class);
        Assertions.assertTrue(enumerable);
    }

    @Test
    void isEnumerableCodeFieldObject() {
        boolean enumerable = EnumerableUtil.isEnumerable(CodeFieldObject.class);
        Assertions.assertFalse(enumerable);
    }

    @Test
    void isEnumerableCodeGetMethodEnum() {
        boolean enumerable = EnumerableUtil.isEnumerable(CodeGetMethodEnum.class);
        Assertions.assertTrue(enumerable);
    }

    @Test
    void isEnumerableCodeGetMethodEnumerableAnnotatedObject() {
        boolean enumerable = EnumerableUtil.isEnumerable(CodeGetMethodEnumerableAnnotatedObject.class);
        Assertions.assertTrue(enumerable);
    }

    @Test
    void isEnumerableCodeGetMethodObject() {
        boolean enumerable = EnumerableUtil.isEnumerable(CodeGetMethodObject.class);
        Assertions.assertFalse(enumerable);
    }

    @Test
    void isEnumerableCodeMethodEnum() {
        boolean enumerable = EnumerableUtil.isEnumerable(CodeMethodEnum.class);
        Assertions.assertTrue(enumerable);
    }

    @Test
    void isEnumerableCodeMethodEnumerableAnnotatedObject() {
        boolean enumerable = EnumerableUtil.isEnumerable(CodeMethodEnumerableAnnotatedObject.class);
        Assertions.assertTrue(enumerable);
    }

    @Test
    void isEnumerableCodeMethodObject() {
        boolean enumerable = EnumerableUtil.isEnumerable(CodeMethodObject.class);
        Assertions.assertFalse(enumerable);
    }

    @Test
    void isEnumerableMultipleCodeAnnotatedFieldEnum() {
        boolean enumerable = EnumerableUtil.isEnumerable(MultipleCodeAnnotatedFieldEnum.class);
        Assertions.assertTrue(enumerable);
    }

    @Test
    void isEnumerableMultipleCodeAnnotatedFieldEnumerableAnnotatedObject() {
        boolean enumerable = EnumerableUtil.isEnumerable(MultipleCodeAnnotatedFieldEnumerableAnnotatedObject.class);
        Assertions.assertTrue(enumerable);
    }

    @Test
    void isEnumerableNoCodeEnum() {
        boolean enumerable = EnumerableUtil.isEnumerable(NoCodeEnum.class);
        Assertions.assertFalse(enumerable);
    }

    @Test
    void isEnumerableNoCodeEnumerableAnnotatedObject() {
        boolean enumerable = EnumerableUtil.isEnumerable(NoCodeEnumerableAnnotatedObject.class);
        Assertions.assertFalse(enumerable);
    }

    @Test
    void isEnumerableTestEnumerable() {
        boolean enumerable = EnumerableUtil.isEnumerable(EnumerableImpl.class);
        Assertions.assertTrue(enumerable);
    }

    @Test
    void checkEnumerable() {
        ModelConstant.CLASSES
                .forEach(type-> {
                        if(EnumerableUtil.isEnumerable(type) ){
                            EnumerableUtil.checkEnumerable(type);
                        }else{
                            Assertions.assertThrows(IllegalArgumentException.class,()->EnumerableUtil.checkEnumerable(type));
                        }
                });
    }

    @Test
    void findCodeReaderExact() {
    }

    @Test
    void testFindCodeReaderExact() {
    }

    @Test
    void findCodeReader() {
    }

    @Test
    void valueOf() {
    }

    @Test
    void toSimpleValue() {
    }
}