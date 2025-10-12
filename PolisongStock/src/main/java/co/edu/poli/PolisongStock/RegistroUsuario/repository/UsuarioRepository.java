package co.edu.poli.PolisongStock.RegistroUsuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.poli.PolisongStock.RegistroPedidos.modelo.Pedido;
import co.edu.poli.PolisongStock.RegistroUsuario.modelo.Persona;

@Repository
public interface UsuarioRepository extends JpaRepository<Persona, Long>{

}
