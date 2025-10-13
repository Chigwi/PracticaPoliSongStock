package co.edu.poli.PolisongStock.RegistroCancion.dto;

import co.edu.poli.PolisongStock.RegistroCancion.modelo.Cancion;
import co.edu.poli.PolisongStock.RegistroCancion.modelo.Formato;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CancionCreateDto {
	
    private String nombre;
    
    private String artista;
    
    private String annoPublicacion;

    private Double precio;
    
    private FormatoCreateDto formato;
    
}
