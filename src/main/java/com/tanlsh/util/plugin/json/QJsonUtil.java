package com.tanlsh.util.plugin.json;

/**
 * qjson util
 * @author qiaowenbin
 * @version 0.0.1.20141216
 * @history
 * 	0.0.1.20141216<br>
 */
public class QJsonUtil {
	
	public static final String TYPE_BS_SUCC = "success";
	public static final String TYPE_BS_INFO = "info";
	public static final String TYPE_BS_WARN = "warning";
	public static final String TYPE_BS_DANG = "danger";
	
	/**
	 * suc
	 * @return
	 */
	public static QJson suc(){
		return new QJson(TYPE_BS_SUCC);
	}
	public static QJson suc(String msg){
		return new QJson(msg, TYPE_BS_SUCC);
	}
	public static QJson suc(Object object){
		return new QJson(object, TYPE_BS_SUCC);
	}
	public static QJson suc(String msg, Object object){
		return new QJson(msg,object, TYPE_BS_SUCC);
	}
	
	/**
	 * info
	 * @return
	 */
	public static QJson info(){
		return new QJson(TYPE_BS_INFO);
	}
	public static QJson info(String msg){
		return new QJson(msg, TYPE_BS_INFO);
	}
	public static QJson info(Object object){
		return new QJson(object, TYPE_BS_INFO);
	}
	public static QJson info(String msg, Object object){
		return new QJson(msg,object, TYPE_BS_INFO);
	}
	
	/**
	 * warn
	 * @return
	 */
	public static QJson warn(){
		return new QJson(TYPE_BS_WARN);
	}
	public static QJson warn(String msg){
		return new QJson(msg, TYPE_BS_WARN);
	}
	public static QJson warn(Object object){
		return new QJson(object, TYPE_BS_WARN);
	}
	public static QJson warn(String msg, Object object){
		return new QJson(msg,object, TYPE_BS_WARN);
	}
	
	/**
	 * error
	 * @return
	 */
	public static QJson error(){
		return new QJson(TYPE_BS_DANG);
	}
	public static QJson error(String msg){
		return new QJson(msg, TYPE_BS_DANG);
	}
	public static QJson error(Object object){
		return new QJson(object, TYPE_BS_DANG);
	}
	public static QJson error(String msg, Object object){
		return new QJson(msg,object, TYPE_BS_DANG);
	}
	
}