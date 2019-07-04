package com.twinkle.cloud.common.constant;

import java.util.HashMap;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     12/11/17 7:54 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
public final class ExceptionCode {
    public final static HashMap<String, String> EXCEPTION_MSG = new HashMap<>();
    static {
        EXCEPTION_MSG.put(ResultCode.ORG_USER_RETRIEVE_FAILED, "查询部门员工失败！");
        EXCEPTION_MSG.put(ResultCode.USERMGMT_UNEXPECTED_EXCEPTION, "获取考生信息异常！");
    }
}
