package co.edu.poli.PolisongStock.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Correo {
	private Long idCorreo;
	private String direccion;
	private Persona persona;

}
