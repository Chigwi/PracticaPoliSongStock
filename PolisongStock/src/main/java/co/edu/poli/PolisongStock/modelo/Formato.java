package co.edu.poli.PolisongStock.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity

@Table(name="Formato")

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Formato {

	private Long idFormato;
	private String nombre;
	private Integer cantidad;
	
}
