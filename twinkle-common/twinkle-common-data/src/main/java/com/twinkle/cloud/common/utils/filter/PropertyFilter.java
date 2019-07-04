package com.twinkle.cloud.common.utils.filter;

import java.beans.PropertyDescriptor;

/**
 * @author wejia
 * @create Jul 6, 2016
 */
public interface PropertyFilter {

	public boolean isPermit(PropertyDescriptor property, Object object);
	
}
