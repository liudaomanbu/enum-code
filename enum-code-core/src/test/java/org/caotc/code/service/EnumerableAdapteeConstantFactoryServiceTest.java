package org.caotc.code.service;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import lombok.extern.slf4j.Slf4j;
import org.caotc.code.factory.EnumConstantFactory;
import org.caotc.code.factory.EnumerableImplFactory;
import org.caotc.code.model.*;
import org.caotc.code.service.impl.DefaultEnumerableAdapteeConstantFactoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@Slf4j
class EnumerableAdapteeConstantFactoryServiceTest {
    EnumerableAdapteeConstantFactoryService enumerableAdapteeConstantFactoryService
            = new DefaultEnumerableAdapteeConstantFactoryService(ImmutableList.of(new EnumConstantFactory(), new EnumerableImplFactory()));

    @Test
    void supportCodeAnnotatedFieldEnum() {
        Assertions.assertTrue(enumerableAdapteeConstantFactoryService.support(CodeAnnotatedFieldEnum.class));
    }

    @Test
    void supportCodeAnnotatedFieldEnumerableAnnotatedObject() {
        Assertions.assertFalse(enumerableAdapteeConstantFactoryService.support(CodeAnnotatedFieldEnumerableAnnotatedObject.class));
    }

    @Test
    void supportCodeAnnotatedFieldObject() {
        Assertions.assertFalse(enumerableAdapteeConstantFactoryService.support(CodeAnnotatedFieldObject.class));
    }

    @Test
    void supportCodeAnnotatedMethodEnum() {
        Assertions.assertTrue(enumerableAdapteeConstantFactoryService.support(CodeAnnotatedMethodEnum.class));
    }

    @Test
    void supportCodeAnnotatedMethodEnumerableAnnotatedObject() {
        Assertions.assertFalse(enumerableAdapteeConstantFactoryService.support(CodeAnnotatedMethodEnumerableAnnotatedObject.class));
    }

    @Test
    void supportCodeAnnotatedMethodObject() {
        Assertions.assertFalse(enumerableAdapteeConstantFactoryService.support(CodeAnnotatedMethodObject.class));
    }

    @Test
    void supportCodeFieldAndCodeMethodAndCodeAnnotatedFieldEnum() {
        Assertions.assertTrue(enumerableAdapteeConstantFactoryService.support(CodeFieldAndCodeMethodAndCodeAnnotatedFieldEnum.class));
    }

    @Test
    void supportCodeFieldAndCodeMethodAndCodeAnnotatedFieldEnumerableAnnotatedObject() {
        Assertions.assertFalse(enumerableAdapteeConstantFactoryService.support(CodeFieldAndCodeMethodAndCodeAnnotatedFieldEnumerableAnnotatedObject.class));
    }

    @Test
    void supportCodeFieldAndCodeMethodAndCodeAnnotatedFieldObject() {
        Assertions.assertFalse(enumerableAdapteeConstantFactoryService.support(CodeFieldAndCodeMethodAndCodeAnnotatedFieldObject.class));
    }

    @Test
    void supportCodeFieldAndCodeMethodAndCodeAnnotatedMethodEnum() {
        Assertions.assertTrue(enumerableAdapteeConstantFactoryService.support(CodeFieldAndCodeMethodAndCodeAnnotatedMethodEnum.class));
    }

    @Test
    void supportCodeFieldAndCodeMethodAndCodeAnnotatedMethodEnumerableAnnotatedObject() {
        Assertions.assertFalse(enumerableAdapteeConstantFactoryService.support(CodeFieldAndCodeMethodAndCodeAnnotatedMethodEnumerableAnnotatedObject.class));
    }

    @Test
    void supportCodeFieldAndCodeMethodAndCodeAnnotatedMethodObject() {
        Assertions.assertFalse(enumerableAdapteeConstantFactoryService.support(CodeFieldAndCodeMethodAndCodeAnnotatedMethodObject.class));
    }

    @Test
    void supportCodeFieldAndCodeMethodEnum() {
        Assertions.assertTrue(enumerableAdapteeConstantFactoryService.support(CodeFieldAndCodeMethodEnum.class));
    }

