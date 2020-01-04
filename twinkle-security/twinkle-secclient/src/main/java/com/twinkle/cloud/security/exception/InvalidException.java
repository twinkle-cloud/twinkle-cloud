package com.twinkle.cloud.security.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.twinkle.cloud.security.data.databind.MyOAuth2ExceptionSerializer;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     1/3/20 3:56 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@JsonSerialize(using = MyOAuth2ExceptionSerializer.class)
public class InvalidException extends MyOAuth2Exception {
    public InvalidException(String msg, Throwable t) {
        super(msg);
    }

    @Override
    public String getOAuth2ErrorCode() {
        return "invalid_exception";
    }

    @Override
    public int getHttpErrorCode() {
        return 426;
    }
}
