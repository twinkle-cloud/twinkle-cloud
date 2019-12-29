package com.twinkle.cloud.gateway.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.twinkle.cloud.gateway.admin.entity.po.TGatewayRoute;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     12/24/19 9:19 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@Mapper
@Repository
public interface GatewayRouteMapper extends BaseMapper<TGatewayRoute> {
}
