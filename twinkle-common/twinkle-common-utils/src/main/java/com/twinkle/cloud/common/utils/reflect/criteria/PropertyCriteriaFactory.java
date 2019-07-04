package com.twinkle.cloud.common.utils.reflect.criteria;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     11/14/18 5:12 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
public class PropertyCriteriaFactory {
	public static PropertyCriteria create() {
		return new PropertyCriteriaImpl();
	}
}
