package com.twinkle.cloud.common.auth.handler;

import com.twinkle.cloud.common.auth.exception.ClientTokenException;
import com.twinkle.cloud.common.auth.exception.UserInvalidException;
import com.twinkle.cloud.common.auth.exception.UserTokenException;
import com.twinkle.cloud.common.data.GeneralContentResult;
import com.twinkle.cloud.common.data.GeneralResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     11/14/18 10:38 AM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@Slf4j
@ControllerAdvice("com.twinkle.cloud")
@ResponseBody
public class AuthExceptionHandler {

    @ExceptionHandler(ClientTokenException.class)
    public GeneralResult clientTokenExceptionHandler(HttpServletResponse response, ClientTokenException ex) {
        response.setStatus(403);
        log.error(ex.getMessage(),ex);
        GeneralResult tempResult = new GeneralResult();
        tempResult.setResultCode(ex.getCode());
        tempResult.setDetailDescription(ex.getMessage());
        return tempResult;
    }

    @ExceptionHandler(UserTokenException.class)
    public GeneralResult userTokenExceptionHandler(HttpServletResponse response, UserTokenException ex) {
        response.setStatus(200);
        log.error(ex.getMessage(),ex);
        GeneralResult tempResult = new GeneralResult();
        tempResult.setResultCode(ex.getCode());
        tempResult.setDetailDescription(ex.getMessage());
        return tempResult;
    }

    @ExceptionHandler(UserInvalidException.class)
    public GeneralResult userInvalidExceptionHandler(HttpServletResponse response, UserInvalidException ex) {
        response.setStatus(200);
        GeneralResult tempResult = new GeneralResult();
        tempResult.setResultCode(ex.getCode());
        tempResult.setDetailDescription(ex.getMessage());
        return tempResult;
    }
}
