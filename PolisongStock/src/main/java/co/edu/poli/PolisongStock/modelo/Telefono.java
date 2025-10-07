package co.edu.poli.PolisongStock.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity

@Table(name="Telefono")

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Telefono {
	private Long idTelefono;
	private String codigoNacion;
	private Persona persona;

}
