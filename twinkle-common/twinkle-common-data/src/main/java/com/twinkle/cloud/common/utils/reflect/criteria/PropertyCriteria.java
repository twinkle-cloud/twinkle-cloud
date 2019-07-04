package com.twinkle.cloud.common.utils.reflect.criteria;

import com.twinkle.cloud.common.utils.reflect.criteria.expression.PropertyExpression;
import com.twinkle.cloud.common.utils.filter.PropertyFilter;

import java.util.List;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     11/14/18 5:12 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
public interface PropertyCriteria {

	public PropertyCriteria add(PropertyExpression expression);
	
	public List<PropertyFilter> getFilters();
	
}
