package com.twinkle.cloud.security.authentication.event;

import com.twinkle.cloud.common.bus.handler.BusReceiver;
import com.twinkle.cloud.security.authentication.entity.Resource;
import com.twinkle.cloud.security.authentication.service.ResourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     12/21/19 4:10 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@Component
@Slf4j
public class ResourceBusReceiver implements BusReceiver<Resource> {
    @Autowired
    private ResourceService resourceService;

    @Override
    public void handleMessage(Resource resource) {
        log.info("Received Message:<{}>", resource);
        this.resourceService.saveResource(resource);
    }
}
