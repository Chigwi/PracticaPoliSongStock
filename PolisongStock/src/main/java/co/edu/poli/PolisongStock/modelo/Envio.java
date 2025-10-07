package co.edu.poli.PolisongStock.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity

@Table(name="Envio")

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Envio {

	private Long idEnvio;
	private String empresaEnvios;
	private Boolean esAereo;
	private String estimacionLlegada;
	
}
