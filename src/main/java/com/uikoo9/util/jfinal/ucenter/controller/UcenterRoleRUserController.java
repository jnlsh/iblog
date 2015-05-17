package com.uikoo9.util.jfinal.ucenter.controller;

import com.uikoo9.util.jfinal.QController;
import com.uikoo9.util.jfinal.ucenter.model.UcenterRoleRUserModel;
import com.uikoo9.util.plugin.json.QJsonUtil;

/**
 * UcenterRoleRUserController
 * @author qiaowenbin
 */
public class UcenterRoleRUserController extends QController{
	
	/**
	 * 跳转到首页 
	 */
	public void index(){
		setAttr("qpage", list(UcenterRoleRUserModel.class));
		render("/WEB-INF/classes/com/uikoo9/util/jfinal/view/ucenter/ucenter-role-r-user-index.ftl");
	}
	
	/**
	 * 跳转到保存修改页 
	 */
	public void savep(){
		setAttr("row", getRow(UcenterRoleRUserModel.class));
		render("/WEB-INF/classes/com/uikoo9/util/jfinal/view/ucenter/ucenter-role-r-user-input.ftl");
	}
	
	/**
	 * 保存或修改
	 */
	public void save(){
		String validate = validate();
		if(validate == null){
			renderJson(save(UcenterRoleRUserModel.class));
		}else{
			renderJson(QJsonUtil.error(validate));
		}
	}
	
	/**
	 * 删除一条或多条
	 */
	public void del(){
		renderJson(del(UcenterRoleRUserModel.class));
	}
	
}
