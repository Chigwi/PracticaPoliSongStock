package co.edu.poli.PolisongStock.RegistroCancion.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.edu.poli.PolisongStock.RegistroCancion.modelo.Cancion;

@Repository
public interface CancionRepository extends JpaRepository<Cancion, Long>{

	@Query("SELECT c FROM Cancion c WHERE c.formato.nombre = :nombre")
	public List<Cancion> findAllByFormatoNombre (String nombre);
	
	@Query("SELECT c FROM Cancion c WHERE c.nombre = :nombre AND c.artista = :artista")
	Optional <Cancion> findByNombreAndArtista(@Param("nombre")String nombre, @Param("artista")String artista);
	
}
