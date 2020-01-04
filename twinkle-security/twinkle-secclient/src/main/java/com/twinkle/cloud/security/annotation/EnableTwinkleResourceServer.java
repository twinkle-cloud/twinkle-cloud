package com.twinkle.cloud.security.annotation;

import com.twinkle.cloud.security.component.ResourceServerAutoConfiguration;
import com.twinkle.cloud.security.component.MySecurityBeanDefinitionRegistrar;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import java.lang.annotation.*;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     1/2/20 9:53 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@Documented
@Inherited
@EnableResourceServer
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Import({ResourceServerAutoConfiguration.class, MySecurityBeanDefinitionRegistrar.class})
public @interface EnableTwinkleResourceServer {

}
