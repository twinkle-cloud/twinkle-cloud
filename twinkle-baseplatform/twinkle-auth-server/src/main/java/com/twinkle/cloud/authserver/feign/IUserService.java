package com.twinkle.cloud.authserver.feign;

import com.twinkle.cloud.authserver.config.FeignConfiguration;
import com.twinkle.cloud.authserver.data.dto.JwtAuthenticationRequest;
import com.twinkle.cloud.common.data.otd.user.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * ClassName: IUserService <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: Sep 30, 2016 2:56:31 PM <br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@FeignClient(value = "ace-admin",configuration = FeignConfiguration.class)
public interface IUserService {
  @RequestMapping(value = "/api/user/validate", method = RequestMethod.POST)
  UserInfo validate(@RequestBody JwtAuthenticationRequest authenticationRequest);
}
