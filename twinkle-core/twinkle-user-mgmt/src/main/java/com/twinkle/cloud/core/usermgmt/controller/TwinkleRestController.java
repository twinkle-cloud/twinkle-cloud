package com.twinkle.cloud.core.usermgmt.controller;

import com.alibaba.fastjson.JSONObject;
import com.twinkle.cloud.common.constant.ResultCode;
import com.twinkle.cloud.common.data.GeneralContentResult;
import com.twinkle.cloud.core.usermgmt.service.HelloWorldService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2019-07-22 17:59<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@Slf4j
@RestController
public class TwinkleRestController {
    @Autowired
    private HelloWorldService helloWorldService;


    public GeneralContentResult<Object> handerMethodWithParams(@RequestParam Map<String, String> _reqParam, @RequestBody JSONObject _reqBody){
        GeneralContentResult<Object> tempResult = new GeneralContentResult<>();

        return tempResult;
    }


    public GeneralContentResult<Object> handerMethodWithParamsAndPathVars(@PathVariable Map<String, String> _reqPathVars,
                                                                          @RequestParam Map<String, String> _reqParam,
                                                                          @RequestBody JSONObject _reqBody){
        GeneralContentResult<Object> tempResult = new GeneralContentResult<>();
        log.info("Get into the method with parameters: {} and path var: {}", _reqParam, _reqPathVars);

        String tempContent = this.helloWorldService.sayHello(_reqParam.toString());
        tempResult.setResultCode(ResultCode.OPERATION_SUCCESS);
        tempResult.setResultContent(tempContent);
        return tempResult;
    }
}