    @Test
    void supportCodeFieldAndCodeMethodEnumerableAnnotatedObject() {
        Assertions.assertFalse(enumerableAdapteeConstantFactoryService.support(CodeFieldAndCodeMethodEnumerableAnnotatedObject.class));
    }

    @Test
    void supportCodeFieldAndCodeMethodObject() {
        Assertions.assertFalse(enumerableAdapteeConstantFactoryService.support(CodeFieldAndCodeMethodObject.class));
    }

    @Test
    void supportCodeFieldEnum() {
        Assertions.assertTrue(enumerableAdapteeConstantFactoryService.support(CodeFieldEnum.class));
    }

    @Test
    void supportCodeFieldEnumerableAnnotatedObject() {
        Assertions.assertFalse(enumerableAdapteeConstantFactoryService.support(CodeFieldEnumerableAnnotatedObject.class));
    }

    @Test
    void supportCodeFieldObject() {
        Assertions.assertFalse(enumerableAdapteeConstantFactoryService.support(CodeFieldObject.class));
    }

    @Test
    void supportCodeGetMethodEnum() {
        Assertions.assertTrue(enumerableAdapteeConstantFactoryService.support(CodeGetMethodEnum.class));
    }

    @Test
    void supportCodeGetMethodEnumerableAnnotatedObject() {
        Assertions.assertFalse(enumerableAdapteeConstantFactoryService.support(CodeGetMethodEnumerableAnnotatedObject.class));
    }

    @Test
    void supportCodeGetMethodObject() {
        Assertions.assertFalse(enumerableAdapteeConstantFactoryService.support(CodeGetMethodObject.class));
    }

    @Test
    void supportCodeMethodEnum() {
        Assertions.assertTrue(enumerableAdapteeConstantFactoryService.support(CodeMethodEnum.class));
    }

    @Test
    void supportCodeMethodEnumerableAnnotatedObject() {
        Assertions.assertFalse(enumerableAdapteeConstantFactoryService.support(CodeMethodEnumerableAnnotatedObject.class));
    }

    @Test
    void supportCodeMethodObject() {
        Assertions.assertFalse(enumerableAdapteeConstantFactoryService.support(CodeMethodObject.class));
    }

    @Test
    void supportEnumerableImpl() {
        Assertions.assertTrue(enumerableAdapteeConstantFactoryService.support(EnumerableImpl.class));
    }

    @Test
    void supportMultipleCodeAnnotatedFieldEnum() {
        Assertions.assertTrue(enumerableAdapteeConstantFactoryService.support(MultipleCodeAnnotatedFieldEnum.class));
    }

    @Test
    void supportMultipleCodeAnnotatedFieldEnumerableAnnotatedObject() {
        Assertions.assertFalse(enumerableAdapteeConstantFactoryService.support(MultipleCodeAnnotatedFieldEnumerableAnnotatedObject.class));
    }

    @Test
    void supportNoCodeEnum() {
        Assertions.assertTrue(enumerableAdapteeConstantFactoryService.support(NoCodeEnum.class));
    }

    @Test
    void supportNoCodeEnumerableAnnotatedObject() {
        Assertions.assertFalse(enumerableAdapteeConstantFactoryService.support(NoCodeEnumerableAnnotatedObject.class));
    }

    @Test
    void createCodeAnnotatedFieldEnum() {
        ImmutableSet<CodeAnnotatedFieldEnum> constants = enumerableAdapteeConstantFactoryService.create(CodeAnnotatedFieldEnum.class);
        Assertions.assertIterableEquals(constants,ImmutableSet.copyOf(CodeAnnotatedFieldEnum.values()));
    }

    @Test
    void createCodeAnnotatedFieldEnumerableAnnotatedObject() {
        Assertions.assertThrows(IllegalArgumentException.class,()->enumerableAdapteeConstantFactoryService.create(CodeAnnotatedFieldEnumerableAnnotatedObject.class));
    }

    @Test
    void createCodeAnnotatedFieldObject() {
        Assertions.assertThrows(IllegalArgumentException.class,()->enumerableAdapteeConstantFactoryService.create(CodeAnnotatedFieldObject.class));
    }

