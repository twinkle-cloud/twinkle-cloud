package com.twinkle.cloud.security.annotation;

import java.lang.annotation.*;

/**
 * Function: 服务调用不鉴权注解. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     1/3/20 4:20 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NoAuth {
    /**
     * 是否AOP统一处理
     *
     * @return false, true
     */
    boolean value() default true;

    /**
     * 需要特殊判空的字段(预留)
     *
     * @return {}
     */
    String[] field() default {};
}
