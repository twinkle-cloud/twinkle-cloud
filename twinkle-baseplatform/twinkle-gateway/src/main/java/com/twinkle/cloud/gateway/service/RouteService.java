package com.twinkle.cloud.gateway.service;

import org.springframework.cloud.gateway.route.RouteDefinition;

import java.util.Collection;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     12/22/19 5:06 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
public interface RouteService {
    /**
     * Get route definitions.
     *
     * @return
     */
    Collection<RouteDefinition> getRouteDefinitions();

    /**
     * Add route definition.
     *
     * @param _route
     * @return
     */
    boolean save(RouteDefinition _route);

    /**
     * Delete the route with given routeId.
     *
     * @param _routeId
     * @return
     */
    boolean delete(String _routeId);
}
