package co.edu.poli.PolisongStock.modelo;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity

@Table(name="Playlist")

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Playlist {

	private Long idPlaylist;
	private String nombre;
	private List<Cancion> canciones;
	
}
