package co.edu.poli.PolisongStock.modelo;

import java.util.List;

import jakarta.persistence.Entity;
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
	
	private Long idDetallePedido;
	private Pedido pedido;
	private Envio Envio;
	private Persona persona;
	private List<Cancion> caniones;
	private Formato formato;
}
