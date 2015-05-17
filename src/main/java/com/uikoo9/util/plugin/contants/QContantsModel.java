package com.uikoo9.util.plugin.contants;

/**
 * 常量model
 * @author qiaowenbin
 * @version 0.0.1.20140830
 */
public class QContantsModel {
	private String value;
	private String text;
	
	public QContantsModel() {
		super();
	}
	public QContantsModel(String value, String text) {
		super();
		this.value = value;
		this.text = text;
	}

	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
}
