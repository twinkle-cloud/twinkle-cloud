package com.twinkle.cloud.security.authentication.data;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     12/21/19 4:40 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
public class HttpServletRequestAuthWrapper extends HttpServletRequestWrapper {
    private String url;
    private String method;

    /**
     * @param _url
     * @param _method
     */
    public HttpServletRequestAuthWrapper(HttpServletRequest _request, String _url, String _method) {
        super(_request);
        this.url = _url;
        this.method = _method;
    }

    @Override
    public String getMethod() {
        return this.method;
    }

    @Override
    public String getServletPath() {
        return this.url;
    }
}
