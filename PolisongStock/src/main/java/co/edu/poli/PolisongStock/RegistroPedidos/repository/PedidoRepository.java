package co.edu.poli.PolisongStock.RegistroPedidos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.poli.PolisongStock.RegistroPedidos.modelo.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
