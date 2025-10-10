package co.edu.poli.PolisongStock.RegistroCanciones.modelo;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity

@Table(name="Cancion")

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cancion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCancion;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="artista")
	private String artista;
	
	@Column(name="annoPublicacion")
	private String annoPublicaion;
	
	@Column(name="precio")
	private Double precio;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "formato")
	private Formato formato;
	
}
