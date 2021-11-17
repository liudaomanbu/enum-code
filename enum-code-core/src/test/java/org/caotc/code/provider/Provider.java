package org.caotc.code.provider;

import com.google.common.collect.Streams;
import lombok.experimental.UtilityClass;
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
import org.caotc.code.model.MultipleCodeAnnotatedFieldObject;
import org.caotc.code.model.NoCodeEnum;
import org.caotc.code.model.NoCodeEnumerableAnnotatedObject;
import org.junit.jupiter.params.provider.Arguments;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Stream;

/**
 * @author caotc
 * @date 2021-10-12
 */
@UtilityClass
public class Provider {
    private static final int DEFAULT_CODE = 0;
    private static final Random RANDOM = new Random();

    static Stream<Object> enumerables() {
        return Streams.concat(Stream.of(CodeAnnotatedFieldEnum.values()), Stream.of(CodeAnnotatedMethodEnum.values()), Stream.of(CodeFieldAndCodeMethodAndCodeAnnotatedFieldEnum.values())
                , Stream.of(CodeFieldAndCodeMethodAndCodeAnnotatedMethodEnum.values()), Stream.of(CodeFieldAndCodeMethodEnum.values()), Stream.of(CodeFieldEnum.values())
                , Stream.of(CodeGetMethodEnum.values()), Stream.of(CodeMethodEnum.values())
                , Stream.of(new CodeAnnotatedFieldEnumerableAnnotatedObject(RANDOM.nextInt()), new CodeAnnotatedMethodEnumerableAnnotatedObject(RANDOM.nextInt())
                        , new CodeFieldAndCodeMethodAndCodeAnnotatedFieldEnumerableAnnotatedObject(RANDOM.nextInt(), RANDOM.nextInt())
                        , new CodeFieldAndCodeMethodAndCodeAnnotatedMethodEnumerableAnnotatedObject(RANDOM.nextInt())
                        , new CodeFieldAndCodeMethodEnumerableAnnotatedObject(RANDOM.nextInt()), new CodeFieldEnumerableAnnotatedObject(RANDOM.nextInt())
                        , new CodeGetMethodEnumerableAnnotatedObject(RANDOM.nextInt()), new CodeMethodEnumerableAnnotatedObject(RANDOM.nextInt()), DictionaryImpl.INSTANCE));
    }

    @SuppressWarnings("unchecked")
    static Stream<Class<? extends Enum<?>>> enumerableEnumTypes() {
        return enumerableEnumInstanceAndCodes()
                .map(Arguments::get)
                .map(arguments -> (Class<? extends Enum<?>>) arguments[0].getClass());
    }

    static Stream<Arguments> enumerableEnumInstanceAndCodes() {
        return Streams.concat(Arrays.stream(CodeAnnotatedFieldEnum.values()).map(codeAnnotatedFieldEnum -> Arguments.of(codeAnnotatedFieldEnum, codeAnnotatedFieldEnum.value()))
                , Arrays.stream(CodeAnnotatedMethodEnum.values()).map(codeAnnotatedMethodEnum -> Arguments.of(codeAnnotatedMethodEnum, codeAnnotatedMethodEnum.value()))
                , Arrays.stream(CodeFieldAndCodeMethodAndCodeAnnotatedFieldEnum.values()).map(codeFieldAndCodeMethodAndCodeAnnotatedFieldEnum -> Arguments.of(codeFieldAndCodeMethodAndCodeAnnotatedFieldEnum, codeFieldAndCodeMethodAndCodeAnnotatedFieldEnum.value))
                , Arrays.stream(CodeFieldAndCodeMethodAndCodeAnnotatedMethodEnum.values()).map(codeFieldAndCodeMethodAndCodeAnnotatedMethodEnum -> Arguments.of(codeFieldAndCodeMethodAndCodeAnnotatedMethodEnum, codeFieldAndCodeMethodAndCodeAnnotatedMethodEnum.abc()))
                , Arrays.stream(CodeFieldAndCodeMethodEnum.values()).map(codeFieldAndCodeMethodEnum -> Arguments.of(codeFieldAndCodeMethodEnum, codeFieldAndCodeMethodEnum.code()))
                , Arrays.stream(CodeFieldEnum.values()).map(codeFieldEnum -> Arguments.of(codeFieldEnum, codeFieldEnum.code))
                , Arrays.stream(CodeGetMethodEnum.values()).map(codeGetMethodEnum -> Arguments.of(codeGetMethodEnum, codeGetMethodEnum.getCode()))
                , Arrays.stream(CodeMethodEnum.values()).map(codeMethodEnum -> Arguments.of(codeMethodEnum, codeMethodEnum.code())));
    }

    static Stream<Class<?>> enumerableOtherTypes() {
        return enumerableOtherInstanceAndCodes()
                .map(Arguments::get)
                .map(arguments -> arguments[0].getClass());
    }

