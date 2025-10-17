package co.edu.poli.PolisongStock.RegistroPlaylist.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.edu.poli.PolisongStock.RegistroPlaylist.modelo.Playlist;


@Repository
public interface PlaylistRepository  extends JpaRepository<Playlist, Long>{
	@Query("SELECT p FROM playlist p WHERE p.nommbre =:nombre")
	public Optional<Playlist> findByNombre(@Param("nombre") String nombre);
	
	@Query("SELECT p FROM playlist p WHERE p.proveedor =:proveedor")
	public List<Optional<Playlist>> findByProveedor(@Param("proveedor") String proveedor);
	
}
