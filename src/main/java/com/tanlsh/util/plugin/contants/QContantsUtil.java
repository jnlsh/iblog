package com.tanlsh.util.plugin.contants;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.tanlsh.util.core.file.QPropertiesUtil;

/**
 * 常量工具类
 * @author qiaowenbin
 * @version 0.0.2.20140909
 */
public class QContantsUtil {
	
	private static final Properties CONTANTS = QPropertiesUtil.readProperties("/contants.properties");
	
	public static List<QContantsModel> list(String[] values){
		List<QContantsModel> list = new ArrayList<QContantsModel>();
		if(values != null){
			for(String value : values){
				list.add(new QContantsModel(value, get(value)));
			}
		}
		
		return list;
	}
	
	public static String get(String value){
		return CONTANTS.getProperty(value);
	}
}
