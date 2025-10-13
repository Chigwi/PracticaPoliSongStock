package co.edu.poli.PolisongStock.Notificaciones.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import co.edu.poli.PolisongStock.Notificaciones.modelo.Notificacion;

@Service
public class NotificacionesService {
	@Autowired
	private JavaMailSender javaMailSender;
	
	public void sendEmail(Notificacion n) {
		
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("polisongstock@gmail.com");
		message.setTo(n.getToEmail());
		message.setSubject(n.getSubject());
		message.setText(n.getBody());
		javaMailSender.send(message);
	}

}
