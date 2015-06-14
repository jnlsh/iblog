package com.tanlsh.util.plugin.contants;

/**
 * 常量model
 * @author 
 * @version 0.0.1.20140830
 */
public class ContantsModel {
	private String value;
	private String text;
	
	public ContantsModel() {
		super();
	}
	public ContantsModel(String value, String text) {
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
