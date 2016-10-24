package com.hrp.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service("emailService")
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Value("${email.from}")
	String from;

	public void sendEmail(String[] to, String sub, String msgBody) {

		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(from);
		message.setTo(to);
		message.setSubject(sub);
		message.setText(msgBody);
		javaMailSender.send(message);
	}

	public void sendNewsLetter(String[] to, String sub, String newsLetterContent) {

		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(message, true, "UTF-8");
			helper.setText(newsLetterContent, true);
			helper.setTo(to);
			helper.setFrom(from);
			helper.setSubject(sub);
			javaMailSender.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}