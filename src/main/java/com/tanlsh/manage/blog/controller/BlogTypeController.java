package com.tanlsh.manage.blog.controller;

import com.tanlsh.manage.blog.model.BlogArticleModel;
import com.tanlsh.manage.blog.model.BlogTypeModel;
import com.tanlsh.util.core.annotation.ControllerUrl;
import com.tanlsh.util.function.CacheUtil;
import com.tanlsh.util.jfinal.BaseController;
import com.tanlsh.util.plugin.json.QJson;
import com.tanlsh.util.plugin.json.QJsonUtil;

/**
 * BlogTypeController
 * @author qiaowenbin
 */
@ControllerUrl("/blog/type")
public class BlogTypeController extends BaseController{
	
	/**
	 * 跳转到首页 
	 */
	public void index(){
		setAttr("qpage", list(BlogTypeModel.class));
		render("/WEB-INF/view/manage/blog/blog-type-index.ftl");
	}
	
	/**
	 * 跳转到保存修改页 
	 */
	public void savep(){
		setAttr("row", getRow(BlogTypeModel.class));
		
		render("/WEB-INF/view/manage/blog/blog-type-input.ftl");
	}
	
	/**
	 * 保存或修改
	 */
	public void save(){
		String validate = validate();
		if(validate == null){
			QJson json = save(BlogTypeModel.class);
			if(QJsonUtil.TYPE_BS_SUCC.equals(json.getType())){
				CacheUtil.putToEHCache("blogTypes", BlogTypeModel.dao.findAll("order by blog_type_name"));
			}
			
			renderJson(json);
		}else{
			renderJson(QJsonUtil.error(validate));
		}
	}
	
	/**
	 * 删除一条或多条
	 */
	public void del(){
		QJson json = del(BlogTypeModel.class, BlogArticleModel.class, "blog_type_id");
		if(QJsonUtil.TYPE_BS_SUCC.equals(json.getType())){
			CacheUtil.putToEHCache("blogTypes", BlogTypeModel.dao.findAll("order by blog_type_name"));
		}
		
		renderJson(json);
	}
	
}
