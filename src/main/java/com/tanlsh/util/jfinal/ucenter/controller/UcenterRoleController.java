package com.tanlsh.util.jfinal.ucenter.controller;

import com.jfinal.plugin.activerecord.Db;
import com.tanlsh.util.jfinal.BaseController;
import com.tanlsh.util.jfinal.ucenter.model.UcenterRoleModel;
import com.tanlsh.util.jfinal.ucenter.model.UcenterRoleRUserModel;
import com.tanlsh.util.jfinal.ucenter.model.UcenterUserModel;
import com.tanlsh.util.jfinal.ucenter.service.RoleService;
import com.tanlsh.util.plugin.json.MyJsonUtil;
import com.tanlsh.util.plugin.tree.MyTree;
import com.tanlsh.util.plugin.tree.MyTreeCheck;

/**
 * UcenterRoleController
 * @author 
 */
public class UcenterRoleController extends BaseController{
	
	private RoleService roleService = RoleService.getInstance();
	
	/**
	 * 跳转到首页 
	 */
	public void index(){
		setAttr("qpage", list(UcenterRoleModel.class));
		render("/WEB-INF/classes/com/tanlsh/util/jfinal/view/ucenter/ucenter-role-index.ftl");
	}
	
	/**
	 * 跳转到保存修改页 
	 */
	public void savep(){
		setAttr("row", getRow(UcenterRoleModel.class));
		render("/WEB-INF/classes/com/tanlsh/util/jfinal/view/ucenter/ucenter-role-input.ftl");
	}
	
	/**
	 * 保存或修改
	 */
	public void save(){
		String validate = validate();
		if(validate == null){
			renderJson(save(UcenterRoleModel.class));
		}else{
			renderJson(MyJsonUtil.error(validate));
		}
	}
	
	/**
	 * 删除一条或多条
	 */
	public void del(){
		renderJson(del(UcenterRoleModel.class, UcenterRoleRUserModel.class, "ucenter_role_id"));
	}
	
	/**
	 * 跳转到设置用户页面
	 */
	public void setUser(){
		Integer roleId = getParaToInt(0);
		setAttr("roleid", roleId);
		UcenterRoleModel role = UcenterRoleModel.dao.findById(roleId);
		setAttr("rls", role.users());
		String sql = "select * from t_ucenter_user uu where uu.id not in (select distinct(rl.ucenter_user_id) from t_ucenter_role_r_user rl) order by uu.ucenter_user_name";
		setAttr("outusers", UcenterUserModel.dao.find(sql));
		
		render("/WEB-INF/classes/com/tanlsh/util/jfinal/view/ucenter/ucenter-role-set-user.ftl");
	}
	
	/**
	 * 添加用户
	 */
	public void addUser(){
		renderJson(roleService.addUser(getParaToInt("roleid"), getPara("userids"), (UcenterUserModel)getAttr("user")));
	}
	
	/**
	 * 移除用户
	 */
	public void removeUser(){
		renderJson(roleService.removeUser(getPara("rlids")));
	}
	
	/**
	 * 跳转到设置Url页面
	 */
	public void setUrl(){
		final int roleId = getParaToInt();
		
		setAttr("roleid", roleId);
		setAttr("tree", new MyTree(0, "/", "根菜单", null, new MyTreeCheck(){
			@Override
			public boolean isCheck(int menuId) {
				String sql = "select count(*) from t_ucenter_role_r_menu where ucenter_role_id=? and ucenter_menu_id=?";
				long count = Db.queryLong(sql, roleId, menuId);
				
				return count == 1 ? true : false;
			}
		}));
		
		render("/WEB-INF/classes/com/tanlsh/util/jfinal/view/ucenter/ucenter-role-set-url.ftl");
	}
	
	/**
	 * 添加或删除Url
	 */
	public void saveUrl(){
		renderJson(roleService.saveUrl(getParaToInt("roleid"), getPara("ids"), (UcenterUserModel)getAttr("user")));
	}
	
}
