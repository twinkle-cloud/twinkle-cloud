package com.twinkle.cloud.security.authorization.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.twinkle.cloud.common.data.GeneralResult;
import com.twinkle.cloud.security.authorization.constants.OAuth2ErrorType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     12/21/19 6:38 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@EqualsAndHashCode(callSuper = true)
@Data
@JsonSerialize(using = CustomOauthExceptionSerializer.class)
public class CustomOauthException extends OAuth2Exception {
    private final GeneralResult result;
    CustomOauthException(OAuth2Exception _exception) {
        super(_exception.getSummary(), _exception);
        this.result = GeneralResult.fail(OAuth2ErrorType.valueOf(_exception.getOAuth2ErrorCode().toUpperCase()), _exception);
    }
}
