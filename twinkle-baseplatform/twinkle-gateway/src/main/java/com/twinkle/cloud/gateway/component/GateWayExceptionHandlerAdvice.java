package com.twinkle.cloud.gateway.component;

import com.twinkle.cloud.common.constant.SystemErrorType;
import com.twinkle.cloud.common.data.GeneralResult;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.netty.channel.ConnectTimeoutException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     12/22/19 5:22 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@Slf4j
@Component
public class GateWayExceptionHandlerAdvice {

    @ExceptionHandler(value = {ResponseStatusException.class})
    public GeneralResult handle(ResponseStatusException ex) {
        log.error("response status exception:{}", ex.getMessage());
        return GeneralResult.fail(SystemErrorType.GATEWAY_ERROR);
    }

    @ExceptionHandler(value = {ConnectTimeoutException.class})
    public GeneralResult handle(ConnectTimeoutException ex) {
        log.error("connect timeout exception:{}", ex.getMessage());
        return GeneralResult.fail(SystemErrorType.GATEWAY_CONNECT_TIME_OUT);
    }

    @ExceptionHandler(value = {NotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public GeneralResult handle(NotFoundException ex) {
        log.error("not found exception:{}", ex.getMessage());
        return GeneralResult.fail(SystemErrorType.GATEWAY_NOT_FOUND_SERVICE);
    }

    @ExceptionHandler(value = {ExpiredJwtException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public GeneralResult handle(ExpiredJwtException ex) {
        log.error("ExpiredJwtException:{}", ex.getMessage());
        return GeneralResult.fail(SystemErrorType.INVALID_TOKEN);
    }

    @ExceptionHandler(value = {SignatureException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public GeneralResult handle(SignatureException ex) {
        log.error("SignatureException:{}", ex.getMessage());
        return GeneralResult.fail(SystemErrorType.INVALID_TOKEN);
    }

    @ExceptionHandler(value = {MalformedJwtException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public GeneralResult handle(MalformedJwtException ex) {
        log.error("MalformedJwtException:{}", ex.getMessage());
        return GeneralResult.fail(SystemErrorType.INVALID_TOKEN);
    }

    @ExceptionHandler(value = {RuntimeException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public GeneralResult handle(RuntimeException ex) {
        log.error("runtime exception:{}", ex.getMessage());
        return GeneralResult.fail();
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public GeneralResult handle(Exception ex) {
        log.error("exception:{}", ex.getMessage());
        return GeneralResult.fail();
    }

    @ExceptionHandler(value = {Throwable.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public GeneralResult handle(Throwable throwable) {
        GeneralResult tempResult = GeneralResult.fail();
        if (throwable instanceof ResponseStatusException) {
            tempResult = handle((ResponseStatusException) throwable);
        } else if (throwable instanceof ConnectTimeoutException) {
            tempResult = handle((ConnectTimeoutException) throwable);
        } else if (throwable instanceof NotFoundException) {
            tempResult = handle((NotFoundException) throwable);
        } else if (throwable instanceof RuntimeException) {
            tempResult = handle((RuntimeException) throwable);
        } else if (throwable instanceof Exception) {
            tempResult = handle((Exception) throwable);
        }
        return tempResult;
    }
}
