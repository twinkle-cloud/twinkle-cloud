package com.twinkle.cloud.security.component;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import java.util.ArrayList;
import java.util.List;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     1/2/20 10:18 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@Data
@RefreshScope
@ConfigurationProperties(prefix = "twinkle.security.ignore")
public class PermitAllUrlConfiguration {
    /**
     * 放行url,支持 ant 表达式
     */
    private List<String> urls = new ArrayList<>();
}
