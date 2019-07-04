package com.twinkle.cloud.authserver.data.otd;

import java.io.Serializable;

/**
 * ClassName: JwtAuthenticationResponse <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: Sep 30, 2016 2:56:31 PM <br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
public class JwtAuthenticationResponse implements Serializable {
    private static final long serialVersionUID = 1250166508152483573L;

    private final String token;

    public JwtAuthenticationResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }
}