    @Test
    void createCodeAnnotatedMethodEnum() {
        ImmutableSet<CodeAnnotatedMethodEnum> constants = enumerableAdapteeConstantFactoryService.create(CodeAnnotatedMethodEnum.class);
        Assertions.assertIterableEquals(constants,ImmutableSet.copyOf(CodeAnnotatedMethodEnum.values()));
    }

    @Test
    void createCodeAnnotatedMethodEnumerableAnnotatedObject() {
        Assertions.assertThrows(IllegalArgumentException.class,()->enumerableAdapteeConstantFactoryService.create(CodeAnnotatedMethodEnumerableAnnotatedObject.class));
    }

    @Test
    void createCodeAnnotatedMethodObject() {
        Assertions.assertThrows(IllegalArgumentException.class,()->enumerableAdapteeConstantFactoryService.create(CodeAnnotatedMethodObject.class));
    }

    @Test
    void createCodeFieldAndCodeMethodAndCodeAnnotatedFieldEnum() {
        ImmutableSet<CodeFieldAndCodeMethodAndCodeAnnotatedFieldEnum> constants = enumerableAdapteeConstantFactoryService.create(CodeFieldAndCodeMethodAndCodeAnnotatedFieldEnum.class);
        Assertions.assertIterableEquals(constants,ImmutableSet.copyOf(CodeFieldAndCodeMethodAndCodeAnnotatedFieldEnum.values()));
    }

    @Test
    void createCodeFieldAndCodeMethodAndCodeAnnotatedFieldEnumerableAnnotatedObject() {
        Assertions.assertThrows(IllegalArgumentException.class,()->enumerableAdapteeConstantFactoryService.create(CodeFieldAndCodeMethodAndCodeAnnotatedFieldEnumerableAnnotatedObject.class));
    }

    @Test
    void createCodeFieldAndCodeMethodAndCodeAnnotatedFieldObject() {
        Assertions.assertThrows(IllegalArgumentException.class,()->enumerableAdapteeConstantFactoryService.create(CodeFieldAndCodeMethodAndCodeAnnotatedFieldObject.class));
    }

    @Test
    void createCodeFieldAndCodeMethodAndCodeAnnotatedMethodEnum() {
        ImmutableSet<CodeFieldAndCodeMethodAndCodeAnnotatedMethodEnum> constants = enumerableAdapteeConstantFactoryService.create(CodeFieldAndCodeMethodAndCodeAnnotatedMethodEnum.class);
        Assertions.assertIterableEquals(constants,ImmutableSet.copyOf(CodeFieldAndCodeMethodAndCodeAnnotatedMethodEnum.values()));
    }

    @Test
    void createCodeFieldAndCodeMethodAndCodeAnnotatedMethodEnumerableAnnotatedObject() {
        Assertions.assertThrows(IllegalArgumentException.class,()->enumerableAdapteeConstantFactoryService.create(CodeFieldAndCodeMethodAndCodeAnnotatedMethodEnumerableAnnotatedObject.class));
    }

    @Test
    void createCodeFieldAndCodeMethodAndCodeAnnotatedMethodObject() {
        Assertions.assertThrows(IllegalArgumentException.class,()->enumerableAdapteeConstantFactoryService.create(CodeFieldAndCodeMethodAndCodeAnnotatedMethodObject.class));
    }

    @Test
    void createCodeFieldAndCodeMethodEnum() {
        ImmutableSet<CodeFieldAndCodeMethodEnum> constants = enumerableAdapteeConstantFactoryService.create(CodeFieldAndCodeMethodEnum.class);
        Assertions.assertIterableEquals(constants,ImmutableSet.copyOf(CodeFieldAndCodeMethodEnum.values()));
    }

    @Test
    void createCodeFieldAndCodeMethodEnumerableAnnotatedObject() {
        Assertions.assertThrows(IllegalArgumentException.class,()->enumerableAdapteeConstantFactoryService.create(CodeFieldAndCodeMethodEnumerableAnnotatedObject.class));
    }

