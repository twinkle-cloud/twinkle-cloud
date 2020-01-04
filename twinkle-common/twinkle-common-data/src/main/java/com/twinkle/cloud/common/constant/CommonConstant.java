/**
 * Project Name:liz-common-com.twinkle.cloud.common.asm.utils
 * File Name:CommonConstant.java
 * Package Name:com.gemii.lizcloud.common.constant
 * Date:Sep 30, 20162:56:31 PM
 * Copyright (c) 2016, chenxj All Rights Reserved.
 */

package com.twinkle.cloud.common.constant;

/**
 * ClassName:CommonConstant <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: Sep 30, 2016 2:56:31 PM <br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
public interface CommonConstant {
    Byte DIC_REGION_ADMIN = 0;
    Byte DIC_REGION_VIRTAUL = 1;
    Byte DIC_GLOBAL_STATUS_INITIAL = 0;
    Byte DIC_GLOBAL_STATUS_ENABLE = 1;
    Byte DIC_GLOBAL_STATUS_SUSPEND = 2;
    Byte DIC_GLOBAL_STATUS_CANCELLED = 3;
    Byte DIC_GLOBAL_STATUS_DELETED = 4;
    Byte DIC_GLOBAL_STATUS_DISABLE = 5;

    Byte DIC_USER_TYPE_VY_ADMIN = 0;
    Byte DIC_USER_TYPE_TENANT_ADMIN = 1;
    Byte DIC_USER_TYPE_TENANT_USER = 2;



    /**
     * 编码
     */
    String UTF8 = "UTF-8";

    /**
     * JSON 资源
     */
    String CONTENT_TYPE = "application/json; charset=utf-8";
}
