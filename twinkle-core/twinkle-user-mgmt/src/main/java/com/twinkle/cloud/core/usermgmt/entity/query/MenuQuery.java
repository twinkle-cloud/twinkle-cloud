package com.twinkle.cloud.core.usermgmt.entity.query;

import com.twinkle.cloud.common.mybatis.entity.query.BaseQuery;
import com.twinkle.cloud.core.usermgmt.entity.Menu;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     1/1/20 10:36 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuQuery extends BaseQuery<Menu> {
    private String name;
    private String code;
    private Integer type;
}
