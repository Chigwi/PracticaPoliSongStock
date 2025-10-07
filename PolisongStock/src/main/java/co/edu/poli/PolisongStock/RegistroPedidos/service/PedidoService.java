package co.edu.poli.PolisongStock.RegistroPedidos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.poli.PolisongStock.RegistroPedidos.repository.PedidoRepository;
import co.edu.poli.PolisongStock.modelo.Pedido;

@Service
public class PedidoService {
	@Autowired
	private PedidoRepository pedidoRepository;
	
	public Pedido createPedido(Pedido pedido) {
		return pedidoRepository.save(pedido); // This triggers JPA to insert into DB
	}

	public List getAllPedidos() {
		return pedidoRepository.findAll();
	}
	public boolean deletePedido(Long id) {
	    if (pedidoRepository.existsById(id)) {
	        pedidoRepository.deleteById(id);
	        return true;
	    }
	    return false;  // Returns false if ID not found
	}

	public Optional<Pedido> getPedidoById(Long id) {
	    return pedidoRepository.findById(id);
	}

}
