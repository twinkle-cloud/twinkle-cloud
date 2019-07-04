package com.twinkle.cloud.common.handler;

import com.twinkle.cloud.common.constant.ResultCode;
import com.twinkle.cloud.common.data.GeneralResult;
import com.twinkle.cloud.common.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     11/13/18 5:20 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@Slf4j
@ControllerAdvice("com.twinkle.cloud")
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public GeneralResult baseExceptionHandler(HttpServletResponse response, BaseException ex) {
        log.error(ex.getMessage(),ex);
        response.setStatus(500);
        GeneralResult tempResult = new GeneralResult();
        tempResult.setResultCode(ex.getCode());
        tempResult.setDetailDescription(ex.getMessage());
        return tempResult;
    }

    @ExceptionHandler(Exception.class)
    public GeneralResult otherExceptionHandler(HttpServletResponse response, Exception ex) {
        response.setStatus(500);
        log.error(ex.getMessage(),ex);

        GeneralResult tempResult = new GeneralResult();
        tempResult.setResultCode(ResultCode.OPERATION_FAILED_UNKOWN);
        tempResult.setDetailDescription(ex.getMessage());
        return tempResult;
    }
}
