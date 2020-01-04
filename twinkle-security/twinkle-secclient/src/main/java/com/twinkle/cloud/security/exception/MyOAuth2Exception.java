package com.twinkle.cloud.security.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.twinkle.cloud.security.data.databind.MyOAuth2ExceptionSerializer;
import lombok.Getter;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     1/3/20 3:01 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@JsonSerialize(using = MyOAuth2ExceptionSerializer.class)
public class MyOAuth2Exception extends OAuth2Exception {
    @Getter
    private String code;

    public MyOAuth2Exception(String _msg) {
        super(_msg);
    }

    public MyOAuth2Exception(String _msg, String _code) {
        super(_msg);
        this.code = _code;
    }
}
