package com.twinkle.cloud.security.authentication.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     12/21/19 4:12 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@Data
public class Resource implements Serializable {
    private String code;
    private String name;
    private String type;
    private String url;
    private String method;
    private String description;
    private String id;
    private String creatorId;
    private Date createDate;
    private Date updateDate;
}
