package co.edu.poli.PolisongStock.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Envio {

	private Long idEnvio;
	private String empresaEnvios;
	private Boolean esAereo;
	private String estimacionLlegada;
	
}
