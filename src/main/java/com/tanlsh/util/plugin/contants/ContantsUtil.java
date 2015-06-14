package com.tanlsh.util.plugin.contants;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.tanlsh.util.core.file.PropertiesUtil;

/**
 * 常量工具类
 * @author 
 * @version 0.0.2.20140909
 */
public class ContantsUtil {
	
	private static final Properties CONTANTS = PropertiesUtil.readProperties("/contants.properties");
	
	public static List<ContantsModel> list(String[] values){
		List<ContantsModel> list = new ArrayList<ContantsModel>();
		if(values != null){
			for(String value : values){
				list.add(new ContantsModel(value, get(value)));
			}
		}
		
		return list;
	}
	
	public static String get(String value){
		return CONTANTS.getProperty(value);
	}
}
