package co.edu.poli.PolisongStock.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity

@Table(name="Rol")

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rol {
	private Long idRol;
	private String nombre;
	private Persona persona;

}
