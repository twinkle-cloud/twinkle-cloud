package com.twinkle.cloud.common.bus.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     12/24/19 9:55 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@Data
@RefreshScope
@Configuration
@ConditionalOnProperty(value = "twinkle.bus.sender.enable",havingValue = "true")
@ConfigurationProperties(prefix = "twinkle.bus.sender")
public class BusSenderConfiguration {
    private String queueName;
    private String exchangeName;
    private String routingKey ;
}
