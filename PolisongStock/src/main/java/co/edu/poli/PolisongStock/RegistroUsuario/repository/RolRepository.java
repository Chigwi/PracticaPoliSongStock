package co.edu.poli.PolisongStock.RegistroUsuario.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import co.edu.poli.PolisongStock.RegistroUsuario.modelo.*;
@Repository
public interface RolRepository extends JpaRepository<Rol, Long>{

	
}
