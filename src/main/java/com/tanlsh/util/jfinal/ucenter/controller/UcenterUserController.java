package com.tanlsh.util.jfinal.ucenter.controller;

import com.jfinal.plugin.activerecord.Record;
import com.tanlsh.util.core.file.QPropertiesUtil;
import com.tanlsh.util.function.EncodeUtil;
import com.tanlsh.util.jfinal.BaseController;
import com.tanlsh.util.jfinal.ucenter.model.UcenterUserModel;
import com.tanlsh.util.plugin.contants.QContants;
import com.tanlsh.util.plugin.contants.QContantsUtil;
import com.tanlsh.util.plugin.json.QJsonUtil;

/**
 * 用户中心-用户controller
 * @author uikoo9
 */
public class UcenterUserController extends BaseController{
	
	/**
	 * 跳转到首页 
	 */
	public void index(){
		setAttr("qpage", list(UcenterUserModel.class));
		if(QPropertiesUtil.getPropertyToBoolean(QPropertiesUtil.config, "jfinal.auth.use_inside_user_page")){
			render("/WEB-INF/classes/com/tanlsh/util/jfinal/view/ucenter/ucenter-user-index.ftl");
		}else{
			render("/WEB-INF/view/manage/ucenter/ucenter-user-index.ftl");
		}
	}
	
	/**
	 * 跳转到保存修改页 
	 */
	public void savep(){
		setAttr("usertypes", QContantsUtil.list(QContants.USER_TYPE));
		
		setAttr("row", getRow(UcenterUserModel.class));
		if(QPropertiesUtil.getPropertyToBoolean(QPropertiesUtil.config, "jfinal.auth.use_inside_user_page")){
			render("/WEB-INF/classes/com/tanlsh/util/jfinal/view/ucenter/ucenter-user-input.ftl");
		}else{
			render("/WEB-INF/view/manage/ucenter/ucenter-user-input.ftl");
		}
	}
	
	/**
	 * 保存或修改
	 */
	public void save(){
		String validate = validate();
		if(validate == null){
			renderJson(save(UcenterUserModel.class));
		}else{
			renderJson(QJsonUtil.error(validate));
		}
	}
	
	/* (non-Javadoc)
	 * @see com.tanlsh.util.jfinal.QController#initRecord(com.jfinal.plugin.activerecord.Record)
	 */
	public Record initRecord(Record record){
		String pwd = record.getStr("ucenter_user_key");
		try {
			record.set("ucenter_user_key", EncodeUtil.md5Encrypt(pwd));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return record;
	}
	
	/**
	 * 删除一条或多条
	 */
	public void del(){
		renderJson(del(UcenterUserModel.class));
	}
	
}
