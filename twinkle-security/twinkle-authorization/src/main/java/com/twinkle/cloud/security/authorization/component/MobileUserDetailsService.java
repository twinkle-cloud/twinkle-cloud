package com.twinkle.cloud.security.authorization.component;

import com.twinkle.cloud.common.constant.ResultCode;
import com.twinkle.cloud.common.data.GeneralResult;
import com.twinkle.cloud.common.data.usermgmt.SecurityUser;
import com.twinkle.cloud.security.authorization.feign.SmsCodeProvider;
import com.twinkle.cloud.security.authorization.feign.UserMgmtProvider;
import com.twinkle.cloud.security.data.TwinkleUser;
import com.twinkle.cloud.security.exception.UnauthorizedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 手机验证码登陆, 用户相关获取. <br/>
 * Date:     12/28/19 2:33 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@Slf4j
@Service("mobileUserDetailsService")
public class MobileUserDetailsService extends CustomUserDetailsService {
    @Autowired
    private UserMgmtProvider userMgmtProvider;
    @Autowired
    private SmsCodeProvider smsCodeProvider;

    @Override
    public UserDetails loadUserByUsername(String _uniqueId) {
        GeneralResult<SecurityUser> tempResult = this.userMgmtProvider.getUserByUniqueId(_uniqueId);
        if(tempResult.getCode().equals(ResultCode.OPERATION_SUCCESS)) {
            SecurityUser tempUser = tempResult.getData();
            log.info("Load user by mobile :{}", tempUser);

            // 如果为mobile模式，从短信服务中获取验证码（动态密码）
            String credentials = smsCodeProvider.getSmsCode(_uniqueId, "LOGIN");
            TwinkleUser tempTwinkleUser = new TwinkleUser(
                    tempUser.getId(),
                    tempUser.getTenantId(),
                    tempUser.getManagedOrgIds(),
                    tempUser.getLoginName(),
                    credentials,
                    this.obtainGrantedAuthorities(tempUser)
            );
            return tempTwinkleUser;
        }
        throw new UnauthorizedException("Did not find user ["+_uniqueId+"].");
    }
}
