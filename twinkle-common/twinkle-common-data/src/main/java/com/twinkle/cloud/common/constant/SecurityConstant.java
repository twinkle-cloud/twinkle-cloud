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
public interface SecurityConstant {
	short SEC_USER_STATUS_INITIAL = 0;
	short SEC_USER_STATUS_ACTIVE = 1;

	String ROLE_MARK_SCORE = "KYKHCP";
	String ROLE_AUDIT_SCORE = "KYKHFH";

	String RESOURCE_TYPE_MENU = "menu";
	String RESOURCE_TYPE_BTN = "button";
	String EX_SECURITY_CODE = "40100";
	String EX_TOKEN_ERROR_CODE = "40101";
	// 用户token异常
	String EX_USER_INVALID_CODE = "40102";
	String EX_TOKEN_MISS = "40103";
	String EX_USER_PASS_INVALID_CODE = "40001";
	// 客户端token异常
	String EX_CLIENT_INVALID_CODE = "40131";
	String EX_CLIENT_FORBIDDEN_CODE = "40331";
	String EX_OTHER_CODE = "500";
	String CONTEXT_KEY_USER_ID = "currentUserId";
	String CONTEXT_KEY_USERNAME = "currentUserName";
	String CONTEXT_KEY_USER_NAME = "currentUser";
	String CONTEXT_KEY_USER_TOKEN = "currentUserToken";
	String JWT_KEY_USER_ID = "userId";
	String JWT_KEY_NAME = "name";

	/**
	 * 角色前缀
	 */
	String ROLE = "ROLE_";
	/**
	 * 前缀
	 */
	String PROJECT_PREFIX = "twinkle_";

	/**
	 * oauth 相关前缀
	 */
	String OAUTH_PREFIX = "oauth:";
	/**
	 * 项目的license
	 */
	String PROJECT_LICENSE = "made by twinkle tech";

	/**
	 * 内部
	 */
	String FROM_IN = "Y";

	/**
	 * 标志
	 */
	String FROM = "from";

	/**
	 * 手机号登录URL
	 */
	String MOBILE_TOKEN_URL = "/mobile/token";

	/**
	 * 默认登录URL
	 */
	String OAUTH_TOKEN_URL = "/oauth/token";

	/**
	 * grant_type
	 */
	String REFRESH_TOKEN = "refresh_token";

	/**
	 * oauth 客户端信息
	 */
	String CLIENT_DETAILS_KEY = PROJECT_PREFIX + OAUTH_PREFIX + "client:details";

	/**
	 * {bcrypt} 加密的特征码
	 */
	String BCRYPT = "{bcrypt}";
	/**
	 * sys_oauth_client_details 表的字段，不包括client_id、client_secret
	 */
	String CLIENT_FIELDS = "client_id, CONCAT('{noop}',client_secret) as client_secret, resource_ids, scope, "
			+ "authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, "
			+ "refresh_token_validity, additional_information, autoapprove";

	/**
	 * JdbcClientDetailsService 查询语句
	 */
	String BASE_FIND_STATEMENT = "select " + CLIENT_FIELDS
			+ " from sys_oauth_client_details";

	/**
	 * 默认的查询语句
	 */
	String DEFAULT_FIND_STATEMENT = BASE_FIND_STATEMENT + " order by client_id";

	/**
	 * 按条件client_id 查询
	 */
	String DEFAULT_SELECT_STATEMENT = BASE_FIND_STATEMENT + " where client_id = ?";

	/***
	 * 资源服务器默认bean名称
	 */
	String RESOURCE_SERVER_CONFIGURER = "resourceServerConfigurerAdapter";

	/**
	 * 用户ID字段
	 */
	String DETAILS_USER_ID = "user_id";

	/**
	 * 用户名字段
	 */
	String DETAILS_USERNAME = "login_name";

	String DETAILS_TENANT_ID = "tenant_id";

	/**
	 * 用户部门字段
	 */
	String DETAILS_DEPT_ID = "dept_id";

	/**
	 * 协议字段
	 */
	String DETAILS_LICENSE = "license";
}

