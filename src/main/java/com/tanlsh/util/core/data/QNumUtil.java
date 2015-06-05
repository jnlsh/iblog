package com.tanlsh.util.core.data;
/**
 * 数字工具类<br>
 * 1.补齐位数并返回字符串<br>
 * @author qiaowenbin
 * @version 0.0.2.20150301
 * @history
 * 	0.0.2.20150301<br>
 * 	0.0.1.20140430<br>
 */
public class QNumUtil {
	
	/**
	 * 补齐位数并返回字符串
	 * @param num 待补齐的数字
	 * @param size 一共多少位
	 * @return 补齐之后的字符串或者null
	 */
	public static String toString(int num, int size){
		int numsize = String.valueOf(num).length();
		if(numsize > size) return null;

		String res = String.valueOf(num);
		for(int i=0; i<size-numsize; i++){
			res = "0" + res;
		}
		
		return res;
	}
	
}