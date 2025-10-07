package co.edu.poli.PolisongStock.modelo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Playlist {

	private Long idPlaylist;
	private String nombre;
	private List<Cancion> canciones;
	
}
