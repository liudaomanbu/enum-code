package org.caotc.code.support.mybatis;

import lombok.Value;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.caotc.code.Dictionary;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

/**
 * @author caotc
 * @date 2021-08-30
 * @implSpec
 * @implNote
 * @since 1.0.0
 **/
@Value
@MappedTypes(value = {Dictionary.class})
public class EnumerableTypeHandler extends BaseTypeHandler<Dictionary<?, ?>> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Dictionary<?, ?> parameter,
                                    JdbcType jdbcType)
            throws SQLException {
        if (Objects.isNull(jdbcType)) {
            ps.setObject(i, parameter);
        } else {
            ps.setObject(i, parameter, jdbcType.TYPE_CODE);
        }
    }

    @Override
    public Dictionary<?, ?> getNullableResult(ResultSet rs, String columnName)
            throws SQLException {
        return null;
    }

    @Override
    public Dictionary<?, ?> getNullableResult(ResultSet rs, int columnIndex)
            throws SQLException {
        return null;
    }

    @Override
    public Dictionary<?, ?> getNullableResult(CallableStatement cs, int columnIndex)
            throws SQLException {
        return null;
    }

}
