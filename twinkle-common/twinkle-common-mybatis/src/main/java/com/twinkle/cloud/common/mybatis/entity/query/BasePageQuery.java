package com.twinkle.cloud.common.mybatis.entity.query;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.twinkle.cloud.common.mybatis.entity.dto.BaseRequest;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     12/21/19 3:43 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@ApiModel
@Slf4j
@Data
public class BasePageQuery<P extends BaseQuery> extends BaseRequest {
    /**
     * 分页查询的参数，当前页数
     */
    private long current = 1;
    /**
     * 分页查询的参数，当前页面每页显示的数量
     */
    private long size = 10;

    /**
     * Form转化为Param
     *
     * @param clazz
     * @return
     */
    public P toParam(Class<P> clazz) {
        P p = BeanUtils.instantiateClass(clazz);
        BeanUtils.copyProperties(this, p);
        return p;
    }

    /**
     * 从form中获取page参数，用于分页查询参数
     *
     * @return
     */
    public Page getPage() {
        return new Page(this.getCurrent(), this.getSize());
    }

}
