package com.twinkle.cloud.common.context;

import com.twinkle.cloud.common.constant.SecurityConstant;
import com.twinkle.cloud.common.utils.StringHelper;

import java.util.HashMap;
import java.util.Map;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     11/13/18 3:03 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
public class BaseContextHandler {
    private static ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<Map<String, Object>>();

    public static void set(String key, Object value) {
        Map<String, Object> map = threadLocal.get();
        if (map == null) {
            map = new HashMap<>();
            threadLocal.set(map);
        }
        map.put(key, value);
    }

    public static Object get(String key) {
        Map<String, Object> map = threadLocal.get();
        if (map == null) {
            map = new HashMap<>();
            threadLocal.set(map);
        }
        return map.get(key);
    }

    public static String getUserID() {
        Object value = get(SecurityConstant.CONTEXT_KEY_USER_ID);
        return returnObjectValue(value);
    }

    public static String getUsername() {
        Object value = get(SecurityConstant.CONTEXT_KEY_USERNAME);
        return returnObjectValue(value);
    }


    public static String getName() {
        Object value = get(SecurityConstant.CONTEXT_KEY_USER_NAME);
        return StringHelper.getObjectValue(value);
    }

    public static String getToken() {
        Object value = get(SecurityConstant.CONTEXT_KEY_USER_TOKEN);
        return StringHelper.getObjectValue(value);
    }

    public static void setToken(String token) {
        set(SecurityConstant.CONTEXT_KEY_USER_TOKEN, token);
    }

    public static void setName(String name) {
        set(SecurityConstant.CONTEXT_KEY_USER_NAME, name);
    }

    public static void setUserID(String userID) {
        set(SecurityConstant.CONTEXT_KEY_USER_ID, userID);
    }

    public static void setUsername(String username) {
        set(SecurityConstant.CONTEXT_KEY_USERNAME, username);
    }

    private static String returnObjectValue(Object value) {
        return value == null ? null : value.toString();
    }

    public static void remove() {
        threadLocal.remove();
    }
}
