package com.tanlsh.util.plugin.json;

/**
 * qjson util
 * @author 
 * @version 0.0.1.20141216
 * @history
 * 	0.0.1.20141216<br>
 */
public class MyJsonUtil {
	
	public static final String TYPE_BS_SUCC = "success";
	public static final String TYPE_BS_INFO = "info";
	public static final String TYPE_BS_WARN = "warning";
	public static final String TYPE_BS_DANG = "danger";
	
	/**
	 * suc
	 * @return
	 */
	public static MyJson suc(){
		return new MyJson(TYPE_BS_SUCC);
	}
	public static MyJson suc(String msg){
		return new MyJson(msg, TYPE_BS_SUCC);
	}
	public static MyJson suc(Object object){
		return new MyJson(object, TYPE_BS_SUCC);
	}
	public static MyJson suc(String msg, Object object){
		return new MyJson(msg,object, TYPE_BS_SUCC);
	}
	
	/**
	 * info
	 * @return
	 */
	public static MyJson info(){
		return new MyJson(TYPE_BS_INFO);
	}
	public static MyJson info(String msg){
		return new MyJson(msg, TYPE_BS_INFO);
	}
	public static MyJson info(Object object){
		return new MyJson(object, TYPE_BS_INFO);
	}
	public static MyJson info(String msg, Object object){
		return new MyJson(msg,object, TYPE_BS_INFO);
	}
	
	/**
	 * warn
	 * @return
	 */
	public static MyJson warn(){
		return new MyJson(TYPE_BS_WARN);
	}
	public static MyJson warn(String msg){
		return new MyJson(msg, TYPE_BS_WARN);
	}
	public static MyJson warn(Object object){
		return new MyJson(object, TYPE_BS_WARN);
	}
	public static MyJson warn(String msg, Object object){
		return new MyJson(msg,object, TYPE_BS_WARN);
	}
	
	/**
	 * error
	 * @return
	 */
	public static MyJson error(){
		return new MyJson(TYPE_BS_DANG);
	}
	public static MyJson error(String msg){
		return new MyJson(msg, TYPE_BS_DANG);
	}
	public static MyJson error(Object object){
		return new MyJson(object, TYPE_BS_DANG);
	}
	public static MyJson error(String msg, Object object){
		return new MyJson(msg,object, TYPE_BS_DANG);
	}
	
}