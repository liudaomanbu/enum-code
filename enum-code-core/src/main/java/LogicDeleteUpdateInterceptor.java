import com.guahao.bhc.todo.list.service.biz.common.util.PluginUtil;
import com.guahao.bhc.todo.list.service.biz.model.BasePO;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.delete.Delete;
import net.sf.jsqlparser.statement.update.Update;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

/**
 * 给DO的insert和update操作设置固定时间字段的值
 *
 * @author caotc
 * @date 2019-06-12
 * @since 1.0.0
 */
@Intercepts({
        @Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class,
                Integer.class})
})
@Slf4j
public class LogicDeleteUpdateInterceptor implements Interceptor {
    private static final LocalDateTime DEFAULT_GMT_DELETED = LocalDateTime.of(9999, 12, 31, 23, 59, 59);

    private void setTime(@NonNull Object param, @NonNull SqlCommandType sqlCommandType) {
        if (param instanceof Collection) {
            ((Collection<?>) param).forEach(i -> setTime(i, sqlCommandType));
            return;
        }
        if (param instanceof Map) {
            ((Map<?, ?>) param).values().forEach(i -> setTime(i, sqlCommandType));
            return;
        }
        if (param instanceof BasePO) {
            ((BasePO<?>) param).setGmtModified(LocalDateTime.now());
            if (SqlCommandType.INSERT.equals(sqlCommandType)) {
                ((BasePO<?>) param).setGmtCreated(LocalDateTime.now());
                ((BasePO<?>) param).setGmtDeleted(DEFAULT_GMT_DELETED);
            }
        }
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler handler = (StatementHandler) PluginUtil.processTarget(invocation.getTarget());
        MappedStatement mappedStatement = (MappedStatement) SystemMetaObject.forObject(handler)
                .getValue("delegate.mappedStatement");
        Object param = invocation.getArgs()[1];
        BoundSql boundSql = handler.getBoundSql();
        EqualsTo equalsTo = new EqualsTo();
        equalsTo.setLeftExpression(new Column("gmt_deleted"));
        equalsTo.setRightExpression(new StringValue("9999-12-31 23:59:59"));
        if (SqlCommandType.UPDATE.equals(mappedStatement.getSqlCommandType())) {
            Update parse = (Update) CCJSqlParserUtil.parse(boundSql.getSql());
            Expression where = Optional.ofNullable(parse.getWhere())
                    .<Expression>map(s -> new AndExpression(s, equalsTo))
                    .orElse(equalsTo);
            parse.setWhere(where);
            SystemMetaObject.forObject(boundSql).setValue("sql", parse.toString());

        }
        if (SqlCommandType.DELETE.equals(mappedStatement.getSqlCommandType())) {
            Delete parse = (Delete) CCJSqlParserUtil.parse(boundSql.getSql());
            Update update = (Update) CCJSqlParserUtil.parse("UPDATE " + parse.getTable().toString() + " SET gmt_deleted=now(),gmt_modified=now()");
            Expression where = Optional.ofNullable(parse.getWhere())
                    .<Expression>map(s -> new AndExpression(s, equalsTo))
                    .orElse(equalsTo);
            update.setWhere(where);
            SystemMetaObject.forObject(boundSql).setValue("sql", update.toString());
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
