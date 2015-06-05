package com.tanlsh.util.function;

import java.math.BigInteger;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

import com.tanlsh.util.core.data.StringUtil;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


/**
 * 编码工具类<br>
 * 1.将byte[]转为各种进制的字符串<br>
 * 2.url encode<br>
 * 3.url decode<br>
 * 4.base 64 encode<br>
 * 5.base 64 decode<br>
 * 6.获取byte[]的md5值<br>
 * 7.获取字符串md5值<br>
 * 8.结合base64实现md5加密<br>
 * 9.AES加密<br>
 * 10.AES加密为base 64 code<br>
 * 11.AES解密<br>
 * 12.将base 64 code AES解密<br>
 * @author qiaowenbin
 * @version 0.0.9.20140610
 */
public class EncodeUtil {
	
	/**
	 * 将byte[]转为各种进制的字符串
	 * @param bytes byte[]
	 * @param radix 可以转换进制的范围，从Character.MIN_RADIX到Character.MAX_RADIX，超出范围后变为10进制
	 * @return 转换后的字符串
	 */
	public static String binary(byte[] bytes, int radix){
		return new BigInteger(1, bytes).toString(radix);// 这里的1代表正数
	}
	
	/**
	 * 对string进行url编码
	 * @param s
	 * @return
	 * @throws Exception
	 */
	public static String urlEncode(String s) throws Exception{
		return URLEncoder.encode(s, "UTF-8");
	}
	
	/**
	 * 对string进行url解码
	 * @param s
	 * @return
	 * @throws Exception
	 */
	public static String urlDecode(String s) throws Exception{
		return URLDecoder.decode(s, "UTF-8");
	}
	
	/**
	 * base 64 encode
	 * @param bytes 待编码的byte[]
	 * @return 编码后的base 64 code
	 */
	public static String base64Encode(byte[] bytes){
		return new BASE64Encoder().encode(bytes);
	}
	
	/**
	 * base 64 decode
	 * @param base64Code 待解码的base 64 code
	 * @return 解码后的byte[]
	 * @throws Exception
	 */
	public static byte[] base64Decode(String base64Code) throws Exception{
		return StringUtil.isEmpty(base64Code) ? null : new BASE64Decoder().decodeBuffer(base64Code);
	}
	
	/**
	 * 获取byte[]的md5值
	 * @param bytes byte[]
	 * @return md5
	 * @throws Exception
	 */
	public static byte[] md5(byte[] bytes) throws Exception {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(bytes);
		
		return md.digest();
	}
	
	/**
	 * 获取字符串md5值
	 * @param msg 
	 * @return md5
	 * @throws Exception
	 */
	public static byte[] md5(String msg) throws Exception {
		return StringUtil.isEmpty(msg) ? null : md5(msg.getBytes());
	}
	
	/**
	 * 结合base64实现md5加密
	 * @param msg 待加密字符串
	 * @return 获取md5后转为base64
	 * @throws Exception
	 */
	public static String md5Encrypt(String msg) throws Exception{
		return StringUtil.isEmpty(msg) ? null : base64Encode(md5(msg));
	}
	
	/**
	 * AES加密
	 * @param content 待加密的内容
	 * @param encryptKey 加密密钥
	 * @return 加密后的byte[]
	 * @throws Exception
	 */
	public static byte[] aesEncryptToBytes(String content, String encryptKey) throws Exception {
		KeyGenerator kgen = KeyGenerator.getInstance("AES");
		kgen.init(128, new SecureRandom(encryptKey.getBytes()));

		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(kgen.generateKey().getEncoded(), "AES"));
		
		return cipher.doFinal(content.getBytes("utf-8"));
	}
	
	/**
	 * AES加密为base 64 code
	 * @param content 待加密的内容
	 * @param encryptKey 加密密钥
	 * @return 加密后的base 64 code
	 * @throws Exception
	 */
	public static String aesEncrypt(String content, String encryptKey) throws Exception {
		return base64Encode(aesEncryptToBytes(content, encryptKey));
	}
	
	/**
	 * AES解密
	 * @param encryptBytes 待解密的byte[]
	 * @param decryptKey 解密密钥
	 * @return 解密后的String
	 * @throws Exception
	 */
	public static String aesDecryptByBytes(byte[] encryptBytes, String decryptKey) throws Exception {
		KeyGenerator kgen = KeyGenerator.getInstance("AES");
		kgen.init(128, new SecureRandom(decryptKey.getBytes()));
		
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(kgen.generateKey().getEncoded(), "AES"));
		byte[] decryptBytes = cipher.doFinal(encryptBytes);
		
		return new String(decryptBytes);
	}
	
	/**
	 * 将base 64 code AES解密
	 * @param encryptStr 待解密的base 64 code
	 * @param decryptKey 解密密钥
	 * @return 解密后的string
	 * @throws Exception
	 */
	public static String aesDecrypt(String encryptStr, String decryptKey) throws Exception {
		return StringUtil.isEmpty(encryptStr) ? null : aesDecryptByBytes(base64Decode(encryptStr), decryptKey);
	}
	
    /**
     * SHA-1加密
     * @param str
     * @return
     */
    public static String sha1(String str) {
        try {
        	MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(str.getBytes());
            
            return bytes2Hex(md.digest());
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }
    public static String bytes2Hex(byte[] bts) {
        StringBuilder sb = new StringBuilder();
        
        String tmp = null;
        for (int i = 0; i < bts.length; i++) {
            tmp = (Integer.toHexString(bts[i] & 0xFF));
            if (tmp.length() == 1) {
                sb.append("0");
            }
            sb.append(tmp);
        }
        
        return sb.toString();
    }
	
}
