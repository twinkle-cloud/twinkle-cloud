package com.twinkle.cloud.authserver.util.client;

import com.twinkle.cloud.authserver.config.KeyConfiguration;
import com.twinkle.cloud.common.jwt.IJwtInfo;
import com.twinkle.cloud.common.jwt.utils.JwtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName: ClientTokenUtil <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: Sep 30, 2016 2:56:31 PM <br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@Configuration
public class ClientTokenUtil {
    @Value("${client.expire}")
    private int expire;
    @Autowired
    private KeyConfiguration keyConfiguration;

    public String generateToken(IJwtInfo jwtInfo) throws Exception {
        return JwtHelper.generateToken(jwtInfo, keyConfiguration.getServicePriKey(), expire);
    }

    public IJwtInfo getInfoFromToken(String token) throws Exception {
        return JwtHelper.getInfoFromToken(token, keyConfiguration.getServicePubKey());
    }

}
