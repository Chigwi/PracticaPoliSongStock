package co.edu.poli.PolisongStock.CarritoCompras.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.poli.PolisongStock.CarritoCompras.modelo.CarritoCompras;
import co.edu.poli.PolisongStock.CarritoCompras.service.CarritoComprasService;

@RestController
public class CarritoComprasController {
	@Autowired
	private CarritoComprasService shoppingCartService;
	
	
	@PostMapping
	public ResponseEntity<CarritoCompras> createPedido(){
		CarritoCompras r = new CarritoCompras();
		return ResponseEntity.ok(r);
	}

    @GetMapping("/user/{userId}")
    public ResponseEntity<Optional<CarritoCompras>> getCartByUserId(@PathVariable Long userId) {
        Optional<CarritoCompras> cart = shoppingCartService.getCartByUserId(userId);
        return ResponseEntity.ok(cart);
    }
    @PostMapping("/checkout")
    public ResponseEntity<CarritoCompras> checkoutCart(@RequestParam Long userId) {
        CarritoCompras cart = shoppingCartService.checkoutCart(userId);
        return ResponseEntity.ok(cart);
    }
}
