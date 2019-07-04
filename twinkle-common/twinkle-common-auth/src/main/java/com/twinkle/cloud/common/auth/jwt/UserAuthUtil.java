package com.twinkle.cloud.common.auth.jwt;

import com.twinkle.cloud.common.auth.config.UserAuthConfig;
import com.twinkle.cloud.common.auth.exception.UserTokenException;
import com.twinkle.cloud.common.jwt.IJwtInfo;
import com.twinkle.cloud.common.jwt.utils.JwtHelper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * Created by ace on 2017/9/15.
 */
@Configuration
public class UserAuthUtil {
    @Autowired
    private UserAuthConfig userAuthConfig;

    public IJwtInfo getInfoFromToken(String token) throws Exception {
        try {
            return JwtHelper.getInfoFromToken(token, userAuthConfig.getPubKeyByte());
        } catch (ExpiredJwtException ex) {
            throw new UserTokenException("User token expired!");
        } catch (SignatureException ex) {
            throw new UserTokenException("User token signature com.twinkle.cloud.common.asm.error!");
        } catch (IllegalArgumentException ex) {
            throw new UserTokenException("User token is null or empty!");
        }
    }
}
