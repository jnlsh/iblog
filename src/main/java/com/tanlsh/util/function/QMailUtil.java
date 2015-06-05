package com.tanlsh.util.function;

import java.util.Date;

import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import com.tanlsh.util.core.data.QStringUtil;
import com.tanlsh.util.core.file.QPropertiesUtil;

/**
 * 邮件工具类<br>
 * @author qiaowenbin
 * @version 0.0.2.20141105
 * @history
 * 	0.0.2.20141105
 * 	0.0.1.20141104
 */
public class QMailUtil {
	
	/**
	 * 发送邮件，通用
	 * @param mailInfo 邮件信息
	 * @param isHtml 是否html格式
	 * @return 结果
	 */
	public static boolean sendMail(String from, String to, String title, String content, boolean isHtml){
		final Message mailMessage = generateMailMessage(from, to, title, content, isHtml);
		if(mailMessage != null){
			QThreadUtil.runCached(new Runnable() {
				public void run() {
					try {
						Transport.send(mailMessage);
					} catch (MessagingException e) {
						e.printStackTrace();
					}
				}
			});
			
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 发送邮件，普通
	 * @param mailInfo 邮件信息
	 * @return 结果
	 */
	public static boolean sendTextMail(String from, String to, String title, String content){
		return sendMail(from, to, title, content, false);
	}
	
	/**
	 * 发送邮件，html
	 * @param mailInfo 邮件信息
	 * @return 结果
	 */
	public static boolean sendHtmlMail(String from, String to, String title, String content){
		return sendMail(from, to, title, content, true);
	}
	
	/**
	 * 生成一个邮件消息，内部使用
	 * @param mailInfo 邮件信息
	 * @param isHtml 是否html格式
	 * @return 邮件消息
	 */
	private static Message generateMailMessage(String from, String to, String title, String content, boolean isHtml){
		final String username = QPropertiesUtil.config.getProperty("mail.user.username");
		final String password = QPropertiesUtil.config.getProperty("mail.user.password");
		if(QStringUtil.anyoneEmpty(new String[]{username, password, from, to, title})) return null;
		
		Session sendMailSession = 
				Session.getDefaultInstance(QPropertiesUtil.config,new Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});
		
		try {
			Message mailMessage = new MimeMessage(sendMailSession);
			mailMessage.setSentDate(new Date());
			mailMessage.setSubject(MimeUtility.encodeText(title, "UTF-8", "B"));
			mailMessage.setFrom(new InternetAddress(from));
			
			if(to.contains(",")){
				mailMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			}else{
				mailMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
			}
			
			if(isHtml){
				BodyPart html = new MimeBodyPart();
				html.setContent(content, "text/html; charset=utf-8");
				Multipart mainPart = new MimeMultipart();
				mainPart.addBodyPart(html);
				mailMessage.setContent(mainPart); 
			}else{
				mailMessage.setText(content);
			}
			
			return mailMessage;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}