package com.twinkle.cloud.redis.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     12/28/19 10:41 AM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@Data
public class City implements Serializable {
    /**
     * 城市编号
     */
    @Id
    private Long id;
    /**     * 省份编号
     */
    private Long provinceId;
    /**     * 城市名称     */
    private String cityName;
    /**     * 描述     */
    private String description;
}
