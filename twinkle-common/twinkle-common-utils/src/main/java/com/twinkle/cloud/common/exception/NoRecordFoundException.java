/**
 * Project Name:liz-common-com.twinkle.cloud.common.asm.utils
 * File Name:NoRecordFoundException.java
 * Package Name:com.twinkle.cloud.common.com.twinkle.cloud.common.asm.block.exception
 * Date:Jul 12, 20165:25:02 PM
 * Copyright (c) 2016, chenxj All Rights Reserved.
 *
*/

package com.twinkle.cloud.common.exception;

/**
 * ClassName:NoRecordFoundException <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: Jul 12, 2016 5:25:02 PM <br/>
 * 
 * @author chenxj
 * @version
 * @since JDK 1.8
 * @see
 */
public class NoRecordFoundException extends RuntimeException {

	/**
	 * serialVersionUID:TODO Description.
	 */
	private static final long serialVersionUID = -7563408365868374668L;

	public NoRecordFoundException() {
		super();
	}

	public NoRecordFoundException(String message) {
		super(message);
	}

	public NoRecordFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoRecordFoundException(Throwable cause) {
		super(cause);
	}
}
