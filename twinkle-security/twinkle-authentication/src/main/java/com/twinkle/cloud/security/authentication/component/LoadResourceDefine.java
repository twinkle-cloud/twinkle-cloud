package com.twinkle.cloud.security.authentication.component;

import com.twinkle.cloud.security.authentication.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.Map;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     12/21/19 4:14 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
public class LoadResourceDefine {
    @Autowired
    private ResourceService resourceService;

    @Bean
    public Map<RequestMatcher, ConfigAttribute> resourceConfigAttributes() {
        return resourceService.loadResource();
    }

}
