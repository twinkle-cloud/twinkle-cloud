package com.twinkle.cloud.common.mybatis.entity.vo;

import com.twinkle.cloud.common.mybatis.entity.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     12/21/19 3:39 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@Data
@NoArgsConstructor
public class BaseVo<E extends BaseEntity> implements Serializable {
    private Serializable id;
}
