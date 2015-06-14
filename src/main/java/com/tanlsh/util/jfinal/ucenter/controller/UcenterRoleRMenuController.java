package com.tanlsh.util.jfinal.ucenter.controller;

import com.tanlsh.util.core.annotation.ControllerUrl;
import com.tanlsh.util.jfinal.BaseController;
import com.tanlsh.util.jfinal.ucenter.model.UcenterRoleRMenuModel;
import com.tanlsh.util.plugin.json.MyJsonUtil;

/**
 * UcenterRoleRMenuController
 * @author 
 */
@ControllerUrl("/ucenter/role/r/menu")
public class UcenterRoleRMenuController extends BaseController{
	
	/**
	 * 跳转到首页 
	 */
	public void index(){
		setAttr("qpage", list(UcenterRoleRMenuModel.class));
		render("/WEB-INF/classes/com/tanlsh/util/jfinal/view/ucenter/ucenter-role-r-menu-index.ftl");
	}
	
	/**
	 * 跳转到保存修改页 
	 */
	public void savep(){
		setAttr("row", getRow(UcenterRoleRMenuModel.class));
		render("/WEB-INF/classes/com/tanlsh/util/jfinal/view/ucenter/ucenter-role-r-menu-input.ftl");
	}
	
	/**
	 * 保存或修改
	 */
	public void save(){
		String validate = validate();
		if(validate == null){
			renderJson(save(UcenterRoleRMenuModel.class));
		}else{
			renderJson(MyJsonUtil.error(validate));
		}
	}
	
	/**
	 * 删除一条或多条
	 */
	public void del(){
		renderJson(del(UcenterRoleRMenuModel.class));
	}
	
}
