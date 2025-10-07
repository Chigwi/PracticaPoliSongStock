package co.edu.poli.PolisongStock.modelo;

import jakarta.persistence.Entity;
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
	private Long idPersona;
	private String nombreUsuario;
	private String nombre;
	private String apellido;
	private String contrasenna;
	private String tipoID;
	private String direccion;
	

}
