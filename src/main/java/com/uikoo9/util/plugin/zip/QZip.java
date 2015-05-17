package com.uikoo9.util.plugin.zip;

import java.io.File;
import java.util.ArrayList;

import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

/**
 * 压缩DTO
 * @author qiaowenbin
 */
public class QZip {
	private String zipFile;
	private ArrayList<File> files;
	private String folder;
	private String password;
	private String encryptType;
	public ZipParameters parameters;
	{
		parameters = new ZipParameters();
		parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);  
		parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
	}
	
	public QZip() {
		super();
	}
	public QZip(String zipFile, ArrayList<File> files) {
		super();
		this.zipFile = zipFile;
		this.files = files;
	}
	public QZip(String zipFile, ArrayList<File> files, String password) {
		super();
		this.zipFile = zipFile;
		this.files = files;
		this.password = password;
	}
	public QZip(String zipFile, ArrayList<File> files, String password, String encryptType) {
		super();
		this.zipFile = zipFile;
		this.files = files;
		this.password = password;
		this.encryptType = encryptType;
	}
	public QZip(String zipFile, String folder) {
		super();
		this.zipFile = zipFile;
		this.folder = folder;
	}
	public QZip(String zipFile, String folder, String password) {
		super();
		this.zipFile = zipFile;
		this.folder = folder;
		this.password = password;
	}
	public QZip(String zipFile, String folder, String password, String encryptType) {
		super();
		this.zipFile = zipFile;
		this.folder = folder;
		this.password = password;
		this.encryptType = encryptType;
	}

	public String getZipFile() {
		return zipFile;
	}
	public void setZipFile(String zipFile) {
		this.zipFile = zipFile;
	}
	public ArrayList<File> getFiles() {
		return files;
	}
	public void setFiles(ArrayList<File> files) {
		this.files = files;
	}
	public String getFolder() {
		return folder;
	}
	public void setFolder(String folder) {
		this.folder = folder;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEncryptType() {
		return encryptType;
	}
	public void setEncryptType(String encryptType) {
		this.encryptType = encryptType;
	}
	public ZipParameters getParameters() {
		return parameters;
	}
	public void setParameters(ZipParameters parameters) {
		this.parameters = parameters;
	}
	
	
}
