import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author caotc
 * @since 1.0.0
 */
@Configuration
@Slf4j
@ConditionalOnProperty(value = "logic-delete.enabled", havingValue = "true", matchIfMissing = true)
public class LogicDeleteAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public LogicDeleteQueryInterceptor logicDeleteQueryInterceptor() {
        return new LogicDeleteQueryInterceptor();
    }

    @Bean
    @ConditionalOnMissingBean
    public LogicDeleteUpdateInterceptor logicDeleteUpdateInterceptor() {
        return new LogicDeleteUpdateInterceptor();
    }
}
