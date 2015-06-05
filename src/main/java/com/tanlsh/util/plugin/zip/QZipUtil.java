package com.tanlsh.util.plugin.zip;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

import com.tanlsh.util.core.data.QArrayUtil;
import com.tanlsh.util.core.data.QStringUtil;

public class QZipUtil {
	
	/**
	 * 压缩
	 * @param zip zip的dto
	 * @return
	 */
	public static String compress(QZip zip){
		String zipFile = zip.getZipFile();
		if(QStringUtil.isEmpty(zipFile)) return "请设置压缩生成文件路径！";

		ZipParameters parameters = zip.getParameters();
		if(parameters == null){
			parameters = new ZipParameters();
			parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);  
			parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
		}
		
		String password = zip.getPassword();
		String encryptType = zip.getEncryptType();
		if(QStringUtil.notEmpty(password)){
			parameters.setEncryptFiles(true);
			if("AES".equals(encryptType)){
				parameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_AES);
				parameters.setAesKeyStrength(Zip4jConstants.AES_STRENGTH_256);
			}else{
				parameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_STANDARD);   
			}
			parameters.setPassword(password);  
		}
		
		try {
			ArrayList<File> files = zip.getFiles();
			String folder = zip.getFolder();
			if(QArrayUtil.isEmpty(files) && QStringUtil.isEmpty(folder)){
				return "请设置压缩文件或文件夹！";
			}else if(QArrayUtil.notEmpty(files)){
				new ZipFile(zipFile).addFiles(files, parameters);
				return "压缩成功！";
			}else if(QStringUtil.notEmpty(folder)){
				new ZipFile(zipFile).addFolder(folder, parameters);
				return "压缩成功！";
			}else{
				return "不能同时压缩文件和文件夹！";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "压缩发生异常！";
		}
	}
	
	/**
	 * 普通解压缩
	 * @param zipFile
	 * @param destFolder
	 * @return
	 */
	public static String extract(String zipFile, String destFolder){
		try {
			new ZipFile(zipFile).extractAll(destFolder);
			return "解压成功！";
		} catch (ZipException e) {
			e.printStackTrace();
			return "解压失败！";
		}
	}
	
	/**
	 * 加密后的解压缩
	 * @param zipFile
	 * @param destFolder
	 * @param password
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String extract(String zipFile, String destFolder, String password){
		try {
			ZipFile zip = new ZipFile(zipFile);

			if(zip.isEncrypted()) zip.setPassword(password);
			List list = zip.getFileHeaders();
			for(int i=0; i<list.size(); i++){
				zip.extractFile((FileHeader) list.get(i), destFolder);
			}
			
			return "解压成功！";
		} catch (ZipException e) {
			e.printStackTrace();
			return "解压失败！";
		}
	}
	
	/**
	 * 获取zip包中文件列表信息
	 * @param zipFile
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<FileHeader> fileList(String zipFile){
		try {
			return new ZipFile(zipFile).getFileHeaders();
		} catch (ZipException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}