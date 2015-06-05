package com.tanlsh.util.external;

import freemarker.ext.beans.BeansWrapper;

/**
 * Freemarker工具类<br>
 * 1.获取静态类<br>
 * @author qiaowenbin
 * @version 0.0.1.20141210
 * @history
 * 	0.0.1.20141210<br>
 */
public class QFreemarkerUtil {
	
	/**
	 * 获取静态类
	 * @param className
	 * @return
	 */
	public static Object getStaticClass(String className){
		try {
			return BeansWrapper.getDefaultInstance().getStaticModels().get(className);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
