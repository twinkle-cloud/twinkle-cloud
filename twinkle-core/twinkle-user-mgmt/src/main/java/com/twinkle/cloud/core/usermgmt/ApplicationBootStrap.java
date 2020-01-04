package com.twinkle.cloud.core.usermgmt;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2019-06-17 17:44<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@ComponentScan(basePackages ={"com.twinkle.cloud"})
@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
@EnableMethodCache(basePackages = "com.twinkle.cloud")
@EnableCreateCacheAnnotation
public class ApplicationBootStrap {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationBootStrap.class, args);
    }
}
