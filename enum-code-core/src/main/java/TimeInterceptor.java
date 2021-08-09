import com.guahao.bhc.data.service.biz.model.BaseDO;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.Properties;

/**
* 给DO的insert和update操作设置固定时间字段的值
*
* @author caotc
* @date 2019-06-12
* @since 1.0.0
*/
@Component
@Intercepts({
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class,
                Object.class})
})
@Slf4j
public class TimeInterceptor implements Interceptor {

    private void setTime(@NonNull Object param, @NonNull SqlCommandType sqlCommandType) {
        if (param instanceof Collection) {
            ((Collection<?>) param).forEach(i -> setTime(i, sqlCommandType));
            return;
        }
        if (param instanceof Map) {
            ((Map<?, ?>) param).values().forEach(i -> setTime(i, sqlCommandType));
            return;
        }
        if (param instanceof BaseDO) {
            ((BaseDO<?>) param).setGmtModified(LocalDateTime.now());
            if (SqlCommandType.INSERT.equals(sqlCommandType)) {
                ((BaseDO<?>) param).setGmtCreated(LocalDateTime.now());
            }
        }
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement= (MappedStatement) invocation.getArgs()[0];
        Object param= invocation.getArgs()[1];
        setTime(param, mappedStatement.getSqlCommandType());
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
