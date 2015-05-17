package com.uikoo9.util.jfinal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;
import com.jfinal.core.Controller;
import com.uikoo9.util.core.data.QStringUtil;
import com.uikoo9.util.core.file.QDocumentUtil;
import com.uikoo9.util.core.file.QPropertiesUtil;
import com.uikoo9.util.core.http.QCookieUtil;
import com.uikoo9.util.core.http.QRequestUtil;
import com.uikoo9.util.function.QCacheUtil;
import com.uikoo9.util.jfinal.ucenter.model.UcenterUserModel;

/**
 * 拦截器
 * @author qiaowenbin
 * @version 0.0.6.20140909
 */
public class QInterceptor implements Interceptor{

	@Override
	public void intercept(ActionInvocation ai) {
		// init sth
		init(ai.getController());
		
		// can visit
		if(normalVisit(ai) || authVisit(ai)){
			ai.invoke();
		}else{
			ai.getController().render("/WEB-INF/classes/com/uikoo9/util/jfinal/view/common/error.ftl");
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
		String cookieUserId = QCookieUtil.getValue(controller.getRequest(), "uikoo9userid");
		if(QStringUtil.notEmpty(cookieUserId)){
			Object valueObject = QCacheUtil.getFromEHCache(cookieUserId);
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
		
		Object devModeObject = QCacheUtil.getFromEHCache("devMode");
		if(devModeObject == null){
			devMode = QPropertiesUtil.getBoolean("jfinal.dev_mode");
			QCacheUtil.putToEHCache("devMode", devMode);
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

		Object baseObject = QCacheUtil.getFromEHCache("base");
		if(baseObject == null){
			if(QPropertiesUtil.getBoolean("jfinal.dev_mode")){
				base = QRequestUtil.getHttpPath(controller.getRequest());
			}else{
				base = "";
			}
			QCacheUtil.putToEHCache("base", base);
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
		controller.setAttr("title", QPropertiesUtil.get("web.html.title"));
	}
	
	/**
	 * 是否可以访问正常路径
	 * @param ai
	 * @return
	 */
	private boolean normalVisit(ActionInvocation ai){
		String actionKey = ai.getActionKey();
		String urlPara = ai.getController().getPara();
		
		if("/".equals(actionKey) && QStringUtil.isEmpty(urlPara)){
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
			Object pathsObject = QCacheUtil.getFromEHCache("paths");
			if(pathsObject == null){
				paths = QDocumentUtil.getTagValue("jfinal-auth.xml", "url");
				QCacheUtil.putToEHCache("paths", paths);
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
				if(QStringUtil.isIn(userName, "admin", "uikoo9")){
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
		Object urlsObject = QCacheUtil.getFromEHCache("auths");
		if(urlsObject == null){
			urls = QJfinalUtil.initAuths();
		}else{
			urls = (Map<String, String>) urlsObject;
		}
		
		return urls;
	}
	
}
