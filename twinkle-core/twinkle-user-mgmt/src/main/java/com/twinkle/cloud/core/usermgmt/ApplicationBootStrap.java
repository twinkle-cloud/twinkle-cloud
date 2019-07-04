package com.twinkle.cloud.core.usermgmt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2019-06-17 17:44<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@SpringBootApplication
//@EnableDiscoveryClient
//@EnableFeignClients
public class ApplicationBootStrap {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationBootStrap.class, args);
    }
//    @Bean
//    public StartupRunner startupRunner(){
//        return new StartupRunner();
//    }
//    @Bean
//    public SecondRunner secondRunner(){
//        return new SecondRunner();
//    }

}
