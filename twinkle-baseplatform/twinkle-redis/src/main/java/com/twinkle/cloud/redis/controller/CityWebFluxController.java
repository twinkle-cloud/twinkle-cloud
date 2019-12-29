package com.twinkle.cloud.redis.controller;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.CreateCache;
import com.twinkle.cloud.redis.domain.City;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     12/28/19 10:42 AM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@Slf4j
@RestController
@RequestMapping(value = "/city")
public class CityWebFluxController implements ApplicationRunner {
    @Autowired
    private RedisTemplate redisTemplate;
    @CreateCache(name = "CITY::", cacheType = CacheType.REMOTE)
    private Cache<String, City> tempCache;

    @GetMapping(value = "/{id}")
    public Mono<City> findCityById(@PathVariable("id") Long id) {
        String key = "city_" + id;
        ValueOperations<String, City> operations = redisTemplate.opsForValue();
        boolean hasKey = redisTemplate.hasKey(key);
        City city = operations.get(key);
        if (!hasKey) {
            City tempcity = tempCache.get("Shanghai");
            return Mono.create(monoSink -> monoSink.success(tempcity));
        }
        return Mono.create(monoSink -> monoSink.success(city));
    }
    @PostMapping()
    public Mono<City> saveCity(@RequestBody City city) {
        String key = "city_" + city.getId();
        ValueOperations<String, City> operations = redisTemplate.opsForValue();
        operations.set(key, city, 60, TimeUnit.SECONDS);
        return Mono.create(monoSink -> monoSink.success(city));
    }
    @DeleteMapping(value = "/{id}")
    public Mono<Long> deleteCity(@PathVariable("id") Long id) {
        String key = "city_" + id;
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey)
        {
            redisTemplate.delete(key);
        }
        return Mono.create(monoSink -> monoSink.success(id));
    }

    private void initializeCities () {
        log.info("Going to add City...");
        City tempCity = new City();
        tempCity.setId(2L);
        tempCity.setCityName("Shanghai");
        tempCity.setProvinceId(3L);
        this.tempCache.put(tempCity.getCityName(), tempCity);
    }
    @Override
    public void run(ApplicationArguments args) throws Exception {
        this.initializeCities ();
    }

}
