package co.edu.poli.PolisongStock.RegistroPedidos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import co.edu.poli.PolisongStock.RegistroPedidos.service.PedidoService;

public class PedidoController {
	@Autowired
	private PedidoService pedidoService;
	
	@PostMapping 
	public ResponseEntity<String> create(){
		return ResponseEntity<String>;
		
	}
}
