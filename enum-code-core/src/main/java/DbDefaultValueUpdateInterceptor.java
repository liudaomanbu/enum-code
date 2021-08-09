import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.Statement;
import java.util.Properties;

/**
 * 数据库默认值的Update方法拦截器.
 * 在StatementHandler拦截是为动态字段更新方法时不设置默认值.
*
* @author caotc
* @date 2019-06-11
* @since 1.0.0
*/
@Component
@Intercepts({
        @Signature(type = StatementHandler.class, method = "parameterize", args = {Statement.class})
})
@Slf4j
public class DbDefaultValueUpdateInterceptor implements Interceptor {
    private static final String STATEMENT_HANDLER_MAPPED_STATEMENT_FIELD_NAME = "delegate.mappedStatement";
    @Resource
    DbDefaultValueHandler dbDefaultValueHandler;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler= (StatementHandler) invocation.getTarget();
        MappedStatement mappedStatement = (MappedStatement) SystemMetaObject.forObject(statementHandler)
                .getValue(STATEMENT_HANDLER_MAPPED_STATEMENT_FIELD_NAME);
        if(SqlCommandType.UPDATE.equals(mappedStatement.getSqlCommandType())){
            Object param= statementHandler.getParameterHandler().getParameterObject();
            dbDefaultValueHandler.setDbDefaultValue(param);
            Object result = invocation.proceed();
            dbDefaultValueHandler.clearDbDefaultValue(param);
            return result;
        }
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
