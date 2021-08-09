import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;

/**
 * 该注解标注的属性会在数据库insert操作前进行默认值设置
 *
 * @author caotc
 * @date 2019-02-20
 * @since 1.0.0
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {FIELD})
public @interface DbDefaultValue {
    String value() default "";

    /**
     * 中转类,当该属性有值时,转换默认值不是直接将String的value()值转为属性类型,而是按照顺序,先转为中转类型,最后转为属性类型.
     * 使用场景:例如想要写{@link com.guahao.convention.EnumSimpleValue}的值,转为属性枚举类型,
     * 但是{@link com.guahao.convention.EnumSimpleValue}注解属性不是String,而是其他如int类型,那么需要先经过中转类.
     */
    Class<?>[] transferClasses() default {};
}
