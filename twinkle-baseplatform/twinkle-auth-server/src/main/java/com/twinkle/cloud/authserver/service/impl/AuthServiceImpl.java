package com.twinkle.cloud.authserver.service.impl;

import com.twinkle.cloud.authserver.data.dto.JwtAuthenticationRequest;
import com.twinkle.cloud.authserver.feign.IUserService;
import com.twinkle.cloud.authserver.service.AuthService;
import com.twinkle.cloud.authserver.util.user.JwtTokenUtil;
import com.twinkle.cloud.common.auth.exception.UserInvalidException;
import com.twinkle.cloud.common.data.otd.user.UserInfo;
import com.twinkle.cloud.common.jwt.data.JwtInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * ClassName: AuthServiceImpl <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: Sep 30, 2016 2:56:31 PM <br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@Service
public class AuthServiceImpl implements AuthService {

    private JwtTokenUtil jwtTokenUtil;
    private IUserService userService;

    @Autowired
    public AuthServiceImpl(
            JwtTokenUtil jwtTokenUtil,
            IUserService userService) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.userService = userService;
    }

    @Override
    public String login(JwtAuthenticationRequest authenticationRequest) throws Exception {
        UserInfo info = userService.validate(authenticationRequest);
        if (!StringUtils.isEmpty(info.getId())) {
            return jwtTokenUtil.generateToken(new JwtInfo(info.getUsername(), info.getId() + "", info.getName()));
        }
        throw new UserInvalidException("Invalid username or password!");
    }

    @Override
    public void validate(String token) throws Exception {
        jwtTokenUtil.getInfoFromToken(token);
    }

    @Override
    public String refresh(String oldToken) throws Exception {
        return jwtTokenUtil.generateToken(jwtTokenUtil.getInfoFromToken(oldToken));
    }
}
