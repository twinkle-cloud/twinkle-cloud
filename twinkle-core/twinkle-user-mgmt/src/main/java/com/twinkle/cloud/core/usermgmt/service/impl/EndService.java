package com.twinkle.cloud.core.usermgmt.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.twinkle.cloud.core.usermgmt.controller.TwinkleRestController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2019-07-22 17:57<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@Slf4j
@Service
public class EndService {
    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;
    @Autowired
    private TwinkleRestController twinkleRestController;

    @PostConstruct
    public void init() throws NoSuchMethodException {
//        log.info("Going to load customized rest controller.");
        this.addRestEndPoint();
    }
    private void addRestEndPoint() throws NoSuchMethodException{
        requestMappingHandlerMapping.registerMapping(
                RequestMappingInfo.paths("/twinkle/{_id}/name/{_name}")
                        .methods()
                        .methods(RequestMethod.POST)
                        .produces(MediaType.APPLICATION_JSON_VALUE)
                        .build(),
                twinkleRestController,
                TwinkleRestController.class.getDeclaredMethod(
                        "handerMethodWithParamsAndPathVars",
                        Map.class, Map.class, JSONObject.class
                )
        );

        requestMappingHandlerMapping.registerMapping(
                RequestMappingInfo.paths("/twinkle/user/{_userId}")
                        .methods()
                        .methods(RequestMethod.POST)
                        .produces(MediaType.APPLICATION_JSON_VALUE)
                        .build(),
                twinkleRestController,
                TwinkleRestController.class.getDeclaredMethod(
                        "handerMethodWithParamsAndPathVars",
                        Map.class, Map.class, JSONObject.class
                )
        );
    }
}
