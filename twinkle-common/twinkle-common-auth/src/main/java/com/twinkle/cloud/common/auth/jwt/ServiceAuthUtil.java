
package com.twinkle.cloud.common.auth.jwt;

import com.twinkle.cloud.common.auth.config.ServiceAuthConfig;
import com.twinkle.cloud.common.auth.feign.ServiceAuthFeign;
import com.twinkle.cloud.common.auth.exception.ClientTokenException;
import com.twinkle.cloud.common.constant.ResultCode;
import com.twinkle.cloud.common.data.GeneralContentResult;
import com.twinkle.cloud.common.jwt.IJwtInfo;
import com.twinkle.cloud.common.jwt.utils.JwtHelper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

/**
 * Created by ace on 2017/9/15.
 */
@Configuration
@Slf4j
@EnableScheduling
public class ServiceAuthUtil{
    @Autowired
    private ServiceAuthConfig serviceAuthConfig;

    @Autowired
    private ServiceAuthFeign serviceAuthFeign;

    private List<String> allowedClient;
    private String clientToken;


    public IJwtInfo getInfoFromToken(String token) throws Exception {
        try {
            return JwtHelper.getInfoFromToken(token, serviceAuthConfig.getPubKeyByte());
        } catch (ExpiredJwtException ex) {
            throw new ClientTokenException("Client token expired!");
        } catch (SignatureException ex) {
            throw new ClientTokenException("Client token signature com.twinkle.cloud.common.asm.error!");
        } catch (IllegalArgumentException ex) {
            throw new ClientTokenException("Client token is null or empty!");
        }
    }

    @Scheduled(cron = "0/30 * * * * ?")
    public void refreshAllowedClient() {
        log.debug("refresh allowedClient.....");
        GeneralContentResult<List<String>> resp = serviceAuthFeign.getAllowedClient(serviceAuthConfig.getClientId(), serviceAuthConfig.getClientSecret());
        if (resp.getResultCode().equals(ResultCode.OPERATION_SUCCESS)) {
            this.allowedClient = resp.getResultContent();
        }
    }

    @Scheduled(cron = "0 0/10 * * * ?")
    public void refreshClientToken() {
        log.debug("refresh client token.....");
        GeneralContentResult<String> resp = serviceAuthFeign.getAccessToken(serviceAuthConfig.getClientId(), serviceAuthConfig.getClientSecret());
        if (resp.getResultCode().equals(ResultCode.OPERATION_SUCCESS)) {
            this.clientToken = resp.getResultContent();
        }
    }


    public String getClientToken() {
        if (this.clientToken == null) {
            this.refreshClientToken();
        }
        return clientToken;
    }

    public List<String> getAllowedClient() {
        if (this.allowedClient == null) {
            this.refreshAllowedClient();
        }
        return allowedClient;
    }
}