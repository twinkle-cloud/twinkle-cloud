package com.twinkle.cloud.common.exception;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     12/21/19 9:53 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
public interface ErrorType {
    /**
     * 返回code
     *
     * @return
     */
    String getCode();

    /**
     * 返回message
     *
     * @return
     */
    String getMessage();
}
