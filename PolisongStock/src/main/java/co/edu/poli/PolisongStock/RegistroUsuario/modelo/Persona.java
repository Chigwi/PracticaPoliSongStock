package co.edu.poli.PolisongStock.RegistroUsuario.modelo;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity

@Table(name="Persona")

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Persona {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPersona;
	
	@Column(name="nombreUsuario")
	private String nombreUsuario;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="apellido")
	private String apellido;
	
	@Column(name="contrasenna")
	private String contrasenna;
	
	@Column(name="tipoID")
	private String tipoID;
	
	@Column(name="direccion")
	private String direccion;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "persona")
	@Column(name="telefonos")
	private List<Telefono> telefonos;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "persona")
	@Column(name="correos")
	private List<Correo> correos;
	
	@ManyToOne
	@JoinColumn(name = "rol") // or whatever your FK column is
	private Rol rol;

	
}
