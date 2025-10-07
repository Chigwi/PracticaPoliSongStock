package co.edu.poli.PolisongStock.RegistroPedidos.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.poli.PolisongStock.RegistroPedidos.service.PedidoService;

@RestController
@RequestMapping("/api/pedidos")

public class PedidoController {
	@Autowired
	private PedidoService pedidoService;
	
	@PostMapping 
	public ResponseEntity<String> create(){
		return ResponseEntity.ok("Pedido creado");
	
	}
	@GetMapping
	public ResponseEntity<List<String>> getAll(){
		ArrayList p = new ArrayList<String>();
		String r = "pedidos";
		p.add(r);
		return ResponseEntity.ok(p);
	}
	@GetMapping("/{id}")
	public ResponseEntity<String> getById(Long id){
		return ResponseEntity.ok("pedido");
		
	}
	@PutMapping("/{id}")
	public ResponseEntity<String> update(Long id, String updatePedido){
		return ResponseEntity.ok("pedido actualizado");
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete (Long id){
		return ResponseEntity.ok("pedido eliminado");
	}
}

