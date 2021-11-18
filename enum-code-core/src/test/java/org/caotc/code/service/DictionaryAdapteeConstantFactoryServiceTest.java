package org.caotc.code.service;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import lombok.extern.slf4j.Slf4j;
import org.caotc.code.factory.DictionaryImplFactory;
import org.caotc.code.factory.EnumConstantFactory;
import org.caotc.code.model.CodeAnnotatedFieldEnum;
import org.caotc.code.model.CodeAnnotatedFieldEnumerableAnnotatedObject;
import org.caotc.code.model.CodeAnnotatedFieldObject;
import org.caotc.code.model.CodeAnnotatedMethodEnum;
import org.caotc.code.model.CodeAnnotatedMethodEnumerableAnnotatedObject;
import org.caotc.code.model.CodeAnnotatedMethodObject;
import org.caotc.code.model.CodeFieldAndCodeMethodAndCodeAnnotatedFieldEnum;
import org.caotc.code.model.CodeFieldAndCodeMethodAndCodeAnnotatedFieldEnumerableAnnotatedObject;
import org.caotc.code.model.CodeFieldAndCodeMethodAndCodeAnnotatedFieldObject;
import org.caotc.code.model.CodeFieldAndCodeMethodAndCodeAnnotatedMethodEnum;
import org.caotc.code.model.CodeFieldAndCodeMethodAndCodeAnnotatedMethodEnumerableAnnotatedObject;
import org.caotc.code.model.CodeFieldAndCodeMethodAndCodeAnnotatedMethodObject;
import org.caotc.code.model.CodeFieldAndCodeMethodEnum;
import org.caotc.code.model.CodeFieldAndCodeMethodEnumerableAnnotatedObject;
import org.caotc.code.model.CodeFieldAndCodeMethodObject;
import org.caotc.code.model.CodeFieldEnum;
import org.caotc.code.model.CodeFieldEnumerableAnnotatedObject;
import org.caotc.code.model.CodeFieldObject;
import org.caotc.code.model.CodeGetMethodEnum;
import org.caotc.code.model.CodeGetMethodEnumerableAnnotatedObject;
import org.caotc.code.model.CodeGetMethodObject;
import org.caotc.code.model.CodeMethodEnum;
import org.caotc.code.model.CodeMethodEnumerableAnnotatedObject;
import org.caotc.code.model.CodeMethodObject;
import org.caotc.code.model.DictionaryImpl;
import org.caotc.code.model.MultipleCodeAnnotatedFieldEnum;
import org.caotc.code.model.MultipleCodeAnnotatedFieldEnumerableAnnotatedObject;
import org.caotc.code.model.NoCodeEnum;
import org.caotc.code.model.NoCodeEnumerableAnnotatedObject;
import org.caotc.code.service.impl.DefaultDictionaryAdapteeConstantFactoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@Slf4j
class DictionaryAdapteeConstantFactoryServiceTest {
    DictionaryAdapteeConstantFactoryService dictionaryAdapteeConstantFactoryService
            = new DefaultDictionaryAdapteeConstantFactoryService(ImmutableList.of(new EnumConstantFactory(), new DictionaryImplFactory()));

    @Test
    void supportCodeAnnotatedFieldEnum() {
        Assertions.assertTrue(dictionaryAdapteeConstantFactoryService.support(CodeAnnotatedFieldEnum.class));
    }

    @Test
    void supportCodeAnnotatedFieldEnumerableAnnotatedObject() {
        Assertions.assertFalse(dictionaryAdapteeConstantFactoryService.support(CodeAnnotatedFieldEnumerableAnnotatedObject.class));
    }

    @Test
    void supportCodeAnnotatedFieldObject() {
        Assertions.assertFalse(dictionaryAdapteeConstantFactoryService.support(CodeAnnotatedFieldObject.class));
    }

    @Test
    void supportCodeAnnotatedMethodEnum() {
        Assertions.assertTrue(dictionaryAdapteeConstantFactoryService.support(CodeAnnotatedMethodEnum.class));
    }

    @Test
    void supportCodeAnnotatedMethodEnumerableAnnotatedObject() {
        Assertions.assertFalse(dictionaryAdapteeConstantFactoryService.support(CodeAnnotatedMethodEnumerableAnnotatedObject.class));
    }

    @Test
    void supportCodeAnnotatedMethodObject() {
        Assertions.assertFalse(dictionaryAdapteeConstantFactoryService.support(CodeAnnotatedMethodObject.class));
    }

    @Test
    void supportCodeFieldAndCodeMethodAndCodeAnnotatedFieldEnum() {
        Assertions.assertTrue(dictionaryAdapteeConstantFactoryService.support(CodeFieldAndCodeMethodAndCodeAnnotatedFieldEnum.class));
    }

    @Test
    void supportCodeFieldAndCodeMethodAndCodeAnnotatedFieldEnumerableAnnotatedObject() {
        Assertions.assertFalse(dictionaryAdapteeConstantFactoryService.support(CodeFieldAndCodeMethodAndCodeAnnotatedFieldEnumerableAnnotatedObject.class));
    }

    @Test
    void supportCodeFieldAndCodeMethodAndCodeAnnotatedFieldObject() {
        Assertions.assertFalse(dictionaryAdapteeConstantFactoryService.support(CodeFieldAndCodeMethodAndCodeAnnotatedFieldObject.class));
    }

    @Test
    void supportCodeFieldAndCodeMethodAndCodeAnnotatedMethodEnum() {
        Assertions.assertTrue(dictionaryAdapteeConstantFactoryService.support(CodeFieldAndCodeMethodAndCodeAnnotatedMethodEnum.class));
    }

    @Test
    void supportCodeFieldAndCodeMethodAndCodeAnnotatedMethodEnumerableAnnotatedObject() {
        Assertions.assertFalse(dictionaryAdapteeConstantFactoryService.support(CodeFieldAndCodeMethodAndCodeAnnotatedMethodEnumerableAnnotatedObject.class));
    }

    @Test
    void supportCodeFieldAndCodeMethodAndCodeAnnotatedMethodObject() {
        Assertions.assertFalse(dictionaryAdapteeConstantFactoryService.support(CodeFieldAndCodeMethodAndCodeAnnotatedMethodObject.class));
    }

    @Test
    void supportCodeFieldAndCodeMethodEnum() {
        Assertions.assertTrue(dictionaryAdapteeConstantFactoryService.support(CodeFieldAndCodeMethodEnum.class));
    }

    @Test
    void supportCodeFieldAndCodeMethodEnumerableAnnotatedObject() {
        Assertions.assertFalse(dictionaryAdapteeConstantFactoryService.support(CodeFieldAndCodeMethodEnumerableAnnotatedObject.class));
    }

    @Test
    void supportCodeFieldAndCodeMethodObject() {
        Assertions.assertFalse(dictionaryAdapteeConstantFactoryService.support(CodeFieldAndCodeMethodObject.class));
    }

    @Test
    void supportCodeFieldEnum() {
        Assertions.assertTrue(dictionaryAdapteeConstantFactoryService.support(CodeFieldEnum.class));
    }

    @Test
    void supportCodeFieldEnumerableAnnotatedObject() {
        Assertions.assertFalse(dictionaryAdapteeConstantFactoryService.support(CodeFieldEnumerableAnnotatedObject.class));
    }

    @Test
    void supportCodeFieldObject() {
        Assertions.assertFalse(dictionaryAdapteeConstantFactoryService.support(CodeFieldObject.class));
    }

    @Test
    void supportCodeGetMethodEnum() {
        Assertions.assertTrue(dictionaryAdapteeConstantFactoryService.support(CodeGetMethodEnum.class));
    }

    @Test
    void supportCodeGetMethodEnumerableAnnotatedObject() {
        Assertions.assertFalse(dictionaryAdapteeConstantFactoryService.support(CodeGetMethodEnumerableAnnotatedObject.class));
    }

    @Test
    void supportCodeGetMethodObject() {
        Assertions.assertFalse(dictionaryAdapteeConstantFactoryService.support(CodeGetMethodObject.class));
    }

    @Test
    void supportCodeMethodEnum() {
        Assertions.assertTrue(dictionaryAdapteeConstantFactoryService.support(CodeMethodEnum.class));
    }

    @Test
    void supportCodeMethodEnumerableAnnotatedObject() {
        Assertions.assertFalse(dictionaryAdapteeConstantFactoryService.support(CodeMethodEnumerableAnnotatedObject.class));
    }

    @Test
    void supportCodeMethodObject() {
        Assertions.assertFalse(dictionaryAdapteeConstantFactoryService.support(CodeMethodObject.class));
    }

    @Test
    void supportEnumerableImpl() {
        Assertions.assertTrue(dictionaryAdapteeConstantFactoryService.support(DictionaryImpl.class));
    }

