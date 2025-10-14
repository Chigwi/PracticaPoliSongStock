package co.edu.poli.PolisongStock.CarritoCompras.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.poli.PolisongStock.CarritoCompras.modelo.CarritoCompras;

@RestController
public class CarritoComprasController {
	
	@PostMapping
	public ResponseEntity<CarritoCompras> createPedido(){
		CarritoCompras r = new CarritoCompras();
		return ResponseEntity.ok(r);
		//spp
	}

}
