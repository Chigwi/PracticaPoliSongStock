package co.edu.poli.PolisongStock.CarritoCompras.repository;

import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import co.edu.poli.PolisongStock.CarritoCompras.modelo.CarritoCompras;



public interface CarritoComprasRepository extends JpaRepository<CarritoCompras, Long>{
	
	@EntityGraph(attributePaths = {"items"})
	Optional<CarritoCompras> findByUserId(Long userId);

}
