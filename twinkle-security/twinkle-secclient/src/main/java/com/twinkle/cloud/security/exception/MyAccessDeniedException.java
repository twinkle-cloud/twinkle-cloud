package com.twinkle.cloud.security.exception;

import com.twinkle.cloud.common.exception.GeneralException;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     1/3/20 3:41 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
public class MyAccessDeniedException extends GeneralException {
    public MyAccessDeniedException(String _msg) {
        super(_msg);
    }

    public MyAccessDeniedException(Throwable _cause) {
        super(_cause);
    }

    public MyAccessDeniedException(String _msg, Throwable _cause) {
        super(_msg, _cause);
    }

    public MyAccessDeniedException(String _msg, Throwable _cause, boolean _enableSuppression, boolean _writableStackTrace) {
        super(_msg, _cause, _enableSuppression, _writableStackTrace);
    }
}
