package com.twinkle.cloud.security.annotation.processor;

import com.twinkle.cloud.common.constant.SecurityConstant;
import com.twinkle.cloud.security.annotation.NoAuth;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     1/3/20 4:23 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@Slf4j
@Aspect
@Component
@AllArgsConstructor
public class NoAuthProcessor implements Ordered {
    private final HttpServletRequest request;

    @SneakyThrows
    @Around("@annotation(noauth)")
    public Object around(ProceedingJoinPoint point, NoAuth noAuth) {
        String header = request.getHeader(SecurityConstant.FROM);
        if (noAuth.value() && !StringUtils.equals(SecurityConstant.FROM_IN, header)) {
            log.warn("访问接口 {} 没有权限", point.getSignature().getName());
            throw new AccessDeniedException("Access is denied");
        }
        return point.proceed();
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 1;
    }

}
