package com.twinkle.cloud.common.utils;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     11/13/18 2:25 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
public class StringHelper {
    public static String getObjectValue(Object obj) {
        return obj == null ? "" : obj.toString();
    }
}
