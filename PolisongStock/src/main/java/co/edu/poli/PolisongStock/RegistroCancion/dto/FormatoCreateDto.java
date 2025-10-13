package co.edu.poli.PolisongStock.RegistroCancion.dto;

import co.edu.poli.PolisongStock.RegistroCancion.modelo.Formato;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormatoCreateDto {
	
	private String nombre;
	
	private Integer cantidad;

}
