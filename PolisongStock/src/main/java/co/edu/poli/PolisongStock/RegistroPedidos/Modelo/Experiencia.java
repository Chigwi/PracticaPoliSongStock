package co.edu.poli.PolisongStock.RegistroPedidos.Modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long idExperiencia;
	
	@Column(name="calificacion")
	private Integer calificacion;
	
	@Column(name="descripcion")
	private String descripcion;
	
}
