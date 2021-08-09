import com.google.common.base.Strings;
import com.google.common.collect.ImmutableMap;
import com.guahao.bhc.data.service.share.annotation.EnumDefault;
import com.guahao.bhc.data.service.share.constant.TimeConstant;
import com.guahao.convention.Validated;
import com.guahao.convention.util.Preconditions;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 对于数据库默认值注解实现类
 *
 * @author caotc
 * @date 2019-06-05
 * @see DbDefaultValue
 * @see EnumDefault
 * @since 1.0.0
 */
@Component
@Slf4j
@Validated
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class DbDefaultValueHandler {
    private static final ImmutableMap<Class<?>, Object> CLASS_TO_DB_DEFAULT_VALUES = ImmutableMap.<Class<?>, Object>builder()
            .put(byte.class, (byte) 0)
            .put(Byte.class, (byte) 0)
            .put(short.class, (short) 0)
            .put(Short.class, (short) 0)
            .put(int.class, 0)
            .put(Integer.class, 0)
            .put(long.class, 0L)
            .put(Long.class, 0L)
            .put(char.class, Character.MIN_VALUE)
            .put(Character.class, Character.MIN_VALUE)
            .put(float.class, 0f)
            .put(Float.class, 0f)
            .put(double.class, 0d)
            .put(Double.class, 0d)
            .put(String.class, "")
            .put(LocalDate.class, TimeConstant.DEFAULT_LOCAL_DATE)
            .put(LocalDateTime.class, TimeConstant.DEFAULT_LOCAL_DATE_TIME)
            .build();
    ConversionService mvcConversionService;

    public void setDbDefaultValue(@NonNull Object object) {
        if (object instanceof Collection) {
            ((Collection<?>) object).forEach(this::setDbDefaultValue);
            return;
        }
        if (object instanceof Map) {
            ((Map<?, ?>) object).values().forEach(this::setDbDefaultValue);
            return;
        }
        ReflectionUtils.doWithFields(object.getClass(), field -> {
            ReflectionUtils.makeAccessible(field);
            Object value = ReflectionUtils.getField(field, object);
            if (Objects.isNull(value)) {
                Object dbDefaultValue = getDbDefaultValue(field);
                ReflectionUtils.setField(field, object, dbDefaultValue);
            }
        }, field -> field.isAnnotationPresent(DbDefaultValue.class));
    }

    public void clearDbDefaultValue(@NonNull Object object) {
        if (object instanceof Collection) {
            ((Collection<?>) object).forEach(this::clearDbDefaultValue);
            return;
        }
        if (object instanceof Map) {
            ((Map<?, ?>) object).values().forEach(this::clearDbDefaultValue);
            return;
        }
        ReflectionUtils.doWithFields(object.getClass(), field -> {
            ReflectionUtils.makeAccessible(field);
            Object value = ReflectionUtils.getField(field, object);
            if (Objects.nonNull(value) && !(value instanceof Enum)) {
                Object dbDefaultValue = getDbDefaultValue(field);
                if (Objects.equals(value, dbDefaultValue)) {
                    ReflectionUtils.setField(field, object, null);
                }
            }
        }, field -> field.isAnnotationPresent(DbDefaultValue.class));
    }

    public <T extends Enum<T>> Object getDbDefaultValue(@NonNull Field field) {
        ReflectionUtils.makeAccessible(field);
        DbDefaultValue annotation = field.getAnnotation(DbDefaultValue.class);
        Object dbDefaultValue = null;
        //没有填写value时
        if (Strings.isNullOrEmpty(annotation.value())) {
            //枚举类型属性自动寻找枚举中注解标注的默认值
            if (field.getType().isEnum()) {
                @SuppressWarnings("unchecked")
                Optional<Enum<T>> enumDefault = findEnumDefault((Class<Enum<T>>) field.getType());
                if (enumDefault.isPresent()) {
                    dbDefaultValue = enumDefault.get();
                }
            }
            //类型在内置map中有定义的,以map中的默认值为准
            if (CLASS_TO_DB_DEFAULT_VALUES.containsKey(field.getType())) {
                dbDefaultValue = CLASS_TO_DB_DEFAULT_VALUES.get(field.getType());
            }
        } else {
            //填写了value时将value以Spring内置转换api从String转换为属性类型
            dbDefaultValue = annotation.value();
            //如果有中转类型,先转为中转类型
            for (Class<?> transferClass : annotation.transferClasses()) {
                dbDefaultValue = mvcConversionService.convert(dbDefaultValue, transferClass);
            }
            dbDefaultValue = mvcConversionService.convert(dbDefaultValue, field.getType());
        }
        return dbDefaultValue;
    }

    @SuppressWarnings("unchecked")
    private <T extends Enum<T>> Optional<Enum<T>> findEnumDefault(@NonNull Class<Enum<T>> enumType) {
        Set<Field> enumDefaults = Arrays.stream(enumType.getDeclaredFields()).filter(field -> field.isAnnotationPresent(EnumDefault.class)).collect(Collectors.toSet());
        Preconditions.checkArgument(enumDefaults.size() <= 1, "%s's EnumDefault is not only", enumType);
        return enumDefaults.stream()
                .peek(ReflectionUtils::makeAccessible).map(field -> {
                    try {
                        return field.get(null);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }).map(value -> (Enum<T>) value).findAny();
    }
}
