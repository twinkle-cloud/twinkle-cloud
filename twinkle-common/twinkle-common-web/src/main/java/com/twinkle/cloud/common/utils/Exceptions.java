/**
 * Copyright (c) 2005-2012 springside.org.cn
 */
package com.twinkle.cloud.common.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;

/**
 * 关于异常的工具类.
 * @author calvin
 * @version 2013-01-15
 */
public class Exceptions {

	/**
	 * 将CheckedException转换为UncheckedException.
	 */
	public static RuntimeException unchecked(Exception e) {
		if (e instanceof RuntimeException) {
			return (RuntimeException) e;
		} else {
			return new RuntimeException(e);
		}
	}

	/**
	 * 将ErrorStack转化为String.
	 */
	public static String getStackTraceAsString(Throwable e) {
		if (e == null){
			return "";
		}
		StringWriter stringWriter = new StringWriter();
		e.printStackTrace(new PrintWriter(stringWriter));
		return stringWriter.toString();
	}

	/**
	 * 判断异常是否由某些底层的异常引起.
	 */
	public static boolean isCausedBy(Exception ex, Class<? extends Exception>... causeExceptionClasses) {
		Throwable cause = ex.getCause();
		while (cause != null) {
			for (Class<? extends Exception> causeClass : causeExceptionClasses) {
				if (causeClass.isInstance(cause)) {
					return true;
				}
			}
			cause = cause.getCause();
		}
		return false;
	}

	/**
	 * 在request中获取异常类
	 * @param request
	 * @return 
	 */
	public static Throwable getThrowable(HttpServletRequest request){
		Throwable ex = null;
		if (request.getAttribute("com.twinkle.cloud.common.asm.block.exception") != null) {
			ex = (Throwable) request.getAttribute("com.twinkle.cloud.common.asm.block.exception");
		} else if (request.getAttribute("javax.servlet.com.twinkle.cloud.common.asm.error.com.twinkle.cloud.common.asm.block.exception") != null) {
			ex = (Throwable) request.getAttribute("javax.servlet.com.twinkle.cloud.common.asm.error.com.twinkle.cloud.common.asm.block.exception");
		}
		return ex;
	}
	
}
