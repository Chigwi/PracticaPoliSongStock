package co.edu.poli.PolisongStock.RegistroUsuario.Mondelo;

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

@Table(name="Telefono")

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Telefono {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTelefono;
	
	@Column(name="codigoNacion")
	private String codigoNacion;
	

}
