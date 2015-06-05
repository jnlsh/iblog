package com.tanlsh.util.jfinal;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.Controller;
import com.jfinal.kit.PathKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.tanlsh.util.core.annotation.ControllerUrl;
import com.tanlsh.util.core.annotation.QTable;
import com.tanlsh.util.core.data.QStringUtil;
import com.tanlsh.util.core.file.QFileUtil;
import com.tanlsh.util.core.file.QPropertiesUtil;
import com.tanlsh.util.function.QCacheUtil;
import com.tanlsh.util.function.QDbUtil;
import com.tanlsh.util.jfinal.ucenter.controller.UcenterLoginController;
import com.tanlsh.util.jfinal.ucenter.controller.UcenterMenuController;
import com.tanlsh.util.jfinal.ucenter.controller.UcenterRoleController;
import com.tanlsh.util.jfinal.ucenter.controller.UcenterRoleRUserController;
import com.tanlsh.util.jfinal.ucenter.controller.UcenterUserController;
import com.tanlsh.util.jfinal.ucenter.model.UcenterMenuModel;
import com.tanlsh.util.jfinal.ucenter.model.UcenterRoleModel;
import com.tanlsh.util.jfinal.ucenter.model.UcenterRoleRMenuModel;
import com.tanlsh.util.jfinal.ucenter.model.UcenterRoleRUserModel;
import com.tanlsh.util.jfinal.ucenter.model.UcenterUserModel;

/**
 * jfinal util
 * @author qiaowenbin
 * @version 0.0.6.20140830
 */
public class QJfinalUtil {
	
	/**
	 * 初始化数据库信息和数据库映射
	 * @param me
	 * @param path
	 */
	@SuppressWarnings("unchecked")
	public static void initDbAndArp(Plugins me){
		C3p0Plugin c3p0Plugin = new C3p0Plugin(
				QPropertiesUtil.config.getProperty("db.url"), 
				QPropertiesUtil.config.getProperty("db.username"), 
				QPropertiesUtil.config.getProperty("db.password"));
		ActiveRecordPlugin arp = new ActiveRecordPlugin(c3p0Plugin);

		me.add(c3p0Plugin);
		me.add(arp);
		
		try {
			Connection con = null;
			try{
				con = QDbUtil.getCon(QPropertiesUtil.config);
				
				QFileUtil.getAllFiles(QFileUtil.getJarPath().split("WEB-INF")[0] + "WEB-INF/classes");
				for(String s : QFileUtil.fileList){
					if(s.endsWith("Model.class")){
						String classPath = s.split("classes")[1].replace(File.separator, ".");
						String className = classPath.substring(1, classPath.length() - 6); 
						
						Class<? extends Model<?>>  theClass = (Class<? extends Model<?>> ) (Class.forName(className));
						if(theClass.isAnnotationPresent(QTable.class)){
							arp.addMapping(theClass.getAnnotation(QTable.class).value(), "id", theClass);
						}
					}
				}
				
				initPackageArp(arp);
			}finally{
				QDbUtil.closeCon(con);
			}
			
			QFileUtil.fileList.clear();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private static void initPackageArp(ActiveRecordPlugin arp){
		arp.addMapping(UcenterUserModel.class.getAnnotation(QTable.class).value(), UcenterUserModel.class);
		arp.addMapping(UcenterMenuModel.class.getAnnotation(QTable.class).value(), UcenterMenuModel.class);
		arp.addMapping(UcenterRoleModel.class.getAnnotation(QTable.class).value(), UcenterRoleModel.class);
		arp.addMapping(UcenterRoleRUserModel.class.getAnnotation(QTable.class).value(), UcenterRoleRUserModel.class);
		arp.addMapping(UcenterRoleRMenuModel.class.getAnnotation(QTable.class).value(), UcenterRoleRMenuModel.class);
	}
	
	/**
	 * 初始化controller
	 * 通过controller中的注解，自动配置controller的访问路径
	 * @param me
	 */
	@SuppressWarnings("unchecked")
	public static void initController(Routes me){
		try {
			QFileUtil.getAllFiles(QFileUtil.getJarPath().split("WEB-INF")[0] + "WEB-INF/classes");
			for(String s : QFileUtil.fileList){
				if(s.endsWith("Controller.class")){
					String classPath = s.split("classes")[1].replace(File.separator, ".");
					String className = classPath.substring(1, classPath.length() - 6); 
					
					Class<? extends Controller> theClass = (Class<? extends Controller>) (Class.forName(className));
					if(theClass.isAnnotationPresent(ControllerUrl.class)){
						me.add(theClass.getAnnotation(ControllerUrl.class).value(), theClass);
					}
				}
			}
			QFileUtil.fileList.clear();

			initPackageController(me);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private static void initPackageController(Routes me){
		me.add("/login", 				UcenterLoginController.class);
		me.add("/ucenter/user", 		UcenterUserController.class);
		me.add("/ucenter/menu", 		UcenterMenuController.class);
		me.add("/ucenter/role", 		UcenterRoleController.class);
		me.add("/ucenter/role/r/user", 	UcenterRoleRUserController.class);
//		me.add("/ucenter/role/r/menu", 	UcenterRoleRMenuController.class);
	}
	
	/**
	 * 初始化一些文件，固定的不会修改的文件都放到jar中
	 */
	public static void initFiles(){
		String sourcePath = "/com/tanlsh/util/jfinal/files/";
		String destBasePath = PathKit.getWebRootPath() + File.separator;
		
		String classes = destBasePath + "WEB-INF" + File.separator + "classes";
		QFileUtil.copyFileFormJar(sourcePath, classes, "ehcache.xml");
		if(QPropertiesUtil.getBoolean("jfinal.auth.use_inside_contants_file")){
			QFileUtil.copyFileFormJar(sourcePath, classes, "contants.properties");
		}
		
		// copy qiao.js
		String wui = destBasePath + "WUI";
		QFileUtil.copyFileFormJar(sourcePath, wui, "qiao.js");
	}
	
	/**
	 * 将所有用户对应的访问路径放到cache
	 */
	public static Map<String, String> initAuths(){
		Map<String, String> auths = new HashMap<String, String>();
		for(UcenterUserModel user : UcenterUserModel.dao.findAll()){
			if("admin".equals(user.getStr("ucenter_user_name"))) continue;
			
			auths.put("user-" + user.getStr("ucenter_user_name"), user.role().urls());
		}
		QCacheUtil.putToEHCache("auths", auths);

		return auths;
	}
	
	/**
	 * add where by model
	 * @param sql
	 * @param obj
	 * @return
	 */
	public static String addWhere(String tableName, Model<?> obj) {
		StringBuilder sb = new StringBuilder("from " + tableName);
		for (String col : obj.getAttrNames()) {
			Object value = obj.get(col);
			if (value != null) {
				String type = QStringUtil.splitAndReturnLastString(value.getClass().toString(), "\\.");
				if ("String".equals(type) && QStringUtil.notEmpty((String) value)) {
					sb.append(" and " + col + " like '%" + value + "%' ");
				}

				if ("Integer".equals(type) && value != null){
					sb.append(" and " + col + "=" + value);
				}
			}
		}
		sb.append(" order by id desc");

		return sb.toString().replaceFirst("and", " where");
	}
	
	/**
	 * 获取paras的值
	 * @param paras
	 * @param key
	 * @return
	 */
	public static String value(Map<String, String[]> paras, String key){
		if(paras != null && QStringUtil.notEmpty(key) && paras.get(key) != null){
			return paras.get(key)[0];
		}else{
			return null;
		}
	}
	
}
