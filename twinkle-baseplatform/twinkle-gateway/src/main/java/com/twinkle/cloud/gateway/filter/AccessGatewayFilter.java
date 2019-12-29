package com.twinkle.cloud.gateway.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twinkle.cloud.gateway.service.PermissionService;
import com.twinkle.cloud.security.auth.client.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 Do the authentication with the request URL. <br/>
 * Date:     12/22/19 5:12 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@Configuration
@ComponentScan(basePackages = "com.twinkle.cloud.security.auth.client")
@Slf4j
public class AccessGatewayFilter implements GlobalFilter {
    private static final String X_CLIENT_TOKEN_USER = "x-client-token-user";
    private static final String X_CLIENT_TOKEN = "x-client-token";

    /**
     * 由authentication-client模块提供签权的feign客户端
     */
    @Autowired
    private AuthService authService;

    @Autowired
    private PermissionService permissionService;

    /**
     * 1.首先网关检查token是否有效，无效直接返回401，不调用签权服务
     * 2.调用签权服务器看是否对该请求有权限，有权限进入下一个filter，没有权限返回401
     *
     * @param _exchange
     * @param _chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange _exchange, GatewayFilterChain _chain) {
        ServerHttpRequest tempRequest = _exchange.getRequest();
        String tempAuthen = tempRequest.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        String tempMethod = tempRequest.getMethodValue();
        String tempURL = tempRequest.getPath().value();
        log.debug("URL:{},method:{},headers:{}", tempURL, tempMethod, tempRequest.getHeaders());
        //不需要网关签权的url
        if (this.authService.ignoreAuthentication(tempURL)) {
            return _chain.filter(_exchange);
        }

        //调用签权服务看用户是否有权限，若有权限进入下一个filter
        if (this.permissionService.permission(tempAuthen, tempURL, tempMethod)) {
            ServerHttpRequest.Builder builder = tempRequest.mutate();
            //TODO 转发的请求都加上服务间认证token
            builder.header(X_CLIENT_TOKEN, "TODO zhoutaoo添加服务间简单认证");
            //将jwt token中的用户信息传给服务
            builder.header(X_CLIENT_TOKEN_USER, getUserToken(tempAuthen));
            return _chain.filter(_exchange.mutate().request(builder.build()).build());
        }
        return unauthorized(_exchange);
    }

    /**
     * 提取jwt token中的数据，转为json
     *
     * @param _authen
     * @return
     */
    private String getUserToken(String _authen) {
        String token = "{}";
        try {
            token = new ObjectMapper().writeValueAsString(this.authService.getJwt(_authen).getBody());
            return token;
        } catch (JsonProcessingException e) {
            log.error("Token json error:{}", e.getMessage());
        }
        return token;
    }

    /**
     * 网关拒绝，返回401
     *
     * @param
     */
    private Mono<Void> unauthorized(ServerWebExchange serverWebExchange) {
        serverWebExchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        DataBuffer buffer = serverWebExchange.getResponse()
                .bufferFactory().wrap(HttpStatus.UNAUTHORIZED.getReasonPhrase().getBytes());
        return serverWebExchange.getResponse().writeWith(Flux.just(buffer));
    }
}
