package com.twinkle.cloud.common.utils.reflect.criteria;

import com.twinkle.cloud.common.utils.reflect.criteria.expression.PropertyExpression;
import com.twinkle.cloud.common.utils.filter.PropertyFilter;

import java.util.ArrayList;
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
public class PropertyCriteriaImpl implements PropertyCriteria {

	private List<PropertyFilter> filters = new ArrayList<PropertyFilter>();

	public List<PropertyFilter> getFilters() {
		return filters;
	}
	
	public PropertyCriteria add(PropertyExpression expression) {
		filters.add(expression.toFilter());
		return this;
	}
}
