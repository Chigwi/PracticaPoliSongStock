package co.edu.poli.PolisongStock.RegistroPlaylist.modelo;

import java.util.List;

import co.edu.poli.PolisongStock.RegistroCancion.modelo.Cancion;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPlaylist;
	
	@Column(name="nombre")
	private String nombre;
	
	@ManyToMany(cascade = CascadeType.ALL)
	
    @JoinTable(
            name = "cancionPlaylist",
            joinColumns = @JoinColumn(name = "playlistId"),
            inverseJoinColumns = @JoinColumn(name = "cancionId")
        )
	private List<Cancion> canciones;
	
}
