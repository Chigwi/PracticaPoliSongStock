package co.edu.poli.PolisongStock.RegistroPlaylist.dto;

import java.util.List;

import co.edu.poli.PolisongStock.RegistroCancion.dto.CancionCreateDto;
import co.edu.poli.PolisongStock.RegistroPlaylist.modelo.Playlist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaylistCreateDto {

	private String nombre;
	
	private List <CancionCreateDto> canciones;
}
