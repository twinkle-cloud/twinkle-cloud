package com.twinkle.cloud.common.auth.exception;

import com.twinkle.cloud.common.constant.SecurityConstant;
import com.twinkle.cloud.common.exception.BaseException;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     11/13/18 3:37 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
public class ClientForbiddenException extends BaseException {
    private static final long serialVersionUID = 4175522796101683448L;

    public ClientForbiddenException(String message) {
        super(message, SecurityConstant.EX_CLIENT_FORBIDDEN_CODE);
    }
}
