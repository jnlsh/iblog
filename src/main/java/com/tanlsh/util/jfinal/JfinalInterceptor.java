package com.tanlsh.util.jfinal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;
import com.jfinal.core.Controller;
import com.tanlsh.util.core.data.StringUtil;
import com.tanlsh.util.core.file.DocumentUtil;
import com.tanlsh.util.core.file.PropertiesUtil;
import com.tanlsh.util.core.http.CookieUtil;
import com.tanlsh.util.core.http.RequestUtil;
import com.tanlsh.util.function.CacheUtil;
import com.tanlsh.util.jfinal.ucenter.model.UcenterUserModel;

/**
 * 拦截器
 * @author 
 * @version 0.0.6.20140909
 */
public class JfinalInterceptor implements Interceptor{

	@Override
	public void intercept(ActionInvocation ai) {
		// init sth
		init(ai.getController());
		
		// can visit
		if(normalVisit(ai) || authVisit(ai)){
			ai.invoke();
		}else{
			ai.getController().render("/WEB-INF/classes/com/tanlsh/util/jfinal/view/common/error.ftl");
		}
	}
	
	/**
	 * 缓存一些变量
	 * @param controller
	 */
	public void init(Controller controller){
		initUser(controller);
		initDevMode(controller);
		initBasePath(controller);
		initHtmlTitle(controller);
	}
	
	/**
	 * 初始化用户
	 * @param ai
	 * @return
	 */
	private void initUser(Controller controller){
		String cookieUserId = CookieUtil.getValue(controller.getRequest(), "uikoo9userid");
		if(StringUtil.notEmpty(cookieUserId)){
			Object valueObject = CacheUtil.getFromEHCache(cookieUserId);
			if(valueObject != null){
				controller.setAttr("user", (UcenterUserModel) valueObject);
			}
		}
	}
	
	/**
	 * 缓存开发模式
	 * @param controller
	 */
	private void initDevMode(Controller controller) {
		Boolean devMode = false;
		
		Object devModeObject = CacheUtil.getFromEHCache("devMode");
		if(devModeObject == null){
			devMode = PropertiesUtil.getBoolean("jfinal.dev_mode");
			CacheUtil.putToEHCache("devMode", devMode);
		}else{
			devMode = (Boolean) devModeObject;
		}
		
		controller.setAttr("devMode", devMode);
	}
	
	/**
	 * 缓存项目根路径
	 * @param controller
	 */
	private void initBasePath(Controller controller){
		String base = null;

		Object baseObject = CacheUtil.getFromEHCache("base");
		if(baseObject == null){
			if(PropertiesUtil.getBoolean("jfinal.dev_mode")){
				base = RequestUtil.getHttpPath(controller.getRequest());
			}else{
				base = "";
			}
			CacheUtil.putToEHCache("base", base);
		}else{
			base = (String) baseObject;
		}
		
		controller.setAttr("base", base);
	}
	
	/**
	 * 初始化页面title
	 * @param controller
	 */
	private void initHtmlTitle(Controller controller){
		controller.setAttr("title", PropertiesUtil.get("web.html.title"));
	}
	
	/**
	 * 是否可以访问正常路径
	 * @param ai
	 * @return
	 */
	private boolean normalVisit(ActionInvocation ai){
		String actionKey = ai.getActionKey();
		String urlPara = ai.getController().getPara();
		
		if("/".equals(actionKey) && StringUtil.isEmpty(urlPara)){
			return true;
		}else{
	        for(String path : getPaths()){
	        	if(actionKey.equals(path)) return true;
	        }
			
			return false;
		}
	}
	@SuppressWarnings("unchecked")
	private List<String> getPaths(){
		List<String> paths = new ArrayList<String>();
		
		try {
			Object pathsObject = CacheUtil.getFromEHCache("paths");
			if(pathsObject == null){
				paths = DocumentUtil.getTagValue("jfinal-auth.xml", "url");
				CacheUtil.putToEHCache("paths", paths);
			}else{
				paths = (List<String>) pathsObject;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return paths;
	}
	
	/**
	 * 是否有权限访问
	 * @param ai
	 * @return
	 */
	public boolean authVisit(ActionInvocation ai) {
		UcenterUserModel user = ai.getController().getAttr("user");
		
		if(user == null){
			return false;
		}else{
			try {
				String userName = user.getStr("ucenter_user_name");
				if(StringUtil.isIn(userName, "admin", "tanlsh")){
					return true;
				}
				
				String actionKey = ai.getActionKey();
				String urls = getAuths().get("user-" + userName);
				for(String url : urls.split(",")){
					if(actionKey.equals(url)){
						return true;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return false;
		}
	}
	@SuppressWarnings("unchecked")
	private Map<String, String> getAuths(){
		Map<String, String> urls = null;
		Object urlsObject = CacheUtil.getFromEHCache("auths");
		if(urlsObject == null){
			urls = MyJfinalUtil.initAuths();
		}else{
			urls = (Map<String, String>) urlsObject;
		}
		
		return urls;
	}
	
}
