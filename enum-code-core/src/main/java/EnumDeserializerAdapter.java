import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Type;

/**
 * @author caotc
 * @date 2019-06-12
 * @since 1.0.0
 */
@Value
@Slf4j
public class EnumDeserializerAdapter implements ObjectDeserializer {
//    ConversionService mvcConversionService;

    @SuppressWarnings("unchecked")
    @Override
    public <T> T deserialze(DefaultJSONParser parser, Type type, Object fieldName) {
        //return mvcConversionService.convert(parser.parse(), (Class<T>) type);
        return (T) EnumSimpleValueUtils.valueOf((Class) type, parser.parse());
    }

    @Override
    public int getFastMatchToken() {
        return 0;
    }
}
