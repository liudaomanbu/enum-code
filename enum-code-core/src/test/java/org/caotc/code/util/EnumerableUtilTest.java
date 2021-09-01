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
    void isEnumerableCodeFieldAnnotatedEnum() {
        boolean enumerable = EnumerableUtil.isEnumerable(CodeFieldAnnotatedEnum.class);
        Assertions.assertTrue(enumerable);
    }

    @Test
    void isEnumerableCodeMethodAnnotatedEnum() {
        boolean enumerable = EnumerableUtil.isEnumerable(CodeMethodAnnotatedEnum.class);
        Assertions.assertTrue(enumerable);
    }

    @Test
    void isEnumerableCodeFieldEnumerableAnnotatedObject() {
        boolean enumerable = EnumerableUtil.isEnumerable(CodeFieldEnumerableAnnotatedObject.class);
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