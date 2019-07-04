package com.twinkle.cloud.common.auth.runner;

import com.twinkle.cloud.common.auth.config.ServiceAuthConfig;
import com.twinkle.cloud.common.auth.config.UserAuthConfig;
import com.twinkle.cloud.common.auth.feign.ServiceAuthFeign;
import com.twinkle.cloud.common.constant.ResultCode;
import com.twinkle.cloud.common.data.GeneralContentResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * 监听完成时触发
 *
 * @author ace
 * @create 2017/11/29.
 */
@Configuration
@Slf4j
public class AuthClientRunner implements CommandLineRunner {

    @Autowired
    private ServiceAuthConfig serviceAuthConfig;
    @Autowired
    private UserAuthConfig userAuthConfig;
    @Autowired
    private ServiceAuthFeign serviceAuthFeign;

    @Override
    public void run(String... args) throws Exception {
        log.info("初始化加载用户pubKey");
        try {
            refreshUserPubKey();
        }catch(Exception e){
            log.error("初始化加载用户pubKey失败,1分钟后自动重试!",e);
        }
        log.info("初始化加载客户pubKey");
        try {
            refreshServicePubKey();
        }catch(Exception e){
            log.error("初始化加载客户pubKey失败,1分钟后自动重试!",e);
        }
    }
    @Scheduled(cron = "0 0/1 * * * ?")
    public void refreshUserPubKey(){
        GeneralContentResult<byte[]> resp = serviceAuthFeign.getUserPublicKey(serviceAuthConfig.getClientId(), serviceAuthConfig.getClientSecret());
        if (resp.getResultCode().equals(ResultCode.OPERATION_SUCCESS)) {
            this.userAuthConfig.setPubKeyByte(resp.getResultContent());
        }
    }
    @Scheduled(cron = "0 0/1 * * * ?")
    public void refreshServicePubKey(){
        GeneralContentResult<byte[]> resp = serviceAuthFeign.getServicePublicKey(serviceAuthConfig.getClientId(), serviceAuthConfig.getClientSecret());
        if (resp.getResultCode().equals(ResultCode.OPERATION_SUCCESS)) {
            this.serviceAuthConfig.setPubKeyByte(resp.getResultContent());
        }
    }

}