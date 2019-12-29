package com.twinkle.cloud.gateway.admin.service.impl;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.CreateCache;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twinkle.cloud.common.bus.config.BusSenderConfiguration;
import com.twinkle.cloud.common.bus.handler.EventSender;
import com.twinkle.cloud.gateway.admin.entity.ov.GatewayRouteVo;
import com.twinkle.cloud.gateway.admin.entity.param.GatewayRouteQueryParam;
import com.twinkle.cloud.gateway.admin.entity.po.TGatewayRoute;
import com.twinkle.cloud.gateway.admin.mapper.GatewayRouteMapper;
import com.twinkle.cloud.gateway.admin.service.GatewayRouteService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     12/24/19 9:30 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@Service
@Slf4j
public class GatewayRouteServiceImpl extends ServiceImpl<GatewayRouteMapper, TGatewayRoute> implements GatewayRouteService, ApplicationRunner {
    private static final String GATEWAY_ROUTES = "gateway_routes::";

    @CreateCache(name = GATEWAY_ROUTES, cacheType = CacheType.REMOTE)
    private Cache<String, RouteDefinition> gatewayRouteCache;

    @Autowired
    private BusSenderConfiguration busSenderConfiguration;

    @Autowired
    private EventSender eventSender;

    @Override
    public boolean add(TGatewayRoute gatewayRoute) {
        boolean isSuccess = this.save(gatewayRoute);
        // 转化为gateway需要的类型，缓存到redis, 并事件通知各网关应用
        RouteDefinition routeDefinition = gatewayRouteToRouteDefinition(gatewayRoute);
        this.gatewayRouteCache.put(gatewayRoute.getRouteId(), routeDefinition);
        this.eventSender.send(this.busSenderConfiguration.getRoutingKey(), routeDefinition);
        return isSuccess;
    }

    @Override
    public boolean delete(String id) {
        TGatewayRoute gatewayRoute = this.getById(id);
        // 删除redis缓存, 并事件通知各网关应用
        this.gatewayRouteCache.remove(gatewayRoute.getRouteId());
        this.eventSender.send(this.busSenderConfiguration.getRoutingKey(), gatewayRouteToRouteDefinition(gatewayRoute));
        return this.removeById(id);
    }

    @Override
    public boolean update(TGatewayRoute gatewayRoute) {
        boolean isSuccess = this.updateById(gatewayRoute);
        // 转化为gateway需要的类型，缓存到redis, 并事件通知各网关应用
        RouteDefinition routeDefinition = gatewayRouteToRouteDefinition(gatewayRoute);
        this.gatewayRouteCache.put(gatewayRoute.getRouteId(), routeDefinition);
        this.eventSender.send(this.busSenderConfiguration.getRoutingKey(), routeDefinition);
        return isSuccess;
    }

    /**
     * 将数据库中json对象转换为网关需要的RouteDefinition对象
     *
     * @param gatewayRoute
     * @return RouteDefinition
     */
    private RouteDefinition gatewayRouteToRouteDefinition(TGatewayRoute gatewayRoute) {
        RouteDefinition routeDefinition = new RouteDefinition();
        routeDefinition.setId(gatewayRoute.getRouteId());
        routeDefinition.setOrder(gatewayRoute.getOrders());
        routeDefinition.setUri(URI.create(gatewayRoute.getUri()));
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            routeDefinition.setFilters(objectMapper.readValue(gatewayRoute.getFilters(), new TypeReference<List<FilterDefinition>>() {
            }));
            routeDefinition.setPredicates(objectMapper.readValue(gatewayRoute.getPredicates(), new TypeReference<List<PredicateDefinition>>() {
            }));
        } catch (IOException e) {
            log.error("网关路由对象转换失败", e);
        }
        return routeDefinition;
    }

    @Override
    public TGatewayRoute get(String id) {
        return this.getById(id);
    }

    @Override
    public List<GatewayRouteVo> query(GatewayRouteQueryParam gatewayRouteQueryParam) {
        QueryWrapper<TGatewayRoute> queryWrapper = gatewayRouteQueryParam.build();
        queryWrapper.eq(StringUtils.isNotBlank(gatewayRouteQueryParam.getUri()), "uri", gatewayRouteQueryParam.getUri());
        return this.list(queryWrapper).stream().map(GatewayRouteVo::new).collect(Collectors.toList());
    }

    @Override
    public boolean overload() {
        List<TGatewayRoute> gatewayRoutes = this.list(new QueryWrapper<>());
        log.info("The retrieved gateway routes: [{}]", gatewayRoutes);
        gatewayRoutes.forEach(gatewayRoute ->
                this.gatewayRouteCache.put(gatewayRoute.getRouteId(), gatewayRouteToRouteDefinition(gatewayRoute))
        );
        log.info("全局初使化网关路由成功!");
        return true;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        this.overload();
    }
}
