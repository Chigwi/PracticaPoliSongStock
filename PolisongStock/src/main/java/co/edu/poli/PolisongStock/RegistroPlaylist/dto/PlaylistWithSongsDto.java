package co.edu.poli.PolisongStock.RegistroPlaylist.dto;


import java.util.List;

import co.edu.poli.PolisongStock.RegistroCancion.dto.CancionDetailDto;
import lombok.Data;

@Data
public class PlaylistWithSongsDto {
	
	private Long id;
	
	private String nombre;
	
	private List<CancionDetailDto>canciones;
	

}
