package com.tanlsh.util.plugin.json;


/**
 * json model
 * @author qiaowenbin
 * @version 0.0.5.20141216
 * @history
 * 	0.0.5.20141216<br>
 * 	0.0.4.20141104<br>
 * 	0.0.3.20140901<br>
 */
public class MyJson{
	
	private boolean success = true;
	private String msg;
	private String type;
	private Object object;
	
	public MyJson(){}
	public MyJson(String type){
		this.type = type;
	}
	public MyJson(String msg, String type){
		this.msg = msg;
		this.type = type;
	}
	public MyJson(Object object, String type){
		this.object = object;
		this.type = type;
	}
	public MyJson(String msg, Object object, String type){
		this.msg = msg;
		this.object = object;
		this.type = type;
	}

	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Object getObject() {
		return object;
	}
	public void setObject(Object object) {
		this.object = object;
	}
	
}
