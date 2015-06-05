package com.tanlsh.util.jfinal;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.tanlsh.util.core.file.PropertiesUtil;
import com.tanlsh.z.MyInterceptor;

/**
 * jfinal config
 * @author qiaowenbin
 * @version 0.0.4.20140909
 */
public class MyJfinalConfig extends JFinalConfig{
	
	@Override
	public void configConstant(Constants me) {
		me.setDevMode(PropertiesUtil.getPropertyToBoolean(PropertiesUtil.config, "jfinal.dev_mode"));
	}

	@Override
	public void configRoute(Routes me) {
		MyJfinalUtil.initController(me);
	}

	@Override
	public void configPlugin(Plugins me) {
		MyJfinalUtil.initDbAndArp(me);
	}

	@Override
	public void configInterceptor(Interceptors me) {
		me.add(new JfinalInterceptor());
		me.add(new MyInterceptor());// 自定义全局拦截器
	}

	@Override
	public void configHandler(Handlers me) {
		
	}
	
	@Override
	public void afterJFinalStart() {
		MyJfinalUtil.initFiles();
	}

}