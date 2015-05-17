package com.uikoo9.util.core.file;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


/**
 * 文档解析工具类<br>
 * 1.获取document<br>
 * 2.获取tagValue<br>
 * @author qiaowenbin
 * @version 0.0.2.20150301
 * @history
 * 	0.0.2.20150301<br>
 * 	0.0.1.20141229<br>
 */
public class QDocumentUtil {
	
	/**
	 * 获取document
	 * @param fileName
	 * @return
	 */
	public static Document parseDocument(String fileName){
		try {
			File file = new File(QFileUtil.getJarPath().split("WEB-INF")[0] + "WEB-INF/classes/" + fileName);
			return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file); 
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 获取document
	 * @param s
	 * @return
	 */
	public static Document parseDocumentByString(String s){
		Document document = null;
		try {
			InputStream in = new ByteArrayInputStream(s.getBytes());
			document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(in);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return document;
	}
	
	/**
	 * 获取tagValue
	 * @param fileName
	 * @param tagName
	 * @return
	 */
	public static List<String> getTagValue(String fileName, String tagName){
		Document document = null;
		try {
			File file = new File(QFileUtil.getJarPath().split("WEB-INF")[0] + "WEB-INF/classes/" + fileName);
			document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return getTagValue(document, tagName);
	}
	
	/**
	 * 获取tagValue
	 * @param s
	 * @param tagName
	 * @return
	 */
	public static List<String> getTagValueByString(String s, String tagName){
    	return getTagValue(parseDocumentByString(s), tagName);
	}
	
	/**
	 * 获取tagValue
	 * @param document
	 * @param tagName
	 * @return
	 */
	public static List<String> getTagValue(Document document, String tagName){
		List<String> res = new ArrayList<String>();
		
		try {
			NodeList list = document.getElementsByTagName(tagName);  
			for(int i = 0; i < list.getLength(); i++){
				Element element = (Element)list.item(i);
				res.add(element.getFirstChild().getNodeValue());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return res;
	}
	
}
