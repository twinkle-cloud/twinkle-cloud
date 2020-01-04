package com.twinkle.cloud.security.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.twinkle.cloud.security.data.databind.MyOAuth2ExceptionSerializer;
import org.springframework.http.HttpStatus;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     1/3/20 3:55 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@JsonSerialize(using = MyOAuth2ExceptionSerializer.class)
public class ForbiddenException extends MyOAuth2Exception {
    public ForbiddenException(String msg, Throwable t) {
        super(msg);
    }

    @Override
    public String getOAuth2ErrorCode() {
        return "access_denied";
    }

    @Override
    public int getHttpErrorCode() {
        return HttpStatus.FORBIDDEN.value();
    }
}
