package com.twinkle.cloud.common.exception;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     11/13/18 3:29 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
public class BaseException extends RuntimeException {
    private static final long serialVersionUID = -9035955863020658450L;

    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BaseException() {
    }

    public BaseException(String _code, String message) {
        super(message);
        this.code = _code;
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    public BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
