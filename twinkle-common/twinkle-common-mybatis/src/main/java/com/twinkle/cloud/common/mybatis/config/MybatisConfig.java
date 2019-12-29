package com.twinkle.cloud.common.mybatis.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 初使化Mybatis审计字段自动赋值的interceptor. <br/>
 * Date:     12/22/19 4:56 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@EnableTransactionManagement
@Configuration
@ConditionalOnBean(DataSource.class)
public class MybatisConfig {
    /**
     * Paging Plugin.
     *
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

}
