package com.twinkle.cloud.common.bus;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twinkle.cloud.common.bus.config.BusSenderConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.ContentTypeDelegatingMessageConverter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     12/22/19 4:25 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@Configuration
@Slf4j
@ConditionalOnProperty(value = "twinkle.bus.sender.enable",havingValue = "true")
public class SenderBusConfig {
    @Autowired
    private BusSenderConfiguration busSenderConfiguration;

    @Bean
    Queue queue() {
        log.info("queue name:{}", this.busSenderConfiguration.getQueueName());
        return new Queue(this.busSenderConfiguration.getQueueName(), false);
    }

    @Bean
    TopicExchange exchange() {
        log.info("exchange:{}", this.busSenderConfiguration.getExchangeName());
        return new TopicExchange(this.busSenderConfiguration.getExchangeName());
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        log.info("binding {} to {} with {}", queue, exchange, this.busSenderConfiguration.getRoutingKey());
        return BindingBuilder.bind(queue).to(exchange).with(this.busSenderConfiguration.getRoutingKey());
    }

    @Bean
    public MessageConverter messageConverter() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        return new ContentTypeDelegatingMessageConverter(new Jackson2JsonMessageConverter(objectMapper));
    }
}
