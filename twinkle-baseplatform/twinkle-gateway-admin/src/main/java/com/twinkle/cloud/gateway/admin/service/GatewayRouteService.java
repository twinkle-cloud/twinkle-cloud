package com.twinkle.cloud.gateway.admin.service;

import com.twinkle.cloud.gateway.admin.entity.otd.GatewayRouteResponse;
import com.twinkle.cloud.gateway.admin.entity.query.GatewayRouteQuery;
import com.twinkle.cloud.gateway.admin.entity.TGatewayRoute;

import java.util.List;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     12/24/19 9:30 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
public interface GatewayRouteService {
    /**
     * 获取网关路由
     *
     * @param id
     * @return
     */
    TGatewayRoute get(String id);

    /**
     * 新增网关路由
     *
     * @param gatewayRoute
     * @return
     */
    boolean add(TGatewayRoute gatewayRoute);

    /**
     * 查询网关路由
     *
     * @return
     */
    List<GatewayRouteResponse> query(GatewayRouteQuery gatewayRouteQueryParam);

    /**
     * 更新网关路由信息
     *
     * @param gatewayRoute
     */
    boolean update(TGatewayRoute gatewayRoute);

    /**
     * 根据id删除网关路由
     *
     * @param id
     */
    boolean delete(String id);

    /**
     * 重新加载网关路由配置到redis
     *
     * @return 成功返回true
     */
    boolean overload();
}
