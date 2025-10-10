package co.edu.poli.PolisongStock.Cancion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.poli.PolisongStock.RegistroCanciones.modelo.Cancion;

@Repository
public interface CancionRepository extends JpaRepository<Cancion, Long>{

}
