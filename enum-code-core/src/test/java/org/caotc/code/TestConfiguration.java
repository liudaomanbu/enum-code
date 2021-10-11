package org.caotc.code;

import org.caotc.code.factory.*;
import org.caotc.code.service.EnumerableAdapteeConstantFactoryService;
import org.caotc.code.service.EnumerableAdapterFactoryService;
import org.caotc.code.service.EnumerableConstantFactoryService;
import org.caotc.code.service.EnumerableService;
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
    public EnumConstantFactory enumConstantFactory(){
        return new EnumConstantFactory();
    }

    @Bean
    public EnumerableImplFactory enumerableImplFactory(){
        return new EnumerableImplFactory();
    }

    @Bean
    public EnumerableAdapteeConstantFactoryService enumerableAdapteeConstantFactoryService(Collection<EnumerableAdapteeConstantFactory<?>> factories){
        return new EnumerableAdapteeConstantFactoryService(factories);
    }

    @Bean
    public CodeReaderEnumerableAdapterFactory codeReaderEnumerableAdapterFactory(){
        return new CodeReaderEnumerableAdapterFactory();
    }

    @Bean
    public EnumerableAdapterFactoryService enumerableAdapterFactoryService(Collection<EnumerableAdapterFactory<?>> factories){
        return new EnumerableAdapterFactoryService(factories);
    }

    @Bean
    public EnumerableAdapteeConstantsFactoryToEnumerableConstantFactoryAdapter enumerableAdapteeConstantsFactoryToEnumerableConstantFactoryAdapter(EnumerableAdapteeConstantFactoryService enumerableAdapteeConstantFactoryService,EnumerableAdapterFactoryService enumerableAdapterFactoryService){
        return new EnumerableAdapteeConstantsFactoryToEnumerableConstantFactoryAdapter(enumerableAdapteeConstantFactoryService,enumerableAdapterFactoryService);
    }

    @Bean
    public EnumerableConstantFactoryService enumerableConstantFactoryService(Collection<EnumerableConstantFactory<?>> factories){
        return new EnumerableConstantFactoryService(factories);
    }

    @Bean
    public EnumerableService enumerableService(EnumerableAdapterFactoryService enumerableAdapterFactoryService,EnumerableConstantFactoryService enumerableConstantFactoryService){
        return new EnumerableService(enumerableAdapterFactoryService,enumerableConstantFactoryService);
    }
}
