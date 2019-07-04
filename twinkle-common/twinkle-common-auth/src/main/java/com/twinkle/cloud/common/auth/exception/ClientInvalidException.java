package com.twinkle.cloud.common.auth.exception;

import com.twinkle.cloud.common.constant.SecurityConstant;
import com.twinkle.cloud.common.exception.BaseException;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     11/15/18 7:33 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
public class ClientInvalidException extends BaseException {
    private static final long serialVersionUID = 6413675348115649415L;

    public ClientInvalidException(String message) {
        super(message, SecurityConstant.EX_CLIENT_INVALID_CODE);
    }
}
