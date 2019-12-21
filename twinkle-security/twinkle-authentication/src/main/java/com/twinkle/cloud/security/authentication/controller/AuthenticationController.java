package com.twinkle.cloud.security.authentication.controller;

import com.twinkle.cloud.common.data.GeneralResult;
import com.twinkle.cloud.security.authentication.data.HttpServletRequestAuthWrapper;
import com.twinkle.cloud.security.authentication.service.AuthenticationService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     12/21/19 4:39 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@RestController
@Api("auth")
@Slf4j
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @ApiOperation(value = "权限验证", notes = "根据用户token，访问的url和method判断用户是否有权限访问")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "_url", value = "访问的url", required = true, dataType = "string"),
            @ApiImplicitParam(paramType = "query", name = "_method", value = "访问的method", required = true, dataType = "string")
    })
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = GeneralResult.class))
    @PostMapping(value = "/auth/permission")
    public GeneralResult<Boolean> decide(@RequestParam String _url, @RequestParam String _method, HttpServletRequest _request) {
        boolean decide = authenticationService.decide(new HttpServletRequestAuthWrapper(_request, _url, _method));
        return GeneralResult.success(decide);
    }
}
