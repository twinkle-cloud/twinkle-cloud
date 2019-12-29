package com.twinkle.cloud.common.bus.handler;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     12/22/19 4:29 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
public interface BusReceiver<T> {
    /**
     * Process the received message.
     *
     * @param _message
     */
    void handleMessage(T _message);
}
