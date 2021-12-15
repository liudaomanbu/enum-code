package org.caotc.code;

import org.caotc.code.factory.CodeReaderDictionaryAdapterFactory;
import org.caotc.code.factory.DictionaryAdapteeConstantFactory;
import org.caotc.code.factory.DictionaryAdapteeConstantsFactoryToDictionaryConverterClassFactoryAdapter;
import org.caotc.code.factory.DictionaryAdapterFactory;
import org.caotc.code.factory.DictionaryConverterClassFactory;
import org.caotc.code.factory.DictionaryConverterFactory;
import org.caotc.code.factory.DictionaryImplFactory;
import org.caotc.code.factory.EnumConstantFactory;
import org.caotc.code.service.DictionaryAdapteeConstantFactoryService;
import org.caotc.code.service.DictionaryAdapterFactoryService;
import org.caotc.code.service.DictionaryConverterFactoryService;
import org.caotc.code.service.DictionaryGroupService;
import org.caotc.code.service.DictionaryService;
import org.caotc.code.service.impl.DefaultDictionaryAdapteeConstantFactoryService;
import org.caotc.code.service.impl.DefaultDictionaryAdapterFactoryService;
import org.caotc.code.service.impl.DefaultDictionaryConverterFactoryService;
import org.caotc.code.service.impl.DefaultDictionaryGroupService;
import org.caotc.code.service.impl.DefaultDictionaryService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;

/**
 * @author caotc
 * @date 2021-10-11
 */
@Configuration
public class TestConfiguration {

    @Bean
    public EnumConstantFactory enumConstantFactory() {
        return new EnumConstantFactory();
    }

    @Bean
    public DictionaryImplFactory enumerableImplFactory() {
        return new DictionaryImplFactory();
    }

    @Bean
    public DictionaryAdapteeConstantFactoryService enumerableAdapteeConstantFactoryService(Collection<DictionaryAdapteeConstantFactory<?>> factories) {
        return new DefaultDictionaryAdapteeConstantFactoryService(factories);
    }

    @Bean
    public CodeReaderDictionaryAdapterFactory codeReaderEnumerableAdapterFactory() {
        return new CodeReaderDictionaryAdapterFactory();
    }

    @Bean
    public DictionaryAdapterFactoryService enumerableAdapterFactoryService(Collection<DictionaryAdapterFactory<?>> factories) {
        return new DefaultDictionaryAdapterFactoryService(factories);
    }

    @Bean
    public DictionaryAdapteeConstantsFactoryToDictionaryConverterClassFactoryAdapter enumerableAdapteeConstantsFactoryToEnumerableConstantFactoryAdapter(DictionaryAdapteeConstantFactoryService dictionaryAdapteeConstantFactoryService, DictionaryAdapterFactoryService dictionaryAdapterFactoryService) {
        return new DictionaryAdapteeConstantsFactoryToDictionaryConverterClassFactoryAdapter(dictionaryAdapteeConstantFactoryService, dictionaryAdapterFactoryService);
    }

    @Bean
    public DictionaryConverterFactoryService enumerableConstantFactoryService(Collection<DictionaryConverterClassFactory<?>> classFactories, Collection<DictionaryConverterFactory<?>> factories) {
        return new DefaultDictionaryConverterFactoryService(classFactories, factories);
    }

    @Bean
    public DictionaryGroupService dictionaryGroupService() {
        return new DefaultDictionaryGroupService();
    }

    @Bean
    public DictionaryService enumerableService(DictionaryConverterFactoryService dictionaryConverterFactoryService, DictionaryGroupService dictionaryGroupService) {
        return new DefaultDictionaryService(dictionaryConverterFactoryService, dictionaryGroupService);
    }
}
