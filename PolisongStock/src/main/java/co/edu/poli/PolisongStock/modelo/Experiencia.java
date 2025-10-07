package co.edu.poli.PolisongStock.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity

@Table(name="Experiencia")

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Experiencia {

	private Long idExperiencia;
	private Integer calificacion;
	private String descripcion;
	
}
