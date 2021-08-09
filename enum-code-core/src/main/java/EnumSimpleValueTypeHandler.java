import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

/**
 * 基于EnumSimpleValue注解的枚举TypeHandler
 *
 * @author caotc
 * @date 2018-10-09
 * @see org.apache.ibatis.type.BaseTypeHandler
 * @see com.guahao.convention.EnumSimpleValue
 * @since 1.0.8
 **/
public final class EnumSimpleValueTypeHandler<E extends Enum<E>> extends
        BaseTypeHandler<E> {

    private final Class<E> type;

    public EnumSimpleValueTypeHandler(Class<E> type) {
        if (Objects.isNull(type)) {
            throw new IllegalArgumentException("type can't be null");
        }
        this.type = type;
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType)
            throws SQLException {
        Object value = EnumSimpleValueUtils.toSimpleValue(parameter);
        if (Objects.isNull(jdbcType)) {
            ps.setObject(i, value);
        } else {
            ps.setObject(i, value, jdbcType.TYPE_CODE);
        }
    }

    @Override
    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return EnumSimpleValueUtils.valueOf(type, rs.getObject(columnName));
    }

    @Override
    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return EnumSimpleValueUtils.valueOf(type, rs.getObject(columnIndex));
    }

    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return EnumSimpleValueUtils.valueOf(type, cs.getObject(columnIndex));
    }
}
