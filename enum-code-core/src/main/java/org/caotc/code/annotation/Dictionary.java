package org.caotc.code.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {TYPE})
public @interface Dictionary {
    /**
     * 专用于枚举类型,可注解在枚举属性和get方法上,以其值作为枚举的对应值
     *
     * @author caotc
     * @date 2021-08-01
     * @since 1.0.0
     **/
    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    @Target(value = {FIELD, METHOD})
    @interface Code {
    }

    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    @Target(value = {FIELD, METHOD})
    @interface Name {
    }

    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    @Target(value = {FIELD, METHOD})
    @interface Description {
    }
}
