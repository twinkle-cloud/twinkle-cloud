package com.twinkle.cloud.gateway.service;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     12/22/19 5:07 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
public interface PermissionService {
    /**
     * @param authentication
     * @param method
     * @param url
     * @return
     */
    boolean permission(String authentication, String url, String method);
}
