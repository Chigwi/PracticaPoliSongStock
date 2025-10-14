package co.edu.poli.PolisongStock.RegistroPedidos.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.poli.PolisongStock.RegistroPedidos.modelo.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {


}
