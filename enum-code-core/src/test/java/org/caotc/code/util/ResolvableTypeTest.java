package org.caotc.code.util;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.caotc.code.DictionaryConstant;
import org.caotc.code.DictionaryConverter;
import org.caotc.code.SpringBootJunit5TestApplicationTests;
import org.caotc.code.model.CodeAnnotatedFieldEnum;
import org.caotc.code.service.DictionaryConverterFactoryService;
import org.junit.jupiter.api.Test;
import org.springframework.core.ResolvableType;
import org.springframework.core.convert.support.GenericConversionService;

import javax.annotation.Resource;

/**
 * @author caotc
 * @date 2021-12-23
 */
@Slf4j
public class ResolvableTypeTest extends SpringBootJunit5TestApplicationTests {
    @Resource
    DictionaryConverterFactoryService dictionaryConverterFactoryService;

    @SneakyThrows
    @Test
    void isEnumerableUnEnumerable() {
        DictionaryConverter<Integer, CodeAnnotatedFieldEnum> dictionaryConverter = dictionaryConverterFactoryService.<CodeAnnotatedFieldEnum, Integer>create(CodeAnnotatedFieldEnum.class).asList().get(0);
        ResolvableType resolvableType = ResolvableType.forInstance(dictionaryConverter).as(DictionaryConstant.class);
        ResolvableType[] generics = resolvableType.getGenerics();
        for (ResolvableType generic : generics) {
            Class<?> type = generic.resolve();
            log.error("as DictionaryConstant forInstance {}", type);
        }

        resolvableType = ResolvableType.forClass(dictionaryConverter.getClass()).as(DictionaryConstant.class);
        generics = resolvableType.getGenerics();
        for (ResolvableType generic : generics) {
            Class<?> type = generic.resolve();
            log.error("as DictionaryConstant forClass {}", type);
        }
    }

    @Test
    void test() {
        GenericConversionService genericConversionService = new GenericConversionService();
    }
}
