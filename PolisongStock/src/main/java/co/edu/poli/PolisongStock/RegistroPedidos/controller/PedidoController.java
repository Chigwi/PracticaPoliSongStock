package co.edu.poli.PolisongStock.RegistroPedidos.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.poli.PolisongStock.RegistroPedidos.modelo.Pedido;
import co.edu.poli.PolisongStock.RegistroPedidos.service.PedidoService;

@RestController
@RequestMapping("/api/pedidos")

public class PedidoController {
	@Autowired
	private PedidoService pedidoService;
	
	@PostMapping 
	public ResponseEntity<Pedido> create(@RequestBody Pedido pedido){
		Pedido saved = pedidoService.createPedido(pedido);
		return ResponseEntity.ok(saved);
	
	}
	@GetMapping
	public ResponseEntity<List<Pedido>> getAll(){
		return ResponseEntity.ok(pedidoService.getAllPedidos());
	}
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Pedido>> getById(@PathVariable Long id){
		return ResponseEntity.ok(pedidoService.getPedidoById(id));
		
	}
	@PutMapping("/{id}")
	public ResponseEntity<String> update(Long id, String updatePedido){
		return ResponseEntity.ok("pedido actualizado");
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete (Long id){
		boolean deleted = pedidoService.deletePedido(id);
		if (deleted) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build(); 
		}
	}
}

