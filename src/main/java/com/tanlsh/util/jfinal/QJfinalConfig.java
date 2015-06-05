package com.tanlsh.util.jfinal;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.tanlsh.util.core.file.QPropertiesUtil;
import com.tanlsh.z.MyInterceptor;

/**
 * jfinal config
 * @author qiaowenbin
 * @version 0.0.4.20140909
 */
public class QJfinalConfig extends JFinalConfig{
	
	@Override
	public void configConstant(Constants me) {
		me.setDevMode(QPropertiesUtil.getPropertyToBoolean(QPropertiesUtil.config, "jfinal.dev_mode"));
	}

	@Override
	public void configRoute(Routes me) {
		QJfinalUtil.initController(me);
	}

	@Override
	public void configPlugin(Plugins me) {
		QJfinalUtil.initDbAndArp(me);
	}

	@Override
	public void configInterceptor(Interceptors me) {
		me.add(new QInterceptor());
		me.add(new MyInterceptor());// 自定义全局拦截器
	}

	@Override
	public void configHandler(Handlers me) {
	}
	
	@Override
	public void afterJFinalStart() {
		QJfinalUtil.initFiles();
	}

}