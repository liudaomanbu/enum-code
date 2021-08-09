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

import javax.annotation.Resource;
import java.util.Properties;

/**
* 数据库默认值的Insert方法拦截器.
 * 在Executor拦截是为了在根据xml生成具体执行语句之前,将默认值设置进去,才能在动态字段插入方法时让默认值生效.
*
* @author caotc
* @date 2019-06-11
* @since 1.0.0
*/
@Component
@Intercepts({
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class,
                Object.class})
})
@Slf4j
public class DbDefaultValueInsertInterceptor implements Interceptor {
    @Resource
    DbDefaultValueHandler dbDefaultValueHandler;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement= (MappedStatement) invocation.getArgs()[0];
        if(SqlCommandType.INSERT.equals(mappedStatement.getSqlCommandType())){
            Object param= invocation.getArgs()[1];
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
