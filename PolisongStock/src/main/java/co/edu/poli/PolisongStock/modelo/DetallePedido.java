package co.edu.poli.PolisongStock.modelo;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity

@Table(name="DetallePedido")

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetallePedido {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long idDetallePedido;

	@OneToMany(cascade= CascadeType.ALL)
	@JoinColumn(name="detallePedidoId")
	@Column(name="canciones")
	private List<Cancion> caniones;

}
