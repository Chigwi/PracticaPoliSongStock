package co.edu.poli.PolisongStock.Notificaciones.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificacionesService {
	@Autowired
	private JavaMailSender javaMailSender;
	
	private void sendEmail(String toEmail, String subject,String body) {
		
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("polisongstock@gmail.com");
		message.setTo(toEmail);
		message.setSubject(subject);
		message.setText(body);
	}

}
