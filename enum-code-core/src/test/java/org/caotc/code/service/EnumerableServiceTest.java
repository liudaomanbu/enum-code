package org.caotc.code.service;

import lombok.extern.slf4j.Slf4j;
import org.caotc.code.SpringBootJunit5TestApplicationTests;
import org.caotc.code.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import javax.annotation.Resource;
import java.util.Optional;

@Slf4j
class EnumerableServiceTest extends SpringBootJunit5TestApplicationTests {
    @Resource
    EnumerableService enumerableService;

    @ParameterizedTest
    @ValueSource
    void valueOf() {
        Optional<CodeFieldEnum> optional = enumerableService.valueOf(CodeFieldEnum.class, CodeFieldEnum.A.code);
        Assertions.assertTrue(optional.isPresent());
        Assertions.assertEquals(optional.get(), CodeFieldEnum.A);
    }

    @Test
    void valueOfEnumerableEnum() {
        Optional<CodeFieldEnum> optional = enumerableService.valueOf(CodeFieldEnum.class, CodeFieldEnum.A.code);
        Assertions.assertTrue(optional.isPresent());
        Assertions.assertEquals(optional.get(), CodeFieldEnum.A);
    }

    @Test
    void valueOfEnumerableEnumInvalidCode() {
        Optional<CodeFieldEnum> optional = enumerableService.valueOf(CodeFieldEnum.class, -1);
        Assertions.assertFalse(optional.isPresent());
    }

    @Test
    void valueOfEnumerable() {
        Optional<EnumerableImpl> optional = enumerableService.valueOf(EnumerableImpl.class, EnumerableImpl.INSTANCE.code());
        Assertions.assertTrue(optional.isPresent());
        Assertions.assertEquals(optional.get(), EnumerableImpl.INSTANCE);
    }

    @Test
    void valueOfEnumerableInvalidCode() {
        Optional<EnumerableImpl> optional = enumerableService.valueOf(EnumerableImpl.class, -1);
        Assertions.assertFalse(optional.isPresent());
    }

    @Test
    void valueOfUnEnumerableObject() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> enumerableService.valueOf(CodeFieldObject.class, 0));
    }

    @Test
    void valueOfNoConstantEnumerable() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> enumerableService.valueOf(CodeFieldEnumerableAnnotatedObject.class, 0));
    }

    @Test
    void valueOfUnEnumerableEnum() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> enumerableService.valueOf(NoCodeEnum.class, 0));
    }

    @Test
    void valueOfExactEnumerableEnum() {
        CodeFieldEnum enumerable = enumerableService.valueOfExact(CodeFieldEnum.class, CodeFieldEnum.A.code);
        Assertions.assertEquals(enumerable, CodeFieldEnum.A);
    }

    @Test
    void valueOfExactEnumerableEnumInvalidCode() {
        Assertions.assertThrows(IllegalStateException.class, () -> enumerableService.valueOfExact(CodeFieldEnum.class, -1));
    }

    @Test
    void valueOfExactEnumerable() {
        EnumerableImpl enumerable = enumerableService.valueOfExact(EnumerableImpl.class, EnumerableImpl.INSTANCE.code());
        Assertions.assertEquals(enumerable, EnumerableImpl.INSTANCE);
    }

    @Test
    void valueOfExactEnumerableInvalidCode() {
        Assertions.assertThrows(IllegalStateException.class, () -> enumerableService.valueOfExact(EnumerableImpl.class, -1));
    }

    @Test
    void valueOfExactUnEnumerableObject() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> enumerableService.valueOfExact(CodeFieldObject.class, 0));
    }

    @Test
    void valueOfExactNoConstantEnumerable() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> enumerableService.valueOfExact(CodeFieldEnumerableAnnotatedObject.class, 0));
    }

    @Test
    void valueOfExactUnEnumerableEnum() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> enumerableService.valueOfExact(NoCodeEnum.class, 0));
    }

    @Test
    void valueOfNullable() {
        Optional<CodeFieldEnum> optional = enumerableService.valueOfNullable(CodeFieldEnum.class, null);
        Assertions.assertFalse(optional.isPresent());
    }

    @Test
    void toCodeEnumerableEnum() {
        Object code = enumerableService.toCode(CodeFieldEnum.A);
        Assertions.assertEquals(code, CodeFieldEnum.A.code);
    }

    @Test
    void toCodeEnumerable() {
        Object code = enumerableService.toCode(EnumerableImpl.INSTANCE);
        Assertions.assertEquals(code, EnumerableImpl.INSTANCE.code());
    }

    @Test
    void toCodeUnEnumerableObject() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> enumerableService.toCode(new CodeFieldObject(0)));
    }

    @Test
    void toCodeNoConstantEnumerable() {
        CodeFieldEnumerableAnnotatedObject enumerable = new CodeFieldEnumerableAnnotatedObject(0);
        Object code = enumerableService.toCode(enumerable);
        Assertions.assertEquals(code, enumerable.code);
    }

    @Test
    void toCodeUnEnumerableEnum() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> enumerableService.toCode(NoCodeEnum.A));
    }

    @Test
    void toCodeNullable() {
        Object code = enumerableService.toCodeNullable(null);
        Assertions.assertNull(code);
    }
}