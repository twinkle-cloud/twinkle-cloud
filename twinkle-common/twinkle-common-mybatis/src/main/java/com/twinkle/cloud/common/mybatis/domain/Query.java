package com.twinkle.cloud.common.mybatis.domain;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     11/14/18 1:40 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
public class Query extends LinkedHashMap<String, Object> {
    private static final long serialVersionUID = 6714976124473104459L;

    public Query(){
        super();
    }
    public Query(Map<String, Object> params) {
        this.putAll(params);
    }
}
