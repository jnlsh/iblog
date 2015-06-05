package com.tanlsh.util.function;

import com.tanlsh.util.core.data.QStringUtil;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * 缓存工具类<br>
 * 1.获得一个ehcache<br>
 * 2.关闭ehcache manager<br>
 * 3.向默认ehcache中存入一个对象<br>
 * 4.从默认ehcache中读取一个对象<br>
 * 5.从默认ehcache中移除一个对象<br>
 * @author qiaowenbin
 * @version 0.0.1.20141109
 * @history
 * 	0.0.1.20141109
 */
public class QCacheUtil {
	
	private static final CacheManager ehCacheManager = CacheManager.create();
	private static final Cache ehCache = getEHCache("cache");
	
	/**
	 * 获得一个ehcache
	 * @param name
	 * @return
	 */
	public static Cache getEHCache(String name){
		if(ehCacheManager != null && QStringUtil.notEmpty(name)){
			return ehCacheManager.getCache(name);
		}else{
			return null; 
		}
	}
	
	/**
	 * 关闭ehcache manager
	 * @param cacheManager
	 */
	public static void closeEHCacheManager(){
		if(ehCacheManager != null) ehCacheManager.shutdown();
	}
	
	/**
	 * 向默认ehcache中存入一个对象
	 * @param key
	 * @param value
	 */
	public static void putToEHCache(Object key, Object value){
		if(ehCache != null) ehCache.put(new Element(key, value));
	}
	
	/**
	 * 从默认ehcache中读取一个对象
	 * @param key
	 * @return
	 */
	public static Object getFromEHCache(Object key){
		if(ehCache != null && key != null && ehCache.get(key) != null){
			return ehCache.get(key).getObjectValue();
		}
		
		return null;
	}
	
	/**
	 * 从默认ehcache中移除一个对象
	 * @param key
	 */
	public static void removeEHCache(Object key){
		if(ehCache != null) ehCache.remove(key);
	}
	
}
