package org.caotc.code.service;

import lombok.extern.slf4j.Slf4j;
import org.caotc.code.SpringBootJunit5TestApplicationTests;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;

import javax.annotation.Resource;
import java.util.Optional;

@Slf4j
class DictionaryServiceTest extends SpringBootJunit5TestApplicationTests {

    @Resource
    DictionaryService dictionaryService;

    @ParameterizedTest
    @MethodSource("org.caotc.code.provider.Provider#enumerableAndCodes")
    void valueOf(Object enumerable, Object code) {
        Optional<?> optional = dictionaryService.valueOf(enumerable.getClass(), code);
        Assertions.assertTrue(optional.isPresent());
        Assertions.assertEquals(optional.get(), enumerable);
    }

    @ParameterizedTest
    @MethodSource("org.caotc.code.provider.Provider#enumerableTypeAndInvalidCode")
    void valueOfInvalidCode(Class<?> enumerableType,Object code) {
        Optional<?> optional = dictionaryService.valueOf(enumerableType, code);
        Assertions.assertFalse(optional.isPresent());
    }

    @ParameterizedTest
    @MethodSource({"org.caotc.code.provider.Provider#unEnumerableTypes","org.caotc.code.provider.Provider#noConstantEnumerableTypes"})
    void valueOfUnEnumerable(Class<?> unEnumerableType) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> dictionaryService.valueOf(unEnumerableType, 0));
    }


    @ParameterizedTest
    @MethodSource({"org.caotc.code.provider.Provider#illegalEnumerableTypes"})
    void valueOfIllegalEnumerable(Class<?> illegalEnumerable) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> dictionaryService.valueOf(illegalEnumerable, 0));
    }

    @ParameterizedTest
    @MethodSource("org.caotc.code.provider.Provider#enumerableAndCodes")
    void valueOfExact(Object enumerable,Object code) {
        Assertions.assertEquals(dictionaryService.valueOfExact(enumerable.getClass(), code), enumerable);
    }

    @ParameterizedTest
    @MethodSource("org.caotc.code.provider.Provider#enumerableTypeAndInvalidCode")
    void valueOfExactInvalidCode(Class<?> enumerableType,Object code) {
        Assertions.assertThrows(IllegalStateException.class, () -> dictionaryService.valueOfExact(enumerableType, code));
    }

    @ParameterizedTest
    @MethodSource({"org.caotc.code.provider.Provider#unEnumerableTypes","org.caotc.code.provider.Provider#noConstantEnumerableTypes"})
    void valueOfExactUnEnumerable(Class<?> unEnumerableType) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> dictionaryService.valueOf(unEnumerableType, 0));
    }

    @ParameterizedTest
    @MethodSource({"org.caotc.code.provider.Provider#illegalEnumerableTypes"})
    void valueOfExactIllegalEnumerable(Class<?> unEnumerableType) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> dictionaryService.valueOf(unEnumerableType, 0));
    }

    @ParameterizedTest
    @MethodSource("org.caotc.code.provider.Provider#enumerableAndCodes")
    void valueOfNullable(Object enumerable,Object code) {
        Optional<?> optional = dictionaryService.valueOfNullable(enumerable.getClass(), code);
        Assertions.assertTrue(optional.isPresent());
        Assertions.assertEquals(optional.get(), enumerable);
    }

    @ParameterizedTest
    @MethodSource("org.caotc.code.provider.Provider#enumerableTypeAndInvalidCode")
    void valueOfNullableInvalidCode(Class<?> enumerableType,Object code) {
        Optional<?> optional = dictionaryService.valueOfNullable(enumerableType, code);
        Assertions.assertFalse(optional.isPresent());
    }

    @ParameterizedTest
    @MethodSource({"org.caotc.code.provider.Provider#unEnumerableTypes","org.caotc.code.provider.Provider#noConstantEnumerableTypes"})
    void valueOfNullableUnEnumerable(Class<?> unEnumerableType) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> dictionaryService.valueOfNullable(unEnumerableType, 0));
    }

    @ParameterizedTest
    @MethodSource({"org.caotc.code.provider.Provider#illegalEnumerableTypes"})
    void valueOfNullableIllegalEnumerable(Class<?> illegalEnumerableType) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> dictionaryService.valueOfNullable(illegalEnumerableType, 0));
    }

    @ParameterizedTest
    @NullSource
    @MethodSource("org.caotc.code.provider.Provider#enumerableTypes")
    void valueOfNullable(Class<?> enumerableType) {
        Optional<?> optional = dictionaryService.valueOfNullable(enumerableType, null);
        Assertions.assertFalse(optional.isPresent());
    }

    @ParameterizedTest
    @MethodSource("org.caotc.code.provider.Provider#enumerableAndCodes")
    void toCode(Object enumerable,Object code) {
        Assertions.assertEquals(dictionaryService.toCode(enumerable), code);
    }

    @ParameterizedTest
    @MethodSource("org.caotc.code.provider.Provider#unEnumerables")
    void toCodeUnEnumerable(Object unEnumerable) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> dictionaryService.toCode(unEnumerable));
    }

    @ParameterizedTest
    @MethodSource("org.caotc.code.provider.Provider#noConstantEnumerableAndCodes")
    void toCodeNoConstantEnumerable(Object enumerable,Object code) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> dictionaryService.toCode(enumerable));
    }

    @ParameterizedTest
    @MethodSource("org.caotc.code.provider.Provider#enumerableAndCodes")
    void toCodeNullable(Object enumerable,Object code) {
        Assertions.assertEquals(dictionaryService.toCodeNullable(enumerable), code);
    }

    @Test
    void toCodeNullable() {
        Assertions.assertNull(dictionaryService.toCodeNullable(null));
    }

    @ParameterizedTest
    @MethodSource("org.caotc.code.provider.Provider#noConstantEnumerableAndCodes")
    void toCodeNullableNoConstantEnumerable(Object enumerable,Object code) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> dictionaryService.toCodeNullable(enumerable));
    }

    @ParameterizedTest
    @MethodSource("org.caotc.code.provider.Provider#unEnumerables")
    void toCodeNullableUnEnumerable(Object unEnumerable) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> dictionaryService.toCodeNullable(unEnumerable));
    }
}