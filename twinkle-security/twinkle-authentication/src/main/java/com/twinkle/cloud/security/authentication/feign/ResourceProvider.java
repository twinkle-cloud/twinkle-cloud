package com.twinkle.cloud.security.authentication.feign;

import com.twinkle.cloud.common.data.GeneralResult;
import com.twinkle.cloud.security.authentication.component.ResourceProviderFallback;
import com.twinkle.cloud.security.authentication.entity.Resource;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Set;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     12/21/19 4:19 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@FeignClient(name = "twinkle-usermgmt", fallback = ResourceProviderFallback.class)
public interface ResourceProvider {
    /**
     * Get the resource list.
     *
     * @return
     */
    @GetMapping(value = "/resource/all")
    GeneralResult<Set<Resource>> getResources();

    /**
     * Get the resource list with given userName.
     *
     * @param _userName
     * @return
     */
    @GetMapping(value = "/resource/user/{_userName}")
    GeneralResult<Set<Resource>> getResources(@PathVariable("_userName") String _userName);

}
