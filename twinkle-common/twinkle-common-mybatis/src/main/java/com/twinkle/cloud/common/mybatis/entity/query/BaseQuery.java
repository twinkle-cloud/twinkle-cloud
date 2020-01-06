package com.twinkle.cloud.common.mybatis.entity.query;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.twinkle.cloud.common.mybatis.entity.BaseEntity;

import java.util.Date;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     12/21/19 3:44 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
public class BaseQuery<T extends BaseEntity> {
    private Date createdTimeStart;
    private Date createdTimeEnd;

    public QueryWrapper<T> build() {
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        queryWrapper.ge(null != this.createdTimeStart, "created_time", this.createdTimeStart)
                .le(null != this.createdTimeEnd, "created_time", this.createdTimeEnd);
        return queryWrapper;
    }
}
