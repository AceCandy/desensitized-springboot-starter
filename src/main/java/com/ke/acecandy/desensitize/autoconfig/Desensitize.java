package com.ke.acecandy.desensitize.autoconfig;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ke.acecandy.desensitize.impl.DesensitizeEnum;
import com.ke.acecandy.desensitize.impl.DesensitizeSerializer;

/**
 * 数据脱敏注解
 *
 * @author tangningzhu
 * @date 2021/9/26
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@JacksonAnnotationsInside
@JsonSerialize(using = DesensitizeSerializer.class)
public @interface Desensitize {

    /**
     * 脱敏类型
     *
     * @return {@link DesensitizeEnum}
     */
    DesensitizeEnum value();

    /**
     * 类型为custom使用 脱敏的开始index
     *
     * @return int
     */
    int start() default 0;

    /**
     * 类型为custom使用 脱敏的结束index
     *
     * @return int
     */
    int end() default 0;
}