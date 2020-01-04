package com.twinkle.cloud.security.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.twinkle.cloud.common.constant.CommonConstant;
import com.twinkle.cloud.common.constant.HttpStatus;
import com.twinkle.cloud.common.data.GeneralResult;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Function: 1. 可以根据 AuthenticationException 不同细化异常处理. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     1/2/20 10:04 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@Slf4j
@Component
@AllArgsConstructor
public class ResourceAuthExceptionEntryPoint implements AuthenticationEntryPoint {
    private final ObjectMapper objectMapper;

    @Override
    @SneakyThrows
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) {
        response.setCharacterEncoding(CommonConstant.UTF8);
        response.setContentType(CommonConstant.CONTENT_TYPE);
        GeneralResult<String> result = new GeneralResult<>();
        result.setCode(HttpStatus.HTTP_UNAUTHORIZED);
        if (authException != null) {
            result.setDescription("error");
            result.setData(authException.getMessage());
        }
        response.setStatus(HttpStatus.HTTP_UNAUTHORIZED);
        PrintWriter printWriter = response.getWriter();
        printWriter.append(this.objectMapper.writeValueAsString(result));
    }
}
