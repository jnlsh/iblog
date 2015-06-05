package com.tanlsh.util.external;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Map;
import java.util.Properties;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.texen.util.FileUtil;


/**
 * Velocity工具类<br>
 * 1.生成代码<br>
 * @author qiaowenbin
 * @version 0.0.4.20141209
 * @history
 * 	0.0.4.20141209<br>
 * 	0.0.3.20141015<br>
 * 	0.0.2.20140502<br>
 */
public class QVelocityUtil {
	
	/**
	 * 生成代码
	 * @param map		变量
	 * @param destPath	目的地址
	 * @param destFile	目的文件名
	 * @param tmpPath	模版地址
	 * @param tmpFile	模版文件名
	 * @return
	 */
	public static boolean generateCodeByVelocity(Map<String, Object> map, String destPath, String destFile, String tmpPath, String tmpFile){
		try {
			// 1.初始化
			Properties properties = new Properties();
			properties.put("file.resource.loader.path", tmpPath);  
			properties.put("input.encoding", "UTF-8");
			properties.put("output.encoding", "UTF-8");
			Velocity.init(properties);
			VelocityContext context = new VelocityContext(map);
				
			// 2.生成代码
			FileUtil.mkdir(destPath);
			BufferedWriter sw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(destPath, destFile)), "UTF-8"));
			Velocity.getTemplate(tmpFile).merge(context, sw);
			sw.flush();
			sw.close();
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
