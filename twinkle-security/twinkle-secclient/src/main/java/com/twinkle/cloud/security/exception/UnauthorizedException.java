package com.twinkle.cloud.security.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.twinkle.cloud.security.data.databind.MyOAuth2ExceptionSerializer;
import org.springframework.http.HttpStatus;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     1/3/20 4:00 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@JsonSerialize(using = MyOAuth2ExceptionSerializer.class)
public class UnauthorizedException extends MyOAuth2Exception {
    public UnauthorizedException(String msg, Throwable t) {
        super(msg);
    }

    @Override
    public String getOAuth2ErrorCode() {
        return "unauthorized";
    }

    @Override
    public int getHttpErrorCode() {
        return HttpStatus.UNAUTHORIZED.value();
    }
}
