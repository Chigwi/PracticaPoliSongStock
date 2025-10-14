package co.edu.poli.PolisongStock.CarritoCompras.modelo;

import java.util.ArrayList;
import java.util.List;

import co.edu.poli.PolisongStock.RegistroPedidos.modelo.Pedido;
import co.edu.poli.PolisongStock.RegistroUsuario.modelo.Persona;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "shopping_cart")
@Data
public class CarritoCompras {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCart;
    
    @Column(name = "user_id")  // Assume user ID for ownership
    private Long userId;  // Link to user entity if exists
    
    @OneToMany(mappedBy = "carrito", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemCarrito> items = new ArrayList<>();  // List of items in cart
	
}
