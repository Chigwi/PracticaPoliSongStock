package co.edu.poli.PolisongStock.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Experiencia {

	private Long idExperiencia;
	private Integer calificacion;
	private String descripcion;
	
}
