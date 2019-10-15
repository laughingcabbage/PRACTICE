package com.hr.naver.mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

public class MailService {

	final Logger LOG = Logger.getLogger(MailService.class);
	
	public MailService(){}
	
	public int do_send_mail(MailVO inVO){
		int flag = 0;
		
		//smtp: Simple Mail Transfer Protocol
		//인터넷에서 이메일을 보내기 위해 이용되는 프로토콜
		
		//1. 발신자의 메일 계정과 비번	
		String host = "smtp.naver.com"; 		//naver smtp서버명
		String user = "udding14@naver.com";	 	//발신자 메일 계정 주소
		String password = "108203jin1."; 		//발신자 메일 계정 비밀번호
			
		//2. Property에 smtp서버정보 설정
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", 465); 		//naver에서 포트 뭔지 알아와야 됨
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.ssl.trust", host);
		
		//3. smtp서버정보와 사용자 정보를 기반으로 session클래스의 인스턴스를 생성
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator(){
			protected PasswordAuthentication getPasswordAuthentication(){
				return new PasswordAuthentication(user, password);
					}
				});
				
		try{
			//4. message 클래스를 사용하여 수신자와 내용, 제목, 메시지를 작성한다. 
			MimeMessage message = new MimeMessage(session);
			//4.1. from
			message.setFrom(new InternetAddress(user));
			//4.2. to
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(inVO.getMailTo()));
			//4.3. 제목
			message.setSubject(inVO.getTitle());
			//4.4. 내용
			message.setText(inVO.getContents());
			
			//5. transport 클래스를 사용하여 작성한 메시지를 전달한다. 
			Transport.send(message); 
			
		}catch(MessagingException me){
			LOG.debug("====================================");
			LOG.debug("MessagingException:"+me.toString());
			LOG.debug("====================================");
			return flag;
		}
		
		LOG.debug("Send Success");
		
		flag=1;
		
		return flag;
	}
}
