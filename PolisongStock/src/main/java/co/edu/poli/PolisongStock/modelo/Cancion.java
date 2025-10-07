package co.edu.poli.PolisongStock.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cancion {

	private Long idCancion;
	private String nombre;
	private String artista;
	private String annoPublicaion;
	private Double precio;
	private Formato formato;
	
}
