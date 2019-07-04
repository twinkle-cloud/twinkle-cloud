package com.twinkle.cloud.common.auth.feign;

import com.twinkle.cloud.common.data.GeneralContentResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by ace on 2017/9/15.
 */
@FeignClient(value = "${auth.serviceId}",configuration = {})
public interface ServiceAuthFeign {
    @RequestMapping(value = "/client/myClient")
    GeneralContentResult<List<String>> getAllowedClient(@RequestParam("serviceId") String serviceId, @RequestParam("secret") String secret);
    @RequestMapping(value = "/client/token",method = RequestMethod.POST)
    GeneralContentResult getAccessToken(@RequestParam("clientId") String clientId, @RequestParam("secret") String secret);
    @RequestMapping(value = "/client/servicePubKey",method = RequestMethod.POST)
    GeneralContentResult<byte[]> getServicePublicKey(@RequestParam("clientId") String clientId, @RequestParam("secret") String secret);
    @RequestMapping(value = "/client/userPubKey",method = RequestMethod.POST)
    GeneralContentResult<byte[]> getUserPublicKey(@RequestParam("clientId") String clientId, @RequestParam("secret") String secret);

}
