package com.twinkle.cloud.common.jwt;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     11/13/18 11:59 AM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
public interface IJwtInfo {
    /**
     * 获取用户名
     *
     * @return
     */
    String getUniqueName();

    /**
     * 获取用户ID
     *
     * @return
     */
    String getId();

    /**
     * 获取名称
     *
     * @return
     */
    String getName();
}
