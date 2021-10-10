package org.caotc.code.service;

import com.google.common.collect.Lists;
import org.caotc.code.factory.*;
import org.caotc.code.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

class EnumerableServiceTest {
    EnumerableAdapteeConstantFactoryService enumerableAdapteeConstantFactoryService = new EnumerableAdapteeConstantFactoryService(Lists.newArrayList(new EnumConstantFactory(),new EnumerableImplFactory()));
    EnumerableAdapterFactoryService enumerableAdapterFactoryService = new EnumerableAdapterFactoryService(Lists.newArrayList(new CodeReaderEnumerableAdapterFactory()));
    EnumerableConstantFactory<Object> enumerableConstantsFactory = new EnumerableAdapteeConstantsFactoryToEnumerableConstantFactoryAdapter(enumerableAdapteeConstantFactoryService, enumerableAdapterFactoryService);
    EnumerableConstantFactoryService enumerableConstantsFactoryService = new EnumerableConstantFactoryService(Lists.newArrayList(enumerableConstantsFactory));
    EnumerableService enumerableService = new EnumerableService(enumerableAdapterFactoryService, enumerableConstantsFactoryService);
    
    @Test
    void valueOfEnumerableEnum() {
        Optional<CodeFieldEnum> optional = enumerableService.valueOf(CodeFieldEnum.class, CodeFieldEnum.A.code);
        Assertions.assertTrue(optional.isPresent());
        Assertions.assertEquals(optional.get(),CodeFieldEnum.A);
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
        Assertions.assertEquals(optional.get(),EnumerableImpl.INSTANCE);
    }

    @Test
    void valueOfEnumerableInvalidCode() {
        Optional<EnumerableImpl> optional = enumerableService.valueOf(EnumerableImpl.class, -1);
        Assertions.assertFalse(optional.isPresent());
    }

    @Test
    void valueOfUnEnumerableObject() {
        Assertions.assertThrows(IllegalArgumentException.class,()->enumerableService.valueOf(CodeFieldObject.class, 0));
    }

    @Test
    void valueOfNoConstantEnumerable() {
        Assertions.assertThrows(IllegalArgumentException.class,()->enumerableService.valueOf(CodeFieldEnumerableAnnotatedObject.class, 0));
    }

    @Test
    void valueOfUnEnumerableEnum() {
        Assertions.assertThrows(IllegalArgumentException.class,()->enumerableService.valueOf(NoCodeEnum.class, 0));
    }

    @Test
    void valueOfExactEnumerableEnum() {
        CodeFieldEnum enumerable = enumerableService.valueOfExact(CodeFieldEnum.class, CodeFieldEnum.A.code);
        Assertions.assertEquals(enumerable,CodeFieldEnum.A);
    }

    @Test
    void valueOfExactEnumerableEnumInvalidCode() {
        Assertions.assertThrows(IllegalStateException.class,()->enumerableService.valueOfExact(CodeFieldEnum.class, -1));
    }

    @Test
    void valueOfExactEnumerable() {
        EnumerableImpl enumerable = enumerableService.valueOfExact(EnumerableImpl.class, EnumerableImpl.INSTANCE.code());
        Assertions.assertEquals(enumerable,EnumerableImpl.INSTANCE);
    }

    @Test
    void valueOfExactEnumerableInvalidCode() {
        Assertions.assertThrows(IllegalStateException.class,()->enumerableService.valueOfExact(EnumerableImpl.class, -1));
    }

    @Test
    void valueOfExactUnEnumerableObject() {
        Assertions.assertThrows(IllegalArgumentException.class,()->enumerableService.valueOfExact(CodeFieldObject.class, 0));
    }

    @Test
    void valueOfExactNoConstantEnumerable() {
        Assertions.assertThrows(IllegalArgumentException.class,()->enumerableService.valueOfExact(CodeFieldEnumerableAnnotatedObject.class, 0));
    }

    @Test
    void valueOfExactUnEnumerableEnum() {
        Assertions.assertThrows(IllegalArgumentException.class,()->enumerableService.valueOfExact(NoCodeEnum.class, 0));
    }

    @Test
    void valueOfNullable() {
        Optional<CodeFieldEnum> optional = enumerableService.valueOfNullable(CodeFieldEnum.class, null);
        Assertions.assertFalse(optional.isPresent());
    }

    @Test
    void toCode() {
    }

    @Test
    void toCodeNullable() {
    }
}