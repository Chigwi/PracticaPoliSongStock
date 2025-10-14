package co.edu.poli.PolisongStock.CarritoCompras.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "Itemcarrito")
@Data
public class ItemCarrito {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idItem;
    
    @ManyToOne
    @JoinColumn(name = "idCarrito")
    private CarritoCompras carrito;
    
    @Column(name = "tipoItem")
    private String tipoItem;
    
    @Column(name = "itemId")
    private Long itemId;
    
    @Column(name = "cantidad")
    private Integer cantidad;
    
    @Column(name = "precio")
    private Double precio;
    

}
