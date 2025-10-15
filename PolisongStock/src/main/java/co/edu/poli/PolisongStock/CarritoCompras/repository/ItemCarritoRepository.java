package co.edu.poli.PolisongStock.CarritoCompras.repository;

import co.edu.poli.PolisongStock.CarritoCompras.modelo.ItemCarrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ItemCarritoRepository extends JpaRepository<ItemCarrito, Long> {
    @Query("SELECT i FROM ItemCarrito i WHERE i.carrito.idCart = :idCart AND i.itemId = :itemId AND i.tipoItem = :tipoItem")  // FIXED: Use i.carrito.idCart
    Optional<ItemCarrito> findByCartIdAndItemIdAndTipoItem(@Param("idCart") Long idCart, @Param("itemId") Long itemId, @Param("tipoItem") String tipoItem);
}