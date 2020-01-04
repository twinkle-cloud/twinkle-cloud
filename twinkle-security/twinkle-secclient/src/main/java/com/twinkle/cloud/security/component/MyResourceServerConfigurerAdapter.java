package com.twinkle.cloud.security.component;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.UserAuthenticationConverter;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.client.RestTemplate;

/**
 * Function: 1. 支持remoteTokenServices 负载均衡. <br/>
 *           2. 支持 获取用户全部信息
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     1/2/20 10:02 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@Slf4j
public class MyResourceServerConfigurerAdapter extends ResourceServerConfigurerAdapter {
    @Autowired
    protected ResourceAuthExceptionEntryPoint resourceAuthExceptionEntryPoint;
    @Autowired
    protected RemoteTokenServices remoteTokenServices;
    @Autowired
    private AccessDeniedHandler accessDeniedHandler;
    @Autowired
    private PermitAllUrlConfiguration permitAllUrl;
    @Autowired
    private RestTemplate lbRestTemplate;

    /**
     * 默认的配置，对外暴露
     *
     * @param _httpSecurity
     */
    @Override
    @SneakyThrows
    public void configure(HttpSecurity _httpSecurity) {
        //允许使用iframe 嵌套，避免swagger-ui 不被加载的问题
        _httpSecurity.headers().frameOptions().disable();
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>
                .ExpressionInterceptUrlRegistry registry = _httpSecurity
                .authorizeRequests();
        this.permitAllUrl.getUrls()
                .forEach(url -> registry.antMatchers(url).permitAll());
        registry.anyRequest().authenticated()
                .and().csrf().disable();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer _resources) {
        DefaultAccessTokenConverter accessTokenConverter = new DefaultAccessTokenConverter();
        UserAuthenticationConverter userTokenConverter = new MyUserAuthenticationConverter();
        accessTokenConverter.setUserTokenConverter(userTokenConverter);

        this.remoteTokenServices.setRestTemplate(this.lbRestTemplate);
        this.remoteTokenServices.setAccessTokenConverter(accessTokenConverter);
        _resources.authenticationEntryPoint(this.resourceAuthExceptionEntryPoint)
                .accessDeniedHandler(this.accessDeniedHandler)
                .tokenServices(this.remoteTokenServices);
    }
}
