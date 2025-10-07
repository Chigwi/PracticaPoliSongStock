package co.edu.poli.PolisongStock.modelo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
