package com.twinkle.cloud.security.data.databind;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.twinkle.cloud.common.constant.SecurityConstant;
import com.twinkle.cloud.security.exception.MyOAuth2Exception;
import lombok.SneakyThrows;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     1/3/20 3:03 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
public class MyOAuth2ExceptionSerializer extends StdSerializer<MyOAuth2Exception> {
    public MyOAuth2ExceptionSerializer() {
        super(MyOAuth2Exception.class);
    }

    @Override
    @SneakyThrows
    public void serialize(MyOAuth2Exception value, JsonGenerator gen, SerializerProvider provider) {
        gen.writeStartObject();
        gen.writeObjectField("code", SecurityConstant.EX_SECURITY_CODE);
        gen.writeStringField("msg", value.getMessage());
        gen.writeStringField("data", value.getCode());
        gen.writeEndObject();
    }
}
