package com.twinkle.cloud.gateway.admin.entity.param;

import com.twinkle.cloud.common.mybatis.entity.query.BaseParam;
import com.twinkle.cloud.gateway.admin.entity.po.TGatewayRoute;
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
public class GatewayRouteQueryParam extends BaseParam<TGatewayRoute> {
    private String uri;
}
