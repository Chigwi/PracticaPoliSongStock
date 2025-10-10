package co.edu.poli.PolisongStock.RegistroPedidos.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long idEnvio;
	
	@Column(name="empresaEnvios")
	private String empresaEnvios;
	
	@Column(name="esAereo")
	private Boolean esAereo;
	
	@Column(name="estimacionLlegada")
	private String estimacionLlegada;
	
}
