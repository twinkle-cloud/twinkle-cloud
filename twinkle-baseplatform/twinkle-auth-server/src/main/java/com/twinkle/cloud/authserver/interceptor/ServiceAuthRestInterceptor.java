package com.twinkle.cloud.authserver.interceptor;

import com.twinkle.cloud.authserver.config.ClientConfiguration;
import com.twinkle.cloud.authserver.service.AuthClientService;
import com.twinkle.cloud.authserver.util.client.ClientTokenUtil;
import com.twinkle.cloud.common.auth.exception.ClientForbiddenException;
import com.twinkle.cloud.common.jwt.IJwtInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ClassName: ServiceAuthRestInterceptor <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: Sep 30, 2016 2:56:31 PM <br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@SuppressWarnings("ALL")
@Slf4j
public class ServiceAuthRestInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private ClientTokenUtil clientTokenUtil;
    @Autowired
    private AuthClientService authClientService;
    @Autowired
    private ClientConfiguration clientConfiguration;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        String token = request.getHeader(clientConfiguration.getClientTokenHeader());
        IJwtInfo infoFromToken = clientTokenUtil.getInfoFromToken(token);
        String uniqueName = infoFromToken.getUniqueName();
        for(String client: authClientService.getAllowedClient(clientConfiguration.getClientId())){
            if(client.equals(uniqueName)){
                return super.preHandle(request, response, handler);
            }
        }
        throw new ClientForbiddenException("Client is Forbidden!");
    }
}
