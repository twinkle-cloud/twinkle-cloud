package com.twinkle.cloud.security.handler;

import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.util.CollectionUtils;

/**
 * Function: 认证成功事件处理器. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     1/3/20 4:30 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
public abstract class AbstractAuthenticationSuccessEventHandler implements ApplicationListener<AuthenticationSuccessEvent> {
    /**
     * Handle an application event.
     *
     * @param event the event to respond to
     */
    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        Authentication authentication = (Authentication) event.getSource();
        if (!CollectionUtils.isEmpty(authentication.getAuthorities())) {
            handle(authentication);
        }
    }

    /**
     * 处理登录成功方法
     * <p>
     * 获取到登录的authentication 对象
     *
     * @param authentication 登录对象
     */
    public abstract void handle(Authentication authentication);

}
