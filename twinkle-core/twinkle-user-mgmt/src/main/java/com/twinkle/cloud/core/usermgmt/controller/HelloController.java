package com.twinkle.cloud.core.usermgmt.controller;

import com.twinkle.cloud.common.constant.ResultCode;
import com.twinkle.cloud.common.data.GeneralContentResult;
import com.twinkle.cloud.common.data.GeneralResult;
import com.twinkle.cloud.core.usermgmt.data.HelloRequest;
import com.twinkle.cloud.core.usermgmt.service.HelloWorldService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2019-06-17 17:46<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@RestController
@Slf4j
@Api
public class HelloController {
    @Autowired
    private HelloWorldService helloWorldService;

    @ApiOperation(value = "获取用户Token")
    @RequestMapping(value = "authsec/token/{_userName}", method = RequestMethod.POST)
    public GeneralContentResult<String> createAuthenticationToken(
            @ApiParam(value = "请求体") @RequestBody HelloRequest _request,
            @ApiParam(value = "UserName") @PathVariable(value = "_userName") String _userName ) throws Exception {
        log.info("The request body is AA");
        log.info("The request body is: {}", _request);
        log.info("The request body is: {} -> {}", _request, _userName);

        String tempContent = this.helloWorldService.sayHello(_request.getUserName());

        GeneralContentResult<String> tempResult = new GeneralContentResult<>();
        tempResult.setResultCode(ResultCode.OPERATION_SUCCESS);
        tempResult.setResultContent(tempContent);

        return tempResult;
    }

    public GeneralResult getRequestString(@PathVariable int _a, @PathVariable float _b) {

        GeneralContentResult<String> tempResult = new GeneralContentResult<>();
        tempResult.setResultCode(ResultCode.OPERATION_SUCCESS);
        tempResult.setResultContent("DDDD");

        return null;
    }

    public GeneralResult getRequestString(@PathVariable int _a) {

        GeneralContentResult<String> tempResult = new GeneralContentResult<>();
        tempResult.setResultCode(ResultCode.OPERATION_SUCCESS);
        tempResult.setResultContent("DDDD");

        return null;
    }
}
