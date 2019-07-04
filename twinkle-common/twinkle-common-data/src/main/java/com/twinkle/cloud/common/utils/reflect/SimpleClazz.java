package com.twinkle.cloud.common.utils.reflect;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     11/14/18 5:12 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
public enum SimpleClazz {

	STRING(String.class),
	
	LONG(Long.class),
	
	INTEGER(Integer.class),
	
	DOUBLE(Double.class),
	
	DATE(Date.class),
	
	TIMESTAMP(Timestamp.class),
	
	BOOLEAN(Boolean.class),
	
	BIGDECIMAL(BigDecimal.class);
	
	Class<?> clazz;

	private SimpleClazz(Class<?> clazz) {
		this.clazz = clazz;
		
	}

	public Class<?> getClazz() {
		return clazz;
	}

}
