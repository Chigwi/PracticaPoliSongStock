package co.edu.poli.PolisongStock.RegistroPlaylist.modelo;

import java.util.List;

import co.edu.poli.PolisongStock.RegistroCancion.modelo.Cancion;
import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
	
	 @ElementCollection(fetch = FetchType.EAGER)  // Or a simple List if no complex associations needed
	    @CollectionTable(name = "detalleCanciones", joinColumns = @JoinColumn(name = "detalleId"))
	    @Column(name = "CancionId")
	private List<Long> canciones;
	 
	@Column(name="proveedor")
	private String proveedor;
	
}
