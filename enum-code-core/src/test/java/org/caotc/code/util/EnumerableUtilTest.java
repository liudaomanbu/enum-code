package org.caotc.code.util;

import lombok.extern.slf4j.Slf4j;
import org.caotc.code.SpringBootJunit5TestApplicationTests;
import org.caotc.code.factory.EnumerableAdapteeConstantFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Optional;

@Slf4j
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class EnumerableUtilTest extends SpringBootJunit5TestApplicationTests {
    @Resource
    Collection<EnumerableAdapteeConstantFactory<?>> enumerableAdapteeConstantFactories;

    @BeforeAll
    void init() {
        enumerableAdapteeConstantFactories.forEach(EnumerableUtil::addEnumerableAdapteeConstantFactory);
    }

    @ParameterizedTest
    @MethodSource({"org.caotc.code.provider.Provider#enumerableTypes", "org.caotc.code.provider.Provider#illegalEnumerableTypes"})
    void isEnumerable(Class<?> enumerableType) {
        Assertions.assertTrue(EnumerableUtil.isEnumerable(enumerableType));
    }

    @ParameterizedTest
    @MethodSource("org.caotc.code.provider.Provider#unEnumerableTypes")
    void isEnumerableUnEnumerable(Class<?> unEnumerableType) {
        Assertions.assertFalse(EnumerableUtil.isEnumerable(unEnumerableType));
    }

    @ParameterizedTest
    @MethodSource({"org.caotc.code.provider.Provider#enumerableTypes"})
    void checkEnumerable(Class<?> enumerableType) {
        EnumerableUtil.checkEnumerable(enumerableType);
    }

    @ParameterizedTest
    @MethodSource({"org.caotc.code.provider.Provider#unEnumerableTypes", "org.caotc.code.provider.Provider#illegalEnumerableTypes"})
    void checkEnumerableUnEnumerableAndIllegalEnumerable(Class<?> type) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> EnumerableUtil.checkEnumerable(type));
    }

    @ParameterizedTest
    @MethodSource("org.caotc.code.provider.Provider#enumerableAndCodes")
    void valueOf(Object enumerable, Object code) {
        Optional<?> optional = EnumerableUtil.valueOf(enumerable.getClass(), code);
        Assertions.assertTrue(optional.isPresent());
        Assertions.assertEquals(optional.get(), enumerable);
    }

    @ParameterizedTest
    @MethodSource("org.caotc.code.provider.Provider#enumerableTypeAndInvalidCode")
    void valueOfInvalidCode(Class<?> enumerableType, Object code) {
        Optional<?> optional = EnumerableUtil.valueOf(enumerableType, code);
        Assertions.assertFalse(optional.isPresent());
    }

    @ParameterizedTest
    @MethodSource({"org.caotc.code.provider.Provider#unEnumerableTypes", "org.caotc.code.provider.Provider#noConstantEnumerableTypes"})
    void valueOfUnEnumerable(Class<?> unEnumerableType) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> EnumerableUtil.valueOf(unEnumerableType, 0));
    }


    @ParameterizedTest
    @MethodSource({"org.caotc.code.provider.Provider#illegalEnumerableTypes"})
    void valueOfIllegalEnumerable(Class<?> illegalEnumerable) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> EnumerableUtil.valueOf(illegalEnumerable, 0));
    }

    @ParameterizedTest
    @MethodSource("org.caotc.code.provider.Provider#enumerableAndCodes")
    void valueOfExact(Object enumerable, Object code) {
        Assertions.assertEquals(EnumerableUtil.valueOfExact(enumerable.getClass(), code), enumerable);
    }

    @ParameterizedTest
    @MethodSource("org.caotc.code.provider.Provider#enumerableTypeAndInvalidCode")
    void valueOfExactInvalidCode(Class<?> enumerableType, Object code) {
        Assertions.assertThrows(IllegalStateException.class, () -> EnumerableUtil.valueOfExact(enumerableType, code));
    }

    @ParameterizedTest
    @MethodSource({"org.caotc.code.provider.Provider#unEnumerableTypes", "org.caotc.code.provider.Provider#noConstantEnumerableTypes"})
    void valueOfExactUnEnumerable(Class<?> unEnumerableType) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> EnumerableUtil.valueOf(unEnumerableType, 0));
    }

    @ParameterizedTest
    @MethodSource({"org.caotc.code.provider.Provider#illegalEnumerableTypes"})
    void valueOfExactIllegalEnumerable(Class<?> unEnumerableType) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> EnumerableUtil.valueOf(unEnumerableType, 0));
    }

    @ParameterizedTest
    @MethodSource("org.caotc.code.provider.Provider#enumerableAndCodes")
    void valueOfNullable(Object enumerable, Object code) {
        Optional<?> optional = EnumerableUtil.valueOfNullable(enumerable.getClass(), code);
        Assertions.assertTrue(optional.isPresent());
        Assertions.assertEquals(optional.get(), enumerable);
    }

    @ParameterizedTest
    @MethodSource("org.caotc.code.provider.Provider#enumerableTypeAndInvalidCode")
    void valueOfNullableInvalidCode(Class<?> enumerableType, Object code) {
        Optional<?> optional = EnumerableUtil.valueOfNullable(enumerableType, code);
        Assertions.assertFalse(optional.isPresent());
    }

    @ParameterizedTest
    @MethodSource({"org.caotc.code.provider.Provider#unEnumerableTypes", "org.caotc.code.provider.Provider#noConstantEnumerableTypes"})
    void valueOfNullableUnEnumerable(Class<?> unEnumerableType) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> EnumerableUtil.valueOfNullable(unEnumerableType, 0));
    }

    @ParameterizedTest
    @MethodSource({"org.caotc.code.provider.Provider#illegalEnumerableTypes"})
    void valueOfNullableIllegalEnumerable(Class<?> illegalEnumerableType) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> EnumerableUtil.valueOfNullable(illegalEnumerableType, 0));
    }

    @ParameterizedTest
    @NullSource
    @MethodSource("org.caotc.code.provider.Provider#enumerableTypes")
    void valueOfNullable(Class<?> enumerableType) {
        Optional<?> optional = EnumerableUtil.valueOfNullable(enumerableType, null);
        Assertions.assertFalse(optional.isPresent());
    }

    @ParameterizedTest
    @MethodSource("org.caotc.code.provider.Provider#enumerableAndCodes")
    void toCode(Object enumerable, Object code) {
        Assertions.assertEquals(EnumerableUtil.toCode(enumerable), code);
    }

    @ParameterizedTest
    @MethodSource("org.caotc.code.provider.Provider#unEnumerables")
    void toCodeUnEnumerable(Object unEnumerable) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> EnumerableUtil.toCode(unEnumerable));
    }

    @ParameterizedTest
    @MethodSource("org.caotc.code.provider.Provider#noConstantEnumerableAndCodes")
    void toCodeNoConstantEnumerable(Object enumerable, Object code) {
        Assertions.assertEquals(code, EnumerableUtil.toCode(enumerable));
    }

    @ParameterizedTest
    @MethodSource("org.caotc.code.provider.Provider#enumerableAndCodes")
    void toCodeNullable(Object enumerable, Object code) {
        Assertions.assertEquals(EnumerableUtil.toCodeNullable(enumerable), code);
    }

    @Test
    void toCodeNullable() {
        Assertions.assertNull(EnumerableUtil.toCodeNullable(null));
    }

    @ParameterizedTest
    @MethodSource("org.caotc.code.provider.Provider#noConstantEnumerableAndCodes")
    void toCodeNullableNoConstantEnumerable(Object enumerable, Object code) {
        Assertions.assertEquals(code, EnumerableUtil.toCodeNullable(enumerable));
    }

    @ParameterizedTest
    @MethodSource("org.caotc.code.provider.Provider#unEnumerables")
    void toCodeNullableUnEnumerable(Object unEnumerable) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> EnumerableUtil.toCodeNullable(unEnumerable));
    }
}