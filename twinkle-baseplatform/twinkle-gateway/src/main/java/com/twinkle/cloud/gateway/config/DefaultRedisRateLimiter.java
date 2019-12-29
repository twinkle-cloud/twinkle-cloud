package com.twinkle.cloud.gateway.config;

import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.support.ConfigurationService;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     12/22/19 5:00 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@Configuration
public class DefaultRedisRateLimiter extends RedisRateLimiter {
    Config getDefaultConfig() {
        return super.getConfig().get("defaultFilters");
    }

    public DefaultRedisRateLimiter(ReactiveStringRedisTemplate _redisTemplate,
                                   RedisScript<List<Long>> _script, ConfigurationService configurationService) {
        super(_redisTemplate, _script, configurationService);
    }

    @Override
    public Mono<Response> isAllowed(String _routeId, String _id) {
        if (null == super.getConfig().get(_routeId))
            getConfig().put(_routeId, getDefaultConfig());
        return super.isAllowed(_routeId, _id);
    }
}
