package com.tanlsh.fore.controller;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.tanlsh.manage.blog.model.BlogArticleModel;
import com.tanlsh.manage.blog.model.BlogCommentModel;
import com.tanlsh.manage.blog.model.BlogTypeModel;
import com.tanlsh.util.core.annotation.ControllerUrl;
import com.tanlsh.util.core.data.StringUtil;

@ControllerUrl("/blog")
public class BlogController extends Controller{
	
	/**
	 * 跳转到博客评论列表页面
	 */
	public void msg(){
		String sql = "select t1.* from t_blog_comment t1 where t1.blog_comment_parent_id=0 and not exists(select 1 from t_blog_comment t2 where t2.blog_comment_parent_id=t1.id)";
		setAttr("msgs", BlogCommentModel.dao.find(sql));
		
		render("/WEB-INF/view/fore/blog/blog-msg.ftl");
	}
	
	/**
	 * 跳转到博客列表首页 
	 */
	public void list(){
		setAttr("blogTypes", BlogTypeModel.dao.findAllByCache());
		
		try {
			Integer typeId = getParaToInt(0);
			if(typeId != null){
				setAttr("blogTypeId", typeId);
				setAttr("blogs", Db.find("select * from t_blog_article tba where tba.blog_type_id=? order by cdate desc", typeId));
			}else{
				setAttr("blogs", BlogArticleModel.dao.find("select * from t_blog_article tba order by cdate desc"));
			}
			
			render("/WEB-INF/view/fore/blog/blog-list.ftl");
		} catch (Exception e) {
			redirect("/blog/list");
		}
	}
	
	/**
	 * 跳转到博客编辑页面
	 */
	public void edit(){
		setAttr("blogTypes", BlogTypeModel.dao.findAllByCache());
		if(getParaToInt(0) != null){
			setAttr("blog", BlogArticleModel.dao.findById(getParaToInt(0)));
		}
		
		render("/WEB-INF/view/fore/blog/blog-edit.ftl");
	}
	
	/**
	 * 跳转到博客详情页面
	 */
	public void detail(){
		try {
			String blogCode = getPara();
			if(StringUtil.notEmpty(blogCode)){
				BlogArticleModel blog = BlogArticleModel.dao.findByCode(blogCode);
				blog.set("blog_article_read_times", ((Integer)blog.get("blog_article_read_times") + 1)).update();
				
				setAttr("blog", blog);
				setAttr("prevBlog", getBlog("prev", blog));
				setAttr("nextBlog", getBlog("next", blog));
				
				render("/WEB-INF/view/fore/blog/blog-detail.ftl");
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		redirect("/blog/list");
	}
	private BlogArticleModel getBlog(String type, BlogArticleModel blog){
		BlogArticleModel theBlog = null;
		try {
			String sql = null;
			if("prev".equals(type)) sql = "select * from t_blog_article where id = (select max(id) from t_blog_article where id<? and blog_type_id=?)";
			if("next".equals(type)) sql = "select * from t_blog_article where id = (select min(id) from t_blog_article where id>? and blog_type_id=?)";
			theBlog = BlogArticleModel.dao.findFirst(sql, blog.getInt("id"), blog.getInt("blog_type_id"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return theBlog != null ? theBlog : new BlogArticleModel().set("id", 0).set("blog_article_title", "没有了~").set("blog_article_code", "null");
	}
	
}
