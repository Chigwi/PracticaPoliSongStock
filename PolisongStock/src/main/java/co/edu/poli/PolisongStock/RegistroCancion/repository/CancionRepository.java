package co.edu.poli.PolisongStock.RegistroCancion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.poli.PolisongStock.RegistroCancion.modelo.Cancion;

@Repository
public interface CancionRepository extends JpaRepository<Cancion, Long>{

}