    @Test
    void createCodeFieldAndCodeMethodObject() {
        Assertions.assertThrows(IllegalArgumentException.class,()->enumerableAdapteeConstantFactoryService.create(CodeFieldAndCodeMethodObject.class));
    }

    @Test
    void createCodeFieldEnum() {
        ImmutableSet<CodeFieldEnum> constants = enumerableAdapteeConstantFactoryService.create(CodeFieldEnum.class);
        Assertions.assertIterableEquals(constants,ImmutableSet.copyOf(CodeFieldEnum.values()));
    }

    @Test
    void createCodeFieldEnumerableAnnotatedObject() {
        Assertions.assertThrows(IllegalArgumentException.class,()->enumerableAdapteeConstantFactoryService.create(CodeFieldEnumerableAnnotatedObject.class));
    }

    @Test
    void createCodeFieldObject() {
        Assertions.assertThrows(IllegalArgumentException.class,()->enumerableAdapteeConstantFactoryService.create(CodeFieldObject.class));
    }

    @Test
    void createCodeGetMethodEnum() {
        ImmutableSet<CodeGetMethodEnum> constants = enumerableAdapteeConstantFactoryService.create(CodeGetMethodEnum.class);
        Assertions.assertIterableEquals(constants,ImmutableSet.copyOf(CodeGetMethodEnum.values()));
    }

    @Test
    void createCodeGetMethodEnumerableAnnotatedObject() {
        Assertions.assertThrows(IllegalArgumentException.class,()->enumerableAdapteeConstantFactoryService.create(CodeGetMethodEnumerableAnnotatedObject.class));
    }

    @Test
    void createCodeGetMethodObject() {
        Assertions.assertThrows(IllegalArgumentException.class,()->enumerableAdapteeConstantFactoryService.create(CodeGetMethodObject.class));
    }

    @Test
    void createCodeMethodEnum() {
        ImmutableSet<CodeMethodEnum> constants = enumerableAdapteeConstantFactoryService.create(CodeMethodEnum.class);
        Assertions.assertIterableEquals(constants,ImmutableSet.copyOf(CodeMethodEnum.values()));
    }

    @Test
    void createCodeMethodEnumerableAnnotatedObject() {
        Assertions.assertThrows(IllegalArgumentException.class,()->enumerableAdapteeConstantFactoryService.create(CodeMethodEnumerableAnnotatedObject.class));
    }

    @Test
    void createCodeMethodObject() {
        Assertions.assertThrows(IllegalArgumentException.class,()->enumerableAdapteeConstantFactoryService.create(CodeMethodObject.class));
    }

    @Test
    void createEnumerableImpl() {
        ImmutableSet<EnumerableImpl> constants = enumerableAdapteeConstantFactoryService.create(EnumerableImpl.class);
        Assertions.assertIterableEquals(constants,ImmutableSet.of(EnumerableImpl.INSTANCE));
    }

    @Test
    void createMultipleCodeAnnotatedFieldEnum() {
        ImmutableSet<MultipleCodeAnnotatedFieldEnum> constants = enumerableAdapteeConstantFactoryService.create(MultipleCodeAnnotatedFieldEnum.class);
        Assertions.assertIterableEquals(constants,ImmutableSet.copyOf(MultipleCodeAnnotatedFieldEnum.values()));
    }

    @Test
    void createMultipleCodeAnnotatedFieldEnumerableAnnotatedObject() {
        Assertions.assertThrows(IllegalArgumentException.class,()->enumerableAdapteeConstantFactoryService.create(MultipleCodeAnnotatedFieldEnumerableAnnotatedObject.class));
    }

    @Test
    void createNoCodeEnum() {
        ImmutableSet<NoCodeEnum> constants = enumerableAdapteeConstantFactoryService.create(NoCodeEnum.class);
        Assertions.assertIterableEquals(constants,ImmutableSet.copyOf(NoCodeEnum.values()));
    }

    @Test
    void createNoCodeEnumerableAnnotatedObject() {
        Assertions.assertThrows(IllegalArgumentException.class,()->enumerableAdapteeConstantFactoryService.create(NoCodeEnumerableAnnotatedObject.class));
    }
}