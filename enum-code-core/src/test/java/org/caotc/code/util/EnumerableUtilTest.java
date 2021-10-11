package org.caotc.code.util;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.caotc.code.CodeFieldReader;
import org.caotc.code.CodeMethodReader;
import org.caotc.code.CodeReader;
import org.caotc.code.SpringBootJunit5TestApplicationTests;
import org.caotc.code.factory.EnumerableAdapteeConstantFactory;
import org.caotc.code.factory.EnumerableImplFactory;
import org.caotc.code.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class EnumerableUtilTest extends SpringBootJunit5TestApplicationTests {
    @Resource
    Collection<EnumerableAdapteeConstantFactory<?>> enumerableAdapteeConstantFactories;

    @BeforeAll
    void init(){
        enumerableAdapteeConstantFactories.forEach(EnumerableUtil::addEnumerableAdapteeConstantFactory);
    }

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
    @SneakyThrows
    void findCodeReaderExactCodeAnnotatedFieldEnum() {
        CodeReader<CodeAnnotatedFieldEnum, Integer> codeReader = EnumerableUtil.findCodeReaderExact(CodeAnnotatedFieldEnum.class);
        CodeReader<CodeAnnotatedFieldEnum, Integer> codeFieldReader = new CodeFieldReader<>(CodeAnnotatedFieldEnum.class.getDeclaredField("value"));
        Assertions.assertEquals(codeReader,codeFieldReader);
    }

    @Test
    @SneakyThrows
    void findCodeReaderExactCodeAnnotatedMethodEnum() {
        CodeReader<CodeAnnotatedMethodEnum, Integer> codeReader = EnumerableUtil.findCodeReaderExact(CodeAnnotatedMethodEnum.class);
        CodeReader<CodeAnnotatedMethodEnum, Integer> codeMethodReader = new CodeMethodReader<>(CodeAnnotatedMethodEnum.class.getDeclaredMethod("value"));
        Assertions.assertEquals(codeReader,codeMethodReader);
    }

    @Test
    @SneakyThrows
    void findCodeReaderExactCodeFieldEnum() {
        CodeReader<CodeFieldEnum, Integer> codeReader = EnumerableUtil.findCodeReaderExact(CodeFieldEnum.class);
        CodeReader<CodeFieldEnum, Integer> codeFieldReader = new CodeFieldReader<>(CodeFieldEnum.class.getDeclaredField("code"));
        Assertions.assertEquals(codeReader,codeFieldReader);
    }

    @Test
    @SneakyThrows
    void findCodeReaderExactCodeMethodEnum() {
        CodeReader<CodeMethodEnum, Integer> codeReader = EnumerableUtil.findCodeReaderExact(CodeMethodEnum.class);
        CodeReader<CodeMethodEnum, Integer> codeMethodReader = new CodeMethodReader<>(CodeMethodEnum.class.getDeclaredMethod("code"));
        Assertions.assertEquals(codeReader,codeMethodReader);
    }

    @Test
    void findCodeReaderExactNoCodeEnum() {
        Assertions.assertThrows(NoSuchElementException.class,()->EnumerableUtil.findCodeReaderExact(NoCodeEnum.class));
    }

    @Test
    void findCodeReaderCodeAnnotatedFieldEnum() {
        Assertions.assertTrue(EnumerableUtil.findCodeReader(CodeAnnotatedFieldEnum.class).isPresent());
    }

    @Test
    void findCodeReaderCodeAnnotatedMethodEnum() {
        Assertions.assertTrue(EnumerableUtil.findCodeReader(CodeAnnotatedMethodEnum.class).isPresent());
    }

    @Test
    void findCodeReaderCodeFieldEnum() {
        Assertions.assertTrue(EnumerableUtil.findCodeReader(CodeFieldEnum.class).isPresent());
    }

    @Test
    void findCodeReaderCodeMethodEnum() {
        Assertions.assertTrue(EnumerableUtil.findCodeReader(CodeMethodEnum.class).isPresent());
    }

    @Test
    void findCodeReaderNoCodeEnum() {
        Assertions.assertFalse(EnumerableUtil.findCodeReader(NoCodeEnum.class).isPresent());
    }

    @Test
    void valueOfEnumerableEnum() {
        Optional<CodeFieldEnum> optional = EnumerableUtil.valueOf(CodeFieldEnum.class, CodeFieldEnum.A.code);
        Assertions.assertTrue(optional.isPresent());
        Assertions.assertEquals(optional.get(),CodeFieldEnum.A);
    }

    @Test
    void valueOfEnumerableEnumInvalidCode() {
        Optional<CodeFieldEnum> optional = EnumerableUtil.valueOf(CodeFieldEnum.class, -1);
        Assertions.assertFalse(optional.isPresent());
    }

    @Test
    void valueOfEnumerable() {
        Optional<EnumerableImpl> optional = EnumerableUtil.valueOf(EnumerableImpl.class, EnumerableImpl.INSTANCE.code());
        Assertions.assertTrue(optional.isPresent());
        Assertions.assertEquals(optional.get(),EnumerableImpl.INSTANCE);
    }

    @Test
    void valueOfEnumerableInvalidCode() {
        Optional<EnumerableImpl> optional = EnumerableUtil.valueOf(EnumerableImpl.class, -1);
        Assertions.assertFalse(optional.isPresent());
    }

    @Test
    void valueOfUnEnumerableObject() {
        Assertions.assertThrows(IllegalArgumentException.class,()->EnumerableUtil.valueOf(CodeFieldObject.class, 0));
    }

    @Test
    void valueOfNoConstantEnumerable() {
        Assertions.assertThrows(IllegalArgumentException.class,()->EnumerableUtil.valueOf(CodeFieldEnumerableAnnotatedObject.class, 0));
    }

    @Test
    void valueOfUnEnumerableEnum() {
        Assertions.assertThrows(IllegalArgumentException.class,()->EnumerableUtil.valueOf(NoCodeEnum.class, 0));
    }

    @Test
    void valueOfExactEnumerableEnum() {
        CodeFieldEnum enumerable = EnumerableUtil.valueOfExact(CodeFieldEnum.class, CodeFieldEnum.A.code);
        Assertions.assertEquals(enumerable,CodeFieldEnum.A);
    }

    @Test
    void valueOfExactEnumerableEnumInvalidCode() {
        Assertions.assertThrows(IllegalStateException.class,()->EnumerableUtil.valueOfExact(CodeFieldEnum.class, -1));
    }

    @Test
    void valueOfExactEnumerable() {
        EnumerableImpl enumerable = EnumerableUtil.valueOfExact(EnumerableImpl.class, EnumerableImpl.INSTANCE.code());
        Assertions.assertEquals(enumerable,EnumerableImpl.INSTANCE);
    }

    @Test
    void valueOfExactEnumerableInvalidCode() {
        Assertions.assertThrows(IllegalStateException.class,()->EnumerableUtil.valueOfExact(EnumerableImpl.class, -1));
    }

    @Test
    void valueOfExactUnEnumerableObject() {
        Assertions.assertThrows(IllegalArgumentException.class,()->EnumerableUtil.valueOfExact(CodeFieldObject.class, 0));
    }

    @Test
    void valueOfExactNoConstantEnumerable() {
        Assertions.assertThrows(IllegalArgumentException.class,()->EnumerableUtil.valueOfExact(CodeFieldEnumerableAnnotatedObject.class, 0));
    }

    @Test
    void valueOfExactUnEnumerableEnum() {
        Assertions.assertThrows(IllegalArgumentException.class,()->EnumerableUtil.valueOfExact(NoCodeEnum.class, 0));
    }

    @Test
    void valueOfNullable() {
        Optional<CodeFieldEnum> optional = EnumerableUtil.valueOfNullable(CodeFieldEnum.class, null);
        Assertions.assertFalse(optional.isPresent());
    }

    @Test
    void toCodeEnumerableEnum() {
        Object code = EnumerableUtil.toCode(CodeFieldEnum.A);
        Assertions.assertEquals(code,CodeFieldEnum.A.code);
    }

    @Test
    void toCodeEnumerable() {
        Object code = EnumerableUtil.toCode(EnumerableImpl.INSTANCE);
        Assertions.assertEquals(code,EnumerableImpl.INSTANCE.code());
    }

    @Test
    void toCodeUnEnumerableObject() {
        Assertions.assertThrows(IllegalArgumentException.class,()->EnumerableUtil.toCode(new CodeFieldObject(0)));
    }

    @Test
    void toCodeNoConstantEnumerable() {
        CodeFieldEnumerableAnnotatedObject enumerable = new CodeFieldEnumerableAnnotatedObject(0);
        Object code = EnumerableUtil.toCode(enumerable);
        Assertions.assertEquals(code,enumerable.code);
    }

    @Test
    void toCodeUnEnumerableEnum() {
        Assertions.assertThrows(IllegalArgumentException.class,()->EnumerableUtil.toCode(NoCodeEnum.A));
    }

    @Test
    void toCodeNullable() {
        Object code = EnumerableUtil.toCodeNullable(null);
        Assertions.assertNull(code);
    }
}