package com.twinkle.cloud.security.authorization.granter;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

/**
 * Function: 手机验证码登陆Token认证类. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     12/21/19 6:33 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
public class MobileAuthenticationToken extends UsernamePasswordAuthenticationToken {

    public MobileAuthenticationToken(Authentication authenticationToken) {
        super(authenticationToken.getPrincipal(), authenticationToken.getCredentials());
    }
}
