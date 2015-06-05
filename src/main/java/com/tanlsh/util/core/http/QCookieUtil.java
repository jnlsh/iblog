package com.tanlsh.util.core.http;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tanlsh.util.core.data.QStringUtil;

/**
 * cookie工具类<br>
 * 1.添加cookie，浏览器关闭后失效<br>
 * 2.删除某一个cookie<br>
 * 3.删除所有cookie<br>
 * 4.设置cookie，并设置有效期<br>
 * 5.获取一个cookie的值<br>
 * @author qiaowenbin
 * @version 0.0.2.20141109
 * @history
 * 	0.0.2.20141109<br>
 * 	0.0.1.20140430<br>
 */
public class QCookieUtil {
	
	/**
	 * 添加cookie，浏览器关闭后失效
	 * @param response
	 * @param name
	 * @param value
	 */
	public static void addCookie(HttpServletResponse response, String name, String value){
		setCookie(response, name, value, -1);
	}
	
	/**
	 * 删除某一个cookie
	 * @param response
	 * @param name
	 */
	public static void removeCookie(HttpServletResponse response, String name){
		setCookie(response, name, null, 0);
	}
	
	/**
	 * 删除所有cookie
	 * @param request
	 * @param response
	 */
	public static void removeAllCookies(HttpServletRequest request, HttpServletResponse response){
		Cookie[] cookies = request.getCookies();
		for(Cookie cookie : cookies){
			setCookie(response, cookie.getName(), null, 0);
		}
	}
	
	/**
	 * 设置cookie，并设置有效期
	 * maxage：负数浏览器关闭，正数单位是秒，0表示删除
	 * @param response
	 * @param name
	 * @param value
	 */
	public static void setCookie(HttpServletResponse response, String name, String value, int maxage){
		if(response != null && QStringUtil.allNotEmpty(new String[]{name, value})){
			Cookie cookie = new Cookie(name, value);
			cookie.setMaxAge(maxage);
			cookie.setPath("/");
			
			response.addCookie(cookie);
		}
	}
	
	/**
	 * 获取一个cookie的值
	 * @param request
	 * @param name
	 * @return
	 */
	public static String getValue(HttpServletRequest request, String name){
		if(QStringUtil.notEmpty(name) && request != null && request.getCookies() != null){
			for(Cookie cookie : request.getCookies()){
				if(name.equals(cookie.getName())){
					return cookie.getValue();
				}
			}
		}
		
		return null;
	}
	
}
