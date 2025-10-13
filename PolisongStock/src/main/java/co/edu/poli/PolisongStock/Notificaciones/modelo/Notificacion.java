package co.edu.poli.PolisongStock.Notificaciones.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notificacion {
	
	private String toEmail;
	
	private String subject;
	
	private String body;
}
