package com.twinkle.cloud.security.authentication.data;

import com.google.common.base.Objects;
import lombok.Getter;
import org.springframework.http.HttpMethod;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     12/21/19 4:17 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@Getter
public class MyMvcRequestMatcher extends MvcRequestMatcher {
    private String pattern;
    private String method;

    public MyMvcRequestMatcher(HandlerMappingIntrospector _introspector, String _pattern, String _method) {
        super(_introspector, _pattern);
        this.setMethod(HttpMethod.resolve(_method));
        this.pattern = _pattern;
        this.method = _method;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyMvcRequestMatcher that = (MyMvcRequestMatcher) o;
        return Objects.equal(pattern, that.pattern) &&
                Objects.equal(method, that.method);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(pattern, method);
    }

}
