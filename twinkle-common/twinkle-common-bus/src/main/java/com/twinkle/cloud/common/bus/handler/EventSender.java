package com.twinkle.cloud.common.bus.handler;

import com.twinkle.cloud.common.bus.config.BusSenderConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     12/24/19 9:43 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@ConditionalOnProperty(value = "twinkle.bus.sender.enable",havingValue = "true")
@Component
@Slf4j
public class EventSender {
    @Autowired
    private BusSenderConfiguration busSenderConfiguration;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private MessageConverter messageConverter;

    @PostConstruct
    public void init() {
        this.rabbitTemplate.setMessageConverter(this.messageConverter);
    }

    public void send(String _routingKey, Object _object) {
        log.info("routingKey:{}=>message:{}", _routingKey, _object);
        this.rabbitTemplate.convertAndSend(this.busSenderConfiguration.getExchangeName(), _routingKey, _object);
    }
}
