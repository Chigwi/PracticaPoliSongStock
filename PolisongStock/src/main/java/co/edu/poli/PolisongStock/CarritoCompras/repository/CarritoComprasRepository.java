package co.edu.poli.PolisongStock.CarritoCompras.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.poli.PolisongStock.CarritoCompras.modelo.CarritoCompras;



public interface CarritoComprasRepository extends JpaRepository<CarritoCompras, Long>{
	
	Optional<CarritoCompras> findByUserId(Long userId);

}
