package com.twinkle.cloud.common.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.twinkle.cloud.common.context.UserContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     12/21/19 3:22 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@Slf4j
public class UserInterceptor implements HandlerInterceptor {
    /**
     * 服务间调用token用户信息,格式为json
     * {
     * "user_name":"必须有"
     * "自定义key:"value"
     * }
     */
    public static final String X_CLIENT_TOKEN_USER = "x-client-token-user";
    /**
     * 服务间调用的认证token
     */
    public static final String X_CLIENT_TOKEN = "x-client-token";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //从网关获取并校验,通过校验就可信任x-client-token-user中的信息
        checkToken(request.getHeader(X_CLIENT_TOKEN));
        String userInfoString = StringUtils.defaultIfBlank(request.getHeader(X_CLIENT_TOKEN_USER), "{}");
        UserContextHolder.getInstance().setContext(new ObjectMapper().readValue(userInfoString, Map.class));
        return true;
    }

    private void checkToken(String token) {
        //TODO 从网关获取并校验,通过校验就可信任x-client-token-user中的信息
        log.debug("//TODO 校验token:{}", token);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        UserContextHolder.getInstance().clear();
    }
}
