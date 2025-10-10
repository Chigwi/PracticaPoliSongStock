package co.edu.poli.PolisongStock.RegistroCanciones.modelo;

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

@Table(name="Formato")

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Formato {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idFormato;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="cantidad")
	private Integer cantidad;
	
}
