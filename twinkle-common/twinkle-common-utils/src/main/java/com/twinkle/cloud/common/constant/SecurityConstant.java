/**
 * Project Name:liz-common-com.twinkle.cloud.common.asm.utils
 * File Name:SecurityConstant.java
 * Package Name:com.gemii.lizcloud.common.constant
 * Date:Jul 12, 20165:23:42 PM
 * Copyright (c) 2016, chenxj All Rights Reserved.
 *
*/

package com.twinkle.cloud.common.constant;
/**
 * ClassName:SecurityConstant <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     Jul 12, 2016 5:23:42 PM <br/>
 * @author   chenxj
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
public class SecurityConstant {
	public final static short SEC_USER_STATUS_INITIAL = 0;
	public final static short SEC_USER_STATUS_ACTIVE = 1;

	public final static String ROLE_MARK_SCORE = "KYKHCP";
	public final static String ROLE_AUDIT_SCORE = "KYKHFH";

	public final static String RESOURCE_TYPE_MENU = "menu";
	public final static String RESOURCE_TYPE_BTN = "button";
	public static final String EX_TOKEN_ERROR_CODE = "40101";
	// 用户token异常
	public static final String EX_USER_INVALID_CODE = "40102";
	public static final String EX_TOKEN_MISS = "40103";
	public static final String EX_USER_PASS_INVALID_CODE = "40001";
	// 客户端token异常
	public static final String EX_CLIENT_INVALID_CODE = "40131";
	public static final String EX_CLIENT_FORBIDDEN_CODE = "40331";
	public static final String EX_OTHER_CODE = "500";
	public static final String CONTEXT_KEY_USER_ID = "currentUserId";
	public static final String CONTEXT_KEY_USERNAME = "currentUserName";
	public static final String CONTEXT_KEY_USER_NAME = "currentUser";
	public static final String CONTEXT_KEY_USER_TOKEN = "currentUserToken";
	public static final String JWT_KEY_USER_ID = "userId";
	public static final String JWT_KEY_NAME = "name";
}

