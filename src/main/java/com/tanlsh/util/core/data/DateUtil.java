package com.tanlsh.util.core.data;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类<br>
 * 1.格式化时间<br>
 * 2.当前时间字符串<br>
 * 3.当前时间毫秒<br>
 * 4.当前时间+-n天的date<br>
 * 5.当前时间是否在一个有效期内<br>
 * @author qiaowenbin
 * @version 0.0.2.20150301
 * @history
 * 	0.0.2.20150301<br>
 * 	0.0.1.20140430<br>
 */
public class DateUtil {
	
	/**
	 * 格式化时间
	 * @param date 需要格式化的时间
	 * @param format 时间格式
	 * y	年
	 * M	月
	 * d	天
	 * H	时
	 * m	分
	 * s	秒
	 * @return 格式化后的字符串
	 */
	public static String format(Date date, String format){
		return date == null ? null : new SimpleDateFormat(format).format(date);
	}
	
	/**
	 * 当前时间字符串
	 * @return yyyyMMddHHmmss格式的当前时间字符串
	 */
	public static String dateStr(){
		return format(new Date(), "yyyyMMddHHmmss");
	}
	
	/**
	 * 当前时间毫秒
	 * @return 当前时间毫秒
	 */
	public static long getNowTime(){
		return System.currentTimeMillis();
	}
	
	/**
	 * 当前时间+-n天的date
	 * @param day 负数代表几天前，正数代表几天后
	 * @return date 运算后的date
	 */
	public static Date getDateByDays(int day){
		Calendar calendar = Calendar.getInstance();
		long now = calendar.getTimeInMillis();
		long res = now + (new BigDecimal(day).multiply(new BigDecimal(24 * 60 * 60 * 1000)).longValue());
		calendar.setTimeInMillis(res);
		
		return calendar.getTime();
	}
	
	/**
	 * 当前时间是否在一个有效期内
	 * @param start 开始时间
	 * @param end 结束时间
	 * @return 判断结果
	 */
	public static boolean between(Date start, Date end){
		if(start == null || end == null) return false;
		
		Date now = new Date();
		return now.after(start) && now.before(end) ? true : false;
	}
	
}