package com.uikoo9.util.external;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import com.uikoo9.util.core.data.QStringUtil;
import com.uikoo9.util.function.QEncodeUtil;

/**
 * 微信工具类
 * 1.校验合法性<br>
 * @author qiaowenbin
 * @version 0.0.1.2015020601
 * @history
 * 	0.0.1.2015020601<br>
 */
public class QWeixinUtil {
	
	/**
	 * 微信token校验
	 * @param request
	 * @param token
	 * @return
	 */
	public static String checkToken(HttpServletRequest request, String token){
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		
		return checkToken(signature, timestamp, nonce, echostr, token);
	}
	
	/**
	 * 微信token校验
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @param echostr
	 * @param token
	 * @return
	 */
	public static String checkToken(String signature, String timestamp, String nonce, String echostr, String token){
		if(QStringUtil.allNotEmpty(signature, timestamp, nonce, echostr, token)){
			StringBuilder sb = new StringBuilder();
			String[] tmp = {token, timestamp, nonce};
			Arrays.sort(tmp);
			for(String s : tmp){
				sb.append(s);
			}
			
			String pwd = QEncodeUtil.sha1(sb.toString());
			if(signature.equals(pwd)) return echostr;
		}
		
		return null;
	}
	
}
