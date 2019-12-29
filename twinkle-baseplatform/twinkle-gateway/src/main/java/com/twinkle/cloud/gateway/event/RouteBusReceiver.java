package com.twinkle.cloud.gateway.event;

import com.twinkle.cloud.common.bus.handler.BusReceiver;
import com.twinkle.cloud.gateway.service.RouteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.stereotype.Component;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     12/22/19 5:05 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@Component
@Slf4j
public class RouteBusReceiver implements BusReceiver<RouteDefinition> {
    @Autowired
    private RouteService routeService;

    @Override
    public void handleMessage(RouteDefinition _message) {
        log.info("Received Message:<{}>", _message);
        // 待实现动态del路由
        this.routeService.save(_message);
    }
}
