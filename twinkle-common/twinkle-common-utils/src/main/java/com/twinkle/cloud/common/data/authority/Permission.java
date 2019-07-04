package com.twinkle.cloud.common.data.authority;

import lombok.Data;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     11/15/18 11:13 AM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@Data
public class Permission {
    private String id;
    private String code;
    private String type;
    private String uri;
    private String method;
    private String name;
    private String menu;
}
