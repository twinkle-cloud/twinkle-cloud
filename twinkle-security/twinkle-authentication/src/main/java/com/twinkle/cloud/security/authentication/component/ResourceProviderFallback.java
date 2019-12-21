package com.twinkle.cloud.security.authentication.component;

import com.twinkle.cloud.common.data.GeneralResult;
import com.twinkle.cloud.security.authentication.entity.Resource;
import com.twinkle.cloud.security.authentication.feign.ResourceProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     12/21/19 4:20 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@Slf4j
@Component
public class ResourceProviderFallback implements ResourceProvider {
    @Override
    public GeneralResult<Set<Resource>> getResources() {
        log.error("认证服务启动时加载资源异常！未加载到资源");
        return GeneralResult.success(new HashSet<Resource>());
    }

    @Override
    public GeneralResult<Set<Resource>> getResources(String _userName) {
        log.error("认证服务查询用户异常！查询用户资源为空！");
        return GeneralResult.success(new HashSet<Resource>());
    }
}
