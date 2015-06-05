package com.tanlsh.fore.controller;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.tanlsh.manage.blog.model.BlogArticleModel;
import com.tanlsh.util.core.annotation.ControllerUrl;
import com.tanlsh.util.jfinal.ucenter.model.UcenterMenuModel;

/**
 * 用户中心-首页controller
 * @author uikoo9
 */
@ControllerUrl("/")
public class IndexController extends Controller{
	
	/**
	 * 跳转到首页 
	 */
	public void index(){
		String sql = "select count(*) from t_blog_comment t1 where t1.blog_comment_parent_id=0 and not exists(select 1 from t_blog_comment t2 where t2.blog_comment_parent_id=t1.id)";
		setAttr("msgcount", Db.queryLong(sql));
		
		render("/WEB-INF/view/fore/home/home-index.ftl");
	}
	
	/**
	 * 跳转到版本更新
	 */
	public void version(){
		render("/WEB-INF/view/fore/home/home-version.ftl");
	}
	
	/**
	 * 跳转到关于我
	 */
	public void me(){
		setAttr("blog", BlogArticleModel.dao.findByCode("about-me"));
		render("/WEB-INF/view/fore/home/home-me.ftl");
	}

	/**
	 * 跳转到后台管理页面
	 */
	public void manage(){
		setAttr("menus", UcenterMenuModel.dao.findAllByCache());
		render("/WEB-INF/classes/com/tanlsh/util/jfinal/view/common/manage.ftl");
	}
	
}
