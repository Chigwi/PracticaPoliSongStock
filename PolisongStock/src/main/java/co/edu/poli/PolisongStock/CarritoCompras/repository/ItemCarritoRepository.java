package co.edu.poli.PolisongStock.CarritoCompras.repository;

import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import co.edu.poli.PolisongStock.CarritoCompras.modelo.ItemCarrito;



public interface ItemCarritoRepository extends JpaRepository<ItemCarrito, Long>{
	
    @Query("SELECT i FROM ItemCarrito i WHERE i.carrito.idCart = :cartId AND i.itemId = :itemId AND i.tipoItem = :itemType")
    Optional<ItemCarrito> findByCartIdAndItemIdAndItemType(@Param("cartId") Long cartId, @Param("itemId") Long itemId, @Param("itemType") String itemType);  // NEW: Finds item by cart ID, item ID, and type

}
