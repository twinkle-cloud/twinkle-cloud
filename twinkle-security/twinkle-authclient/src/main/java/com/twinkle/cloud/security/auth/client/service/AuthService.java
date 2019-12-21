package com.twinkle.cloud.security.auth.client.service;

import com.twinkle.cloud.common.data.GeneralResult;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     12/21/19 10:16 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
public interface AuthService {
    /**
     * 调用签权服务，判断用户是否有权限
     *
     * @param authentication
     * @param url
     * @param method
     * @return Result
     */
    GeneralResult<Boolean> authenticate(String authentication, String url, String method);

    /**
     * 判断url是否在忽略的范围内
     * 只要是配置中的开头，即返回true
     *
     * @param url
     * @return
     */
    boolean ignoreAuthentication(String url);

    /**
     * 查看签权服务器返回结果，有权限返回true
     *
     * @param authResult
     * @return
     */
    boolean hasPermission(GeneralResult<Boolean> authResult);

    /**
     * 调用签权服务，判断用户是否有权限
     *
     * @param authentication
     * @param url
     * @param method
     * @return true/false
     */
    boolean hasPermission(String authentication, String url, String method);

    /**
     * 是否无效authentication
     *
     * @param authentication
     * @return
     */
    boolean invalidJwtAccessToken(String authentication);

    /**
     * 从认证信息中提取jwt token 对象
     *
     * @param jwtToken toke信息 header.payload.signature
     * @return Jws对象
     */
    Jws<Claims> getJwt(String jwtToken);
}
