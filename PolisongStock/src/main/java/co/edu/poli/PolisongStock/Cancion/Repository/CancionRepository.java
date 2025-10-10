package co.edu.poli.PolisongStock.Cancion.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import co.edu.poli.PolisongStock.RegistroCanciones.Modelo.Cancion;

@Repository
public interface CancionRepository extends JpaRepository<Cancion, Long>{

}
