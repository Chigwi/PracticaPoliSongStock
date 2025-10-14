package co.edu.poli.PolisongStock.RegistroCancion.dto;

import co.edu.poli.PolisongStock.RegistroCancion.modelo.Formato;
import lombok.Data;

@Data
public class CancionDetailDto {
	
	private Long id;
	
	private String nombre;
	
	private String artista;
	
	private Formato formato;

}
