package com.twinkle.cloud.authserver.service;

import com.twinkle.cloud.authserver.data.dto.JwtAuthenticationRequest;

/**
 * ClassName: AuthService <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: Sep 30, 2016 2:56:31 PM <br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
public interface AuthService {
    /**
     * Login support.
     * @param authenticationRequest
     * @return
     * @throws Exception
     */
    String login(JwtAuthenticationRequest authenticationRequest) throws Exception;

    /**
     * Refresh token.
     * @param oldToken
     * @return
     * @throws Exception
     */
    String refresh(String oldToken) throws Exception;

    /**
     * Validate the token.
     * @param token
     * @throws Exception
     */
    void validate(String token) throws Exception;
}