    @Test
    void supportMultipleCodeAnnotatedFieldEnum() {
        Assertions.assertTrue(dictionaryAdapteeConstantFactoryService.support(MultipleCodeAnnotatedFieldEnum.class));
    }

    @Test
    void supportMultipleCodeAnnotatedFieldEnumerableAnnotatedObject() {
        Assertions.assertFalse(dictionaryAdapteeConstantFactoryService.support(MultipleCodeAnnotatedFieldEnumerableAnnotatedObject.class));
    }

    @Test
    void supportNoCodeEnum() {
        Assertions.assertTrue(dictionaryAdapteeConstantFactoryService.support(NoCodeEnum.class));
    }

    @Test
    void supportNoCodeEnumerableAnnotatedObject() {
        Assertions.assertFalse(dictionaryAdapteeConstantFactoryService.support(NoCodeEnumerableAnnotatedObject.class));
    }

    @Test
    void createCodeAnnotatedFieldEnum() {
        ImmutableSet<CodeAnnotatedFieldEnum> constants = dictionaryAdapteeConstantFactoryService.create(CodeAnnotatedFieldEnum.class);
        Assertions.assertIterableEquals(constants,ImmutableSet.copyOf(CodeAnnotatedFieldEnum.values()));
    }

    @Test
    void createCodeAnnotatedFieldEnumerableAnnotatedObject() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> dictionaryAdapteeConstantFactoryService.create(CodeAnnotatedFieldEnumerableAnnotatedObject.class));
    }

    @Test
    void createCodeAnnotatedFieldObject() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> dictionaryAdapteeConstantFactoryService.create(CodeAnnotatedFieldObject.class));
    }

    @Test
    void createCodeAnnotatedMethodEnum() {
        ImmutableSet<CodeAnnotatedMethodEnum> constants = dictionaryAdapteeConstantFactoryService.create(CodeAnnotatedMethodEnum.class);
        Assertions.assertIterableEquals(constants,ImmutableSet.copyOf(CodeAnnotatedMethodEnum.values()));
    }

    @Test
    void createCodeAnnotatedMethodEnumerableAnnotatedObject() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> dictionaryAdapteeConstantFactoryService.create(CodeAnnotatedMethodEnumerableAnnotatedObject.class));
    }

    @Test
    void createCodeAnnotatedMethodObject() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> dictionaryAdapteeConstantFactoryService.create(CodeAnnotatedMethodObject.class));
    }

    @Test
    void createCodeFieldAndCodeMethodAndCodeAnnotatedFieldEnum() {
        ImmutableSet<CodeFieldAndCodeMethodAndCodeAnnotatedFieldEnum> constants = dictionaryAdapteeConstantFactoryService.create(CodeFieldAndCodeMethodAndCodeAnnotatedFieldEnum.class);
        Assertions.assertIterableEquals(constants,ImmutableSet.copyOf(CodeFieldAndCodeMethodAndCodeAnnotatedFieldEnum.values()));
    }

    @Test
    void createCodeFieldAndCodeMethodAndCodeAnnotatedFieldEnumerableAnnotatedObject() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> dictionaryAdapteeConstantFactoryService.create(CodeFieldAndCodeMethodAndCodeAnnotatedFieldEnumerableAnnotatedObject.class));
    }

    @Test
    void createCodeFieldAndCodeMethodAndCodeAnnotatedFieldObject() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> dictionaryAdapteeConstantFactoryService.create(CodeFieldAndCodeMethodAndCodeAnnotatedFieldObject.class));
    }

    @Test
    void createCodeFieldAndCodeMethodAndCodeAnnotatedMethodEnum() {
        ImmutableSet<CodeFieldAndCodeMethodAndCodeAnnotatedMethodEnum> constants = dictionaryAdapteeConstantFactoryService.create(CodeFieldAndCodeMethodAndCodeAnnotatedMethodEnum.class);
        Assertions.assertIterableEquals(constants,ImmutableSet.copyOf(CodeFieldAndCodeMethodAndCodeAnnotatedMethodEnum.values()));
    }

    @Test
    void createCodeFieldAndCodeMethodAndCodeAnnotatedMethodEnumerableAnnotatedObject() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> dictionaryAdapteeConstantFactoryService.create(CodeFieldAndCodeMethodAndCodeAnnotatedMethodEnumerableAnnotatedObject.class));
    }

    @Test
    void createCodeFieldAndCodeMethodAndCodeAnnotatedMethodObject() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> dictionaryAdapteeConstantFactoryService.create(CodeFieldAndCodeMethodAndCodeAnnotatedMethodObject.class));
    }

    @Test
    void createCodeFieldAndCodeMethodEnum() {
        ImmutableSet<CodeFieldAndCodeMethodEnum> constants = dictionaryAdapteeConstantFactoryService.create(CodeFieldAndCodeMethodEnum.class);
        Assertions.assertIterableEquals(constants,ImmutableSet.copyOf(CodeFieldAndCodeMethodEnum.values()));
    }

    @Test
    void createCodeFieldAndCodeMethodEnumerableAnnotatedObject() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> dictionaryAdapteeConstantFactoryService.create(CodeFieldAndCodeMethodEnumerableAnnotatedObject.class));
    }

    @Test
    void createCodeFieldAndCodeMethodObject() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> dictionaryAdapteeConstantFactoryService.create(CodeFieldAndCodeMethodObject.class));
    }

    @Test
    void createCodeFieldEnum() {
        ImmutableSet<CodeFieldEnum> constants = dictionaryAdapteeConstantFactoryService.create(CodeFieldEnum.class);
        Assertions.assertIterableEquals(constants,ImmutableSet.copyOf(CodeFieldEnum.values()));
    }

    @Test
    void createCodeFieldEnumerableAnnotatedObject() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> dictionaryAdapteeConstantFactoryService.create(CodeFieldEnumerableAnnotatedObject.class));
    }

    @Test
    void createCodeFieldObject() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> dictionaryAdapteeConstantFactoryService.create(CodeFieldObject.class));
    }

    @Test
    void createCodeGetMethodEnum() {
        ImmutableSet<CodeGetMethodEnum> constants = dictionaryAdapteeConstantFactoryService.create(CodeGetMethodEnum.class);
        Assertions.assertIterableEquals(constants,ImmutableSet.copyOf(CodeGetMethodEnum.values()));
    }

    @Test
    void createCodeGetMethodEnumerableAnnotatedObject() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> dictionaryAdapteeConstantFactoryService.create(CodeGetMethodEnumerableAnnotatedObject.class));
    }

    @Test
    void createCodeGetMethodObject() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> dictionaryAdapteeConstantFactoryService.create(CodeGetMethodObject.class));
    }

    @Test
    void createCodeMethodEnum() {
        ImmutableSet<CodeMethodEnum> constants = dictionaryAdapteeConstantFactoryService.create(CodeMethodEnum.class);
        Assertions.assertIterableEquals(constants,ImmutableSet.copyOf(CodeMethodEnum.values()));
    }

    @Test
    void createCodeMethodEnumerableAnnotatedObject() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> dictionaryAdapteeConstantFactoryService.create(CodeMethodEnumerableAnnotatedObject.class));
    }

    @Test
    void createCodeMethodObject() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> dictionaryAdapteeConstantFactoryService.create(CodeMethodObject.class));
    }

    @Test
    void createEnumerableImpl() {
        ImmutableSet<DictionaryImpl> constants = dictionaryAdapteeConstantFactoryService.create(DictionaryImpl.class);
        Assertions.assertIterableEquals(constants, ImmutableSet.of(DictionaryImpl.INSTANCE));
    }

    @Test
    void createMultipleCodeAnnotatedFieldEnum() {
        ImmutableSet<MultipleCodeAnnotatedFieldEnum> constants = dictionaryAdapteeConstantFactoryService.create(MultipleCodeAnnotatedFieldEnum.class);
        Assertions.assertIterableEquals(constants,ImmutableSet.copyOf(MultipleCodeAnnotatedFieldEnum.values()));
    }

    @Test
    void createMultipleCodeAnnotatedFieldEnumerableAnnotatedObject() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> dictionaryAdapteeConstantFactoryService.create(MultipleCodeAnnotatedFieldEnumerableAnnotatedObject.class));
    }

    @Test
    void createNoCodeEnum() {
        ImmutableSet<NoCodeEnum> constants = dictionaryAdapteeConstantFactoryService.create(NoCodeEnum.class);
        Assertions.assertIterableEquals(constants,ImmutableSet.copyOf(NoCodeEnum.values()));
    }

    @Test
    void createNoCodeEnumerableAnnotatedObject() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> dictionaryAdapteeConstantFactoryService.create(NoCodeEnumerableAnnotatedObject.class));
    }
}