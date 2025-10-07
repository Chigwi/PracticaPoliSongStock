package co.edu.poli.PolisongStock.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Telefono {
	private Long idTelefono;
	private String codigoNacion;
	private Persona persona;

}
