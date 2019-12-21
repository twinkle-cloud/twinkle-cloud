package com.twinkle.cloud.security.authorization.component;

import com.twinkle.cloud.security.authorization.feign.SmsCodeProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     12/21/19 6:29 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@Component
public class SmsCodeProviderFallback implements SmsCodeProvider {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String getSmsCode(String mobile, String businessType) {
        // 该类为mock, 目前暂时没有sms的服务
        return this.passwordEncoder.encode("123456");
    }
}
