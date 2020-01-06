package com.twinkle.cloud.gateway.admin.entity.otd;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twinkle.cloud.common.mybatis.entity.otd.BaseResponse;
import com.twinkle.cloud.gateway.admin.entity.TGatewayRoute;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     12/24/19 9:25 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Slf4j
public class GatewayRouteResponse extends BaseResponse {
    private String id;
    private String routeId;
    private String description;
    private Integer status;
    private String uri;
    private Integer orders;
    private String creatorId;
    private Date createDate;
    private Date updateDate;
    private List<FilterDefinition> filters = new ArrayList<>();
    private List<PredicateDefinition> predicates = new ArrayList<>();

    public GatewayRouteResponse(TGatewayRoute gatewayRoute) {
        this.id = gatewayRoute.getId().toString();
        this.routeId = gatewayRoute.getRouteId();
        this.uri = gatewayRoute.getUri();
        this.description = gatewayRoute.getDescription();
        this.status = gatewayRoute.getStatus();
        this.orders = gatewayRoute.getOrders();
        this.creatorId = gatewayRoute.getCreatorId();
        this.createDate = gatewayRoute.getCreateDate();
        this.updateDate = gatewayRoute.getUpdateDate();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            this.filters = objectMapper.readValue(gatewayRoute.getFilters(), new TypeReference<List<FilterDefinition>>() {
            });
            this.predicates = objectMapper.readValue(gatewayRoute.getPredicates(), new TypeReference<List<PredicateDefinition>>() {
            });
        } catch (IOException e) {
            log.error("网关路由对象转换失败", e);
        }
    }
}
