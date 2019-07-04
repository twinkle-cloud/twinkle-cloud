package com.twinkle.cloud.authserver.controller;

import com.twinkle.cloud.authserver.config.KeyConfiguration;
import com.twinkle.cloud.authserver.service.AuthClientService;
import com.twinkle.cloud.common.constant.ResultCode;
import com.twinkle.cloud.common.data.GeneralContentResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ace on 2017/9/10.
 */
@RestController
@RequestMapping("client")
public class ClientController {
    @Autowired
    private AuthClientService authClientService;
    @Autowired
    private KeyConfiguration keyConfiguration;

    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public GeneralContentResult<String> getAccessToken(String clientId, String secret) throws Exception {
        return new GeneralContentResult<>(ResultCode.OPERATION_SUCCESS, authClientService.apply(clientId, secret));
    }

    @RequestMapping(value = "/myClient")
    public GeneralContentResult<String> getAllowedClient(String serviceId, String secret) {
        return new GeneralContentResult(ResultCode.OPERATION_SUCCESS, authClientService.getAllowedClient(serviceId, secret));
    }

    @RequestMapping(value = "/servicePubKey", method = RequestMethod.POST)
    public GeneralContentResult<byte[]> getServicePublicKey(@RequestParam("clientId") String clientId, @RequestParam("secret") String secret) throws Exception {
        authClientService.validate(clientId, secret);
        return new GeneralContentResult(ResultCode.OPERATION_SUCCESS, keyConfiguration.getServicePubKey());
    }

    @RequestMapping(value = "/userPubKey", method = RequestMethod.POST)
    public GeneralContentResult<byte[]> getUserPublicKey(@RequestParam("clientId") String clientId, @RequestParam("secret") String secret) throws Exception {
        authClientService.validate(clientId, secret);
        return new GeneralContentResult(ResultCode.OPERATION_SUCCESS, keyConfiguration.getUserPubKey());
    }


}
