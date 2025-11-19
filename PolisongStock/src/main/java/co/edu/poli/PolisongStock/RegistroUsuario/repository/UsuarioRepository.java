package co.edu.poli.PolisongStock.RegistroUsuario.repository;

import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.poli.PolisongStock.RegistroPedidos.modelo.Pedido;
import co.edu.poli.PolisongStock.RegistroUsuario.modelo.Persona;

@Repository
public interface UsuarioRepository extends JpaRepository<Persona, Long>{
	@Query("SELECT p FROM Persona p WHERE p.nombreUsuario = :nombreUsuario")
	 public Optional<Persona> findByNombreUsuario(String nombreUsuario);
	

	 Optional<Persona> findById(Long id);


}
