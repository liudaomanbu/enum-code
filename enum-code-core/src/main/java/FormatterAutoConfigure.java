import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.guahao.convention.metrics.code.ClassPathScanner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.filter.AbstractTypeHierarchyTraversingFilter;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author caotc
 * @date 2018-10-26
 * @implSpec
 * @implNote
 * @since 1.0.0
 **/

@Configuration
@Slf4j
public class FormatterAutoConfigure {

    @Bean
    public ClassPathEnumScanner classPathEnumScanner() {
        return new ClassPathEnumScanner();
    }

    @Bean
    public com.guahao.bhc.gateway.api.biz.autoconfigure.EnumSerializerAdapter enumSerializerAdapter(ClassPathEnumScanner classPathEnumScanner) {
        com.guahao.bhc.gateway.api.biz.autoconfigure.EnumSerializerAdapter enumSerializerAdapter = new com.guahao.bhc.gateway.api.biz.autoconfigure.EnumSerializerAdapter();
        classPathEnumScanner.findClasses("com.guahao.bhc.gateway.api.share.common.enums")
                .forEach(enumType -> {
                    SerializeConfig.getGlobalInstance().put(enumType, enumSerializerAdapter);
                });
        return enumSerializerAdapter;
    }

    @Bean
    public EnumDeserializerAdapter enumDeserializerAdapter(ClassPathEnumScanner classPathEnumScanner) {
        EnumDeserializerAdapter enumDeserializerAdapter = new EnumDeserializerAdapter();
        classPathEnumScanner.findClasses("com.guahao.bhc.gateway.api.share.common.enums")
                .forEach(enumType -> {
                    ParserConfig.getGlobalInstance().putDeserializer(enumType, enumDeserializerAdapter);
                });
        return enumDeserializerAdapter;
    }

    static class ClassPathEnumScanner extends ClassPathScanner {
        public ClassPathEnumScanner() {
            super(false);
            addIncludeFilter(new AbstractTypeHierarchyTraversingFilter(false, false) {
                @Override
                protected boolean matchClassName(String className) {
                    return true;
                }
            });
        }

        @Override
        protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
            return !beanDefinition.getMetadata().isInterface();
        }

        @Override
        public Set<Class<?>> findClasses(String basePackage) {
            return super.findClasses(basePackage).stream()
                    .filter(type -> type.isEnum() || type.getSuperclass().isEnum())
                    .collect(Collectors.toSet());
        }
    }
}
