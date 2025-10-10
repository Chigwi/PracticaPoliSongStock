package co.edu.poli.PolisongStock.RegistrosUsuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.poli.PolisongStock.RegistroUsuario.modelo.Persona;



@Repository
public interface UsuarioReporitory extends JpaRepository<Persona, Long>{

}
