package com.twinkle.cloud.common.auth.exception;

import com.twinkle.cloud.common.constant.SecurityConstant;
import com.twinkle.cloud.common.exception.BaseException;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     11/14/18 10:48 AM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
public class UserInvalidException extends BaseException {
    public UserInvalidException(String message) {
        super(message, SecurityConstant.EX_USER_PASS_INVALID_CODE);
    }
}
