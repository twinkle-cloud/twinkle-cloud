package com.twinkle.cloud.common.auth.exception;

import com.twinkle.cloud.common.constant.SecurityConstant;
import com.twinkle.cloud.common.exception.BaseException;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     11/13/18 3:42 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
public class UserTokenException extends BaseException {
    public UserTokenException(String message) {
        super(message, SecurityConstant.EX_USER_INVALID_CODE);
    }
}
