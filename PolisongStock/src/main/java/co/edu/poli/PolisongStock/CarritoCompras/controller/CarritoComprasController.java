package co.edu.poli.PolisongStock.CarritoCompras.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.poli.PolisongStock.CarritoCompras.modelo.CarritoCompras;
import co.edu.poli.PolisongStock.CarritoCompras.service.CarritoComprasService;
import co.edu.poli.PolisongStock.security.AppUserDetails;

@RestController
@RequestMapping("/api/carritoCompras")
@PreAuthorize("isAuthenticated()")
public class CarritoComprasController {
    @Autowired
    private CarritoComprasService shoppingCartService;

    @PreAuthorize("hasRole('superusuario')")
    @PostMapping("/cart")
    public ResponseEntity<CarritoCompras> createPedido(@AuthenticationPrincipal AppUserDetails currentUser){
    	return ResponseEntity.ok(shoppingCartService.createCart(currentUser.getId()));
    }
    
    @PostMapping("/add2cart/{tipo}/{id}/{cantidad}")
    public ResponseEntity<CarritoCompras> add2cart(@AuthenticationPrincipal AppUserDetails currentUser,@PathVariable String tipo ,@PathVariable Long id,@PathVariable int cantidad){
    	Long userID = currentUser.getId();
    	CarritoCompras r = shoppingCartService.addToCart(userID, id, tipo, cantidad);
    	return ResponseEntity.ok(r);
    }
    
    @PostMapping("/remove/{tipo}/{id}/{cantidad}")
    public ResponseEntity<CarritoCompras> removeFromCart(
            @AuthenticationPrincipal AppUserDetails currentUser,
            @PathVariable String tipo,
            @PathVariable Long id,
            @PathVariable int cantidad) {

        if (currentUser == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        if (cantidad <= 0) return ResponseEntity.badRequest().build();

        CarritoCompras updated = shoppingCartService.removeFromCart(currentUser.getId(), id, tipo, cantidad);
        return ResponseEntity.ok(updated);
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