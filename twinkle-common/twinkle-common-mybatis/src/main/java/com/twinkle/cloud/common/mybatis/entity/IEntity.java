package com.twinkle.cloud.common.mybatis.entity;

import java.io.Serializable;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     12/21/19 3:33 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
public interface IEntity extends Serializable {
    String DEFAULT_USERNAME = "system";

    /**
     * Support String ID.
     *
     * @param _id
     */
    default void setId(String _id) {

    }

    /**
     * Support Long Id.
     *
     * @param _id
     */
    default void setId(Long _id) {

    }

    /**
     * Support Integer Id.
     *
     * @param _id
     */
    default void setId(Integer _id) {

    }
}
