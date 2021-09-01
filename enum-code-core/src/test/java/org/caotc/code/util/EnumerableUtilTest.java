package org.caotc.code.util;

import org.caotc.code.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EnumerableUtilTest {

    @Test
    void isEnumerableCodeFieldEnum() {
        boolean enumerable = EnumerableUtil.isEnumerable(CodeFieldEnum.class);
        Assertions.assertTrue(enumerable);
    }

    @Test
    void isEnumerableCodeGetMethodEnum() {
        boolean enumerable = EnumerableUtil.isEnumerable(CodeGetMethodEnum.class);
        Assertions.assertTrue(enumerable);
    }

    @Test
    void isEnumerableCodeMethodEnum() {
        boolean enumerable = EnumerableUtil.isEnumerable(CodeMethodEnum.class);
        Assertions.assertTrue(enumerable);
    }

    @Test
    void isEnumerableCodeAnnotatedFieldEnum() {
        boolean enumerable = EnumerableUtil.isEnumerable(CodeAnnotatedFieldEnum.class);
        Assertions.assertTrue(enumerable);
    }

    @Test
    void isEnumerableCodeMethodAnnotatedEnum() {
        boolean enumerable = EnumerableUtil.isEnumerable(CodeAnnotatedMethodEnum.class);
        Assertions.assertTrue(enumerable);
    }

    @Test
    void isEnumerableCodeFieldEnumerableAnnotatedObject() {
        boolean enumerable = EnumerableUtil.isEnumerable(CodeFieldEnumerableAnnotatedObject.class);
        Assertions.assertTrue(enumerable);
    }

    @Test
    void isEnumerableCodeFieldAndCodeMethodAndCodeAnnotatedMethodEnum() {
        boolean enumerable = EnumerableUtil.isEnumerable(CodeFieldAndCodeMethodAndCodeAnnotatedMethodEnum.class);
        Assertions.assertTrue(enumerable);
    }

    @Test
    void isEnumerableCodeFieldAndCodeMethodAndCodeAnnotatedFieldEnum() {
        boolean enumerable = EnumerableUtil.isEnumerable(CodeFieldAndCodeMethodAndCodeAnnotatedFieldEnum.class);
        Assertions.assertTrue(enumerable);
    }

    @Test
    void isEnumerableCodeFieldAndCodeMethodEnum() {
        boolean enumerable = EnumerableUtil.isEnumerable(CodeFieldAndCodeMethodEnum.class);
        Assertions.assertTrue(enumerable);
    }

    @Test
    void isEnumerableCodeFieldAndCodeMethodAndCodeAnnotatedFieldEnumerableAnnotatedObject() {
        boolean enumerable = EnumerableUtil.isEnumerable(CodeFieldAndCodeMethodAndCodeAnnotatedFieldEnumerableAnnotatedObject.class);
        Assertions.assertTrue(enumerable);
    }

    @Test
    void isEnumerableCodeFieldAndCodeMethodAndCodeAnnotatedMethodEnumerableAnnotatedObject() {
        boolean enumerable = EnumerableUtil.isEnumerable(CodeFieldAndCodeMethodAndCodeAnnotatedMethodEnumerableAnnotatedObject.class);
        Assertions.assertTrue(enumerable);
    }

    @Test
    void isEnumerableCodeFieldAndCodeMethodEnumerableAnnotatedObject() {
        boolean enumerable = EnumerableUtil.isEnumerable(CodeFieldAndCodeMethodEnumerableAnnotatedObject.class);
        Assertions.assertTrue(enumerable);
    }

    @Test
    void isEnumerableCodeFieldAnnotatedEnumerableAnnotatedObject() {
        boolean enumerable = EnumerableUtil.isEnumerable(CodeAnnotatedFieldEnumerableAnnotatedObject.class);
        Assertions.assertTrue(enumerable);
    }

    @Test
    void isEnumerableCodeGetMethodEnumerableAnnotatedObject() {
        boolean enumerable = EnumerableUtil.isEnumerable(CodeGetMethodEnumerableAnnotatedObject.class);
        Assertions.assertTrue(enumerable);
    }

    @Test
    void isEnumerableCodeMethodAnnotatedEnumerableAnnotatedObject() {
        boolean enumerable = EnumerableUtil.isEnumerable(CodeAnnotatedMethodEnumerableAnnotatedObject.class);
        Assertions.assertTrue(enumerable);
    }

    @Test
    void isEnumerableCodeMethodEnumerableAnnotatedObject() {
        boolean enumerable = EnumerableUtil.isEnumerable(CodeMethodEnumerableAnnotatedObject.class);
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
    void isEnumerableCodeFieldObject() {
        boolean enumerable = EnumerableUtil.isEnumerable(CodeFieldObject.class);
        Assertions.assertFalse(enumerable);
    }

    @Test
    void isEnumerableObject() {
        boolean enumerable = EnumerableUtil.isEnumerable(Object.class);
        Assertions.assertFalse(enumerable);
    }

    @Test
    void checkEnumerable() {
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