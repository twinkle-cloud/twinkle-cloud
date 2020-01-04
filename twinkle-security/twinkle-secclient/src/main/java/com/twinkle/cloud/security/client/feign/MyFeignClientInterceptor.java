package com.twinkle.cloud.security.client.feign;

import com.twinkle.cloud.common.constant.SecurityConstant;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.security.oauth2.client.AccessTokenContextRelay;
import org.springframework.cloud.security.oauth2.client.feign.OAuth2FeignRequestInterceptor;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.util.CollectionUtils;

import java.util.Collection;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     1/3/20 4:16 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@Slf4j
public class MyFeignClientInterceptor extends OAuth2FeignRequestInterceptor {
    private final OAuth2ClientContext oAuth2ClientContext;
    private final AccessTokenContextRelay accessTokenContextRelay;

    /**
     * Default constructor which uses the provided OAuth2ClientContext and Bearer tokens
     * within Authorization header
     *
     * @param oAuth2ClientContext     provided context
     * @param resource                type of resource to be accessed
     * @param accessTokenContextRelay
     */
    public MyFeignClientInterceptor(OAuth2ClientContext oAuth2ClientContext
            , OAuth2ProtectedResourceDetails resource, AccessTokenContextRelay accessTokenContextRelay) {
        super(oAuth2ClientContext, resource);
        this.oAuth2ClientContext = oAuth2ClientContext;
        this.accessTokenContextRelay = accessTokenContextRelay;
    }


    /**
     * Create a template with the header of provided name and extracted extract
     * 1. 如果使用 非web 请求，header 区别
     * 2. 根据authentication 还原请求token
     *
     * @param template
     */
    @Override
    public void apply(RequestTemplate template) {
        Collection<String> fromHeader = template.headers().get(SecurityConstant.FROM);
        if (!CollectionUtils.isEmpty(fromHeader) && fromHeader.contains(SecurityConstant.FROM_IN)) {
            return;
        }

        accessTokenContextRelay.copyToken();
        if (oAuth2ClientContext != null
                && oAuth2ClientContext.getAccessToken() != null) {
            super.apply(template);
        }
    }
}
