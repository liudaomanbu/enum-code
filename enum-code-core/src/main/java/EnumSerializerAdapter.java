import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import lombok.Value;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * @author caotc
 * @date 2019-06-12
 * @since 1.0.0
 */
@Value
public class EnumSerializerAdapter implements ObjectSerializer {
    @SuppressWarnings("unchecked")
    @Override
    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
        Object result = EnumSimpleValueUtils.toSimpleValue((Enum) object);
        serializer.write(result);
    }
}
