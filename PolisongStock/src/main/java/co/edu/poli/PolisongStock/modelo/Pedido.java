package co.edu.poli.PolisongStock.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {

	private Long idPedido;
	private Envio envio;
	private DetallePedido detallePedido;
	private Experiencia experiencia;
	private String comprobante;
	private String fecha;
	
	
}
