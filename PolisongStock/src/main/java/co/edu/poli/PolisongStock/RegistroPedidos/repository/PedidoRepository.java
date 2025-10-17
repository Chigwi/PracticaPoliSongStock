package co.edu.poli.PolisongStock.RegistroPedidos.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import co.edu.poli.PolisongStock.RegistroPedidos.modelo.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

		@Query("SELECT p FROM pedido p WHERE p.comprador = :comprador")
		public List<Optional <Pedido>> findByComprador(@Param ("comprador") String comprador);
}
