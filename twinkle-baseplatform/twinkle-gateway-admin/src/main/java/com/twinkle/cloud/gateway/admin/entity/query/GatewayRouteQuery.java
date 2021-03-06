package com.twinkle.cloud.gateway.admin.entity.query;

import com.twinkle.cloud.common.mybatis.entity.query.BaseQuery;
import com.twinkle.cloud.gateway.admin.entity.TGatewayRoute;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     12/24/19 9:27 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GatewayRouteQuery extends BaseQuery<TGatewayRoute> {
    private String uri;
}
