package com.tanlsh.manage.blog.controller;

import com.tanlsh.manage.blog.model.BlogArticleModel;
import com.tanlsh.manage.blog.model.BlogTypeModel;
import com.tanlsh.util.core.annotation.ControllerUrl;
import com.tanlsh.util.jfinal.BaseController;
import com.tanlsh.util.plugin.json.QJsonUtil;

/**
 * BlogArticleController
 * @author qiaowenbin
 */
@ControllerUrl("/blog/article")
public class BlogArticleController extends BaseController{
	
	/**
	 * 跳转到首页 
	 */
	public void index(){
		setAttr("qpage", listBySql(getParaMap(), " (select ba.*,bt.blog_type_name tname from t_blog_article ba, t_blog_type bt where ba.blog_type_id=bt.id ) as tba ", "tba"));
		render("/WEB-INF/view/manage/blog/blog-article-index.ftl");
	}
	
	/**
	 * 跳转到保存修改页 
	 */
	public void savep(){
		setAttr("blogTypes", BlogTypeModel.dao.findAllByCache());
		setAttr("row", getRow(BlogArticleModel.class));
		
		render("/WEB-INF/view/manage/blog/blog-article-input.ftl");
	}
	
	/**
	 * 保存或修改
	 */
	public void save(){
		String validate = validate();
		if(validate == null){
			renderJson(save(BlogArticleModel.class));
		}else{
			renderJson(QJsonUtil.error(validate));
		}
	}
	
	/**
	 * 删除一条或多条
	 */
	public void del(){
		renderJson(del(BlogArticleModel.class));
	}
	
}
