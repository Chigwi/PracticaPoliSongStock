package co.edu.poli.PolisongStock.CarritoCompras.modelo;

import co.edu.poli.PolisongStock.RegistroPedidos.modelo.Pedido;
import co.edu.poli.PolisongStock.RegistroUsuario.modelo.Persona;
import lombok.Data;

@Data
public class CarritoCompras {
	
	private Persona usuario;
	
	private Pedido pedido;
	
	private Integer precioTotal;
	
}
