package com.twinkle.cloud.authserver.controller;

import com.twinkle.cloud.authserver.data.dto.JwtAuthenticationRequest;
import com.twinkle.cloud.authserver.service.AuthService;
import com.twinkle.cloud.common.constant.ResultCode;
import com.twinkle.cloud.common.data.GeneralContentResult;
import com.twinkle.cloud.common.data.GeneralResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("jwt")
@Slf4j
public class AuthController {
    @Value("${jwt.token-header}")
    private String tokenHeader;

    @Autowired
    private AuthService authService;

    @RequestMapping(value = "token", method = RequestMethod.POST)
    public GeneralContentResult<String> createAuthenticationToken(
            @RequestBody JwtAuthenticationRequest authenticationRequest) throws Exception {
        log.info(authenticationRequest.getUsername()+" require logging...");
        final String token = authService.login(authenticationRequest);
        return new GeneralContentResult<>(ResultCode.OPERATION_SUCCESS, token);
    }

    @RequestMapping(value = "refresh", method = RequestMethod.GET)
    public GeneralContentResult<String> refreshAndGetAuthenticationToken(
            HttpServletRequest request) throws Exception {
        String token = request.getHeader(tokenHeader);
        String refreshedToken = authService.refresh(token);
        return new GeneralContentResult<>(ResultCode.OPERATION_SUCCESS, refreshedToken);
    }

    @RequestMapping(value = "verify", method = RequestMethod.GET)
    public GeneralResult verify(String token) throws Exception {
        authService.validate(token);
        return new GeneralResult(ResultCode.OPERATION_SUCCESS);
    }
}
