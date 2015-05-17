package com.uikoo9.util.jfinal.ucenter.controller;

import com.uikoo9.util.jfinal.QController;
import com.uikoo9.util.jfinal.ucenter.service.LoginService;

/**
 * 用户中心-登录controller
 * @author uikoo9
 */
public class UcenterLoginController extends QController{
	
	private LoginService loginService = LoginService.getInstance();
	
	/**
	 * 用户登录
	 */
	public void login(){
		renderJson(loginService.login(getParaMap(), getResponse()));
	}
	
	/**
	 * 退出登录
	 */
	public void logout(){
		loginService.logout(getRequest(), getResponse());
		redirect("/");
	}
	
	/**
	 * 跳转到修改密码页面
	 */
	public void modifyPwdp(){
		render("/WEB-INF/classes/com/uikoo9/util/jfinal/view/ucenter/ucenter-user-modifypwd.ftl");
	}
	
	/**
	 * 修改密码
	 */
	public void modifyPwd(){
		renderJson(loginService.modifyPwd(getParaMap(), getRequest()));
	}
	
}
