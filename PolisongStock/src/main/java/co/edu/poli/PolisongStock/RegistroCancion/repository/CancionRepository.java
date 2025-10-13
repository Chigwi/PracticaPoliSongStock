package co.edu.poli.PolisongStock.RegistroCancion.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.poli.PolisongStock.RegistroCancion.modelo.Cancion;

@Repository
public interface CancionRepository extends JpaRepository<Cancion, Long>{

	@Query("SELECT c FROM Cancion c WHERE c.formato.nombre = :nombre")
	public List<Cancion> findAllByFormatoNombre (String nombre);
}
