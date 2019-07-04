package com.twinkle.cloud.core.usermgmt.controller;

import com.twinkle.cloud.common.constant.ResultCode;
import com.twinkle.cloud.common.data.GeneralContentResult;
import com.twinkle.cloud.core.usermgmt.data.HelloRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
public class Hello2Controller {
    @ApiOperation(value = "获取用户List")
    @RequestMapping(value = "authsec/user", method = RequestMethod.POST)
    public GeneralContentResult<String> createAuthenticationToken(
            @RequestBody HelloRequest _request) throws Exception {
        log.info("The request user is: {}", _request);
        return new GeneralContentResult<>(ResultCode.OPERATION_SUCCESS, "EEEFF");
    }
}