    static Stream<Arguments> enumerableOtherInstanceAndCodes() {
        return Stream.of(Arguments.of(DictionaryImpl.INSTANCE, DictionaryImpl.INSTANCE.code()));
    }

    static Stream<Arguments> enumerableAndCodes() {
        return Streams.concat(enumerableEnumInstanceAndCodes(), enumerableOtherInstanceAndCodes());
    }

    static Stream<Class<?>> enumerableTypes() {
        return Streams.concat(enumerableEnumTypes(), enumerableOtherTypes());
    }

    static Stream<Arguments> enumerableTypeAndInvalidCode() {
        return enumerableTypes()
                .map(type -> Arguments.of(type, RANDOM.nextInt(Integer.MAX_VALUE) + 1));
    }

    static Stream<Class<?>> unEnumerableTypes() {
        return unEnumerables()
                .map(Object::getClass);
    }

    static Stream<Object> unEnumerables() {
        return Stream.of(new CodeAnnotatedFieldObject(DEFAULT_CODE)
                , new CodeAnnotatedMethodObject(DEFAULT_CODE)
                , new CodeFieldAndCodeMethodAndCodeAnnotatedFieldObject(RANDOM.nextInt(), DEFAULT_CODE)
                , new CodeFieldAndCodeMethodAndCodeAnnotatedMethodObject(DEFAULT_CODE)
                , new CodeFieldAndCodeMethodObject(DEFAULT_CODE)
                , new CodeFieldObject(DEFAULT_CODE), new CodeGetMethodObject(DEFAULT_CODE), new CodeMethodObject(DEFAULT_CODE)
                , new MultipleCodeAnnotatedFieldObject(DEFAULT_CODE, RANDOM.nextInt())
                , NoCodeEnum.A, new NoCodeEnumerableAnnotatedObject(), new Object());
    }

    static Stream<Class<?>> illegalEnumerableTypes() {
        return Stream.of(MultipleCodeAnnotatedFieldEnum.class, MultipleCodeAnnotatedFieldEnumerableAnnotatedObject.class);
    }

    static Stream<Class<?>> noConstantEnumerableTypes() {
        return noConstantEnumerableAndCodes()
                .map(Arguments::get)
                .map(arguments -> arguments[0].getClass());
    }

    static Stream<Arguments> noConstantEnumerableAndCodes() {
        return Stream.of(Arguments.of(new CodeAnnotatedFieldEnumerableAnnotatedObject(DEFAULT_CODE), DEFAULT_CODE)
                , Arguments.of(new CodeAnnotatedMethodEnumerableAnnotatedObject(DEFAULT_CODE), DEFAULT_CODE)
                , Arguments.of(new CodeFieldAndCodeMethodAndCodeAnnotatedFieldEnumerableAnnotatedObject(RANDOM.nextInt(), DEFAULT_CODE), DEFAULT_CODE)
                , Arguments.of(new CodeFieldAndCodeMethodAndCodeAnnotatedMethodEnumerableAnnotatedObject(DEFAULT_CODE), DEFAULT_CODE)
                , Arguments.of(new CodeFieldAndCodeMethodEnumerableAnnotatedObject(DEFAULT_CODE), DEFAULT_CODE)
                , Arguments.of(new CodeFieldEnumerableAnnotatedObject(DEFAULT_CODE), DEFAULT_CODE)
                , Arguments.of(new CodeGetMethodEnumerableAnnotatedObject(DEFAULT_CODE), DEFAULT_CODE)
                , Arguments.of(new CodeMethodEnumerableAnnotatedObject(DEFAULT_CODE), DEFAULT_CODE));
    }

    static Stream<Arguments> enumerableTypeAndCodeReaders() {
        return Stream.of(Arguments.of(new CodeAnnotatedFieldEnumerableAnnotatedObject(DEFAULT_CODE), DEFAULT_CODE)
                , Arguments.of(new CodeAnnotatedMethodEnumerableAnnotatedObject(DEFAULT_CODE), DEFAULT_CODE)
                , Arguments.of(new CodeFieldAndCodeMethodAndCodeAnnotatedFieldEnumerableAnnotatedObject(RANDOM.nextInt(), DEFAULT_CODE), DEFAULT_CODE)
                , Arguments.of(new CodeFieldAndCodeMethodAndCodeAnnotatedMethodEnumerableAnnotatedObject(DEFAULT_CODE), DEFAULT_CODE)
                , Arguments.of(new CodeFieldAndCodeMethodEnumerableAnnotatedObject(DEFAULT_CODE), DEFAULT_CODE)
                , Arguments.of(new CodeFieldEnumerableAnnotatedObject(DEFAULT_CODE), DEFAULT_CODE)
                , Arguments.of(new CodeGetMethodEnumerableAnnotatedObject(DEFAULT_CODE), DEFAULT_CODE)
                , Arguments.of(new CodeMethodEnumerableAnnotatedObject(DEFAULT_CODE), DEFAULT_CODE));
    }
}