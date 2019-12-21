package com.twinkle.cloud.security.authorization.feign;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     12/21/19 6:29 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
//@FeignClient(name = "sms", fallback = OrganizationProviderFallback.class)
public interface SmsCodeProvider {
    /**
     * @param mobile
     * @return
     */
    @GetMapping(value = "/sms/{mobile}")
    String getSmsCode(@PathVariable("mobile") String mobile, @RequestParam("businessType") String businessType);
}
