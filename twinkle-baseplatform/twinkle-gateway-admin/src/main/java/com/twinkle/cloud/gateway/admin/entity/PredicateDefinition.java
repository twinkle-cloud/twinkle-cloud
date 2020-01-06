package com.twinkle.cloud.gateway.admin.entity;

import lombok.*;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     12/24/19 9:28 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@EqualsAndHashCode
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PredicateDefinition {
    private String name;
    private Map<String, String> args = new LinkedHashMap<>();
}
