package com.uikoo9.util.external;

import com.qiniu.api.auth.digest.Mac;
import com.qiniu.api.io.IoApi;
import com.qiniu.api.io.PutExtra;
import com.qiniu.api.rs.PutPolicy;
import com.uikoo9.util.core.file.QPropertiesUtil;

/**
 * 七牛云工具类<br>
 * 1.上传文件<br>
 * @author qiaowenbin
 * @version 0.0.1.20141209
 * @history
 * 	0.0.1.20141209<br>
 */
public class QQiNiuUtil {
	
	private static String uptoken = null;
	static{
		try {
			String ak = QPropertiesUtil.config.getProperty("qiniu.ak");
			String sk = QPropertiesUtil.config.getProperty("qiniu.sk");
			String bucket = QPropertiesUtil.config.getProperty("qiniu.bucket");
			uptoken = new PutPolicy(bucket).token(new Mac(ak, sk));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 上传文件
	 * @param path
	 * @param file
	 */
	public static void uploadFile(String path, String file){
        IoApi.putFile(uptoken, path, file, new PutExtra());
	}
	
}
