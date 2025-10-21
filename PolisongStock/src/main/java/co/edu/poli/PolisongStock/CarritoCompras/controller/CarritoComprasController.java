package co.edu.poli.PolisongStock.CarritoCompras.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.poli.PolisongStock.CarritoCompras.modelo.CarritoCompras;
import co.edu.poli.PolisongStock.CarritoCompras.service.CarritoComprasService;
import co.edu.poli.PolisongStock.security.AppUserDetails;

@RestController
@RequestMapping("/api/carritoCompras")
public class CarritoComprasController {
    @Autowired
    private CarritoComprasService shoppingCartService;

    @PostMapping("/cart")
    public ResponseEntity<CarritoCompras> createPedido(){
    	CarritoCompras r = new CarritoCompras();
    	return ResponseEntity.ok(r);
    }

    @GetMapping("/cart")
    public ResponseEntity<Optional<CarritoCompras>> getCartByCurrentUser(@AuthenticationPrincipal AppUserDetails currentUser) {
        Long userId = currentUser.getId();
        Optional<CarritoCompras> cart = shoppingCartService.getCartByUserId(userId);
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/cart/checkout")
    public ResponseEntity<CarritoCompras> checkoutCart(@AuthenticationPrincipal AppUserDetails currentUser) {
        Long userId = currentUser.getId();
        CarritoCompras cart = shoppingCartService.checkoutCart(userId);
        return ResponseEntity.ok(cart);
    }
}