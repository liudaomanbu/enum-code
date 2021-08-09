import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.Statement;
import java.util.Collection;
import java.util.Properties;

/**
 * 结果集拦截器
 *
 * @author caotc
 * @date 2019-07-24
 * @since 1.0.0
 */
@Component
@Intercepts({
        @Signature(type = ResultSetHandler.class, method = "handleResultSets", args = {Statement.class})
})
@Slf4j
public class DbDefaultValueSelectInterceptor implements Interceptor {
    @Resource
    DbDefaultValueHandler dbDefaultValueHandler;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object result = invocation.proceed();
        if (result instanceof Collection) {
            dbDefaultValueHandler.clearDbDefaultValue(result);
        }
        return result;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
