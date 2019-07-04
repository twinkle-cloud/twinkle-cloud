package com.twinkle.cloud.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     11/23/18 7:29 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // For example: Use only Http Basic and not form login.
        http
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .antMatcher("/**").authorizeRequests()
                .antMatchers("/webjars/**", "/", "/**/*.html", "/assets/**", "/css/**", "/js/**", "/index.html", "/home.html", "/login.html", "/uaa/**")
                .permitAll()//
                .antMatchers("/*/webjars/**").permitAll()
                .antMatchers("/*/swagger-resources/**").permitAll()
                .antMatchers("/*/v2/**").permitAll()
                .antMatchers("/*/noauth/**").permitAll()
                .antMatchers("/*/swagger-ui.html/**").permitAll()
                .antMatchers("/*/swagger-ui.html#/**").permitAll()
                .antMatchers("/swagger**/**").permitAll()
                .antMatchers("/*/*/noauth/**").permitAll()
                .antMatchers("/*/authsec/**").permitAll()
                .antMatchers("/*/*/authsec/**").permitAll()
                .antMatchers("/*/*/favicon.ico/**").permitAll()
                .antMatchers("/uaa/logout").permitAll()
                .anyRequest().authenticated()//
                .and()//
                .httpBasic().disable();
    }
}

