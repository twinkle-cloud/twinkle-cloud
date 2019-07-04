package com.twinkle.cloud.core.usermgmt.service.impl;

import com.twinkle.cloud.core.usermgmt.service.HelloWorldService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2019-06-27 16:46<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@Service
@Slf4j
public class HelloWorldServiceImpl implements HelloWorldService {
    @Override
    public String sayHello(String _name) {
        StringBuilder tempBuilder = new StringBuilder();
        tempBuilder.append("Hello ");
        tempBuilder.append(_name);
        tempBuilder.append("!");
        return tempBuilder.toString();
    }
}
