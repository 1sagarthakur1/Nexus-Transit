package com.config;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

@Service
public class SandOtp_ByEmail {

	public String sendEmail(String to, String subject, String body) {
		// Set up email properties
		Properties properties = new Properties();
		properties.put("mail.smtp.host", "smtp.gmail.com"); // SMTP server address
		properties.put("mail.smtp.port", "587"); // SMTP server port
		properties.put("mail.smtp.auth", "true"); // Enable authentication
		properties.put("mail.smtp.starttls.enable", "true"); // Enable STARTTLS

		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("1sagarthakur1@gmail.com", "rwns jmxq obct ymqn");
			}
		});

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("1sagarthakur1@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject(subject);
			message.setText(body);

			Transport.send(message);

			return "OTP has been sanded successfully on the email";

		} catch (MessagingException e) {
//			e.printStackTrace();
			return e.getMessage();
		}
		
	}
}

