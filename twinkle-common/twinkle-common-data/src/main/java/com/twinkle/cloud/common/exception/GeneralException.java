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
public class GeneralException extends RuntimeException {
    private static final long serialVersionUID = -9035955863020658450L;

    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public GeneralException() {
    }

    public GeneralException(String _code, String _message) {
        super(_message);
        this.code = _code;
    }

    public GeneralException(String _message) {
        super(_message);
    }

    public GeneralException(String _message, Throwable _cause) {
        super(_message, _cause);
    }

    public GeneralException(Throwable _cause) {
        super(_cause);
    }

    public GeneralException(String _message, Throwable _cause, boolean _enableSuppression, boolean _writableStackTrace) {
        super(_message, _cause, _enableSuppression, _writableStackTrace);
    }
}
