package co.edu.poli.PolisongStock.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity

@Table(name="Correo")

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Correo {
	private Long idCorreo;
	private String direccion;
	private Persona persona;

}
