package co.edu.poli.PolisongStock.RegistroPedidos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.poli.PolisongStock.RegistroPedidos.dto.PedidoDto;
import co.edu.poli.PolisongStock.RegistroPedidos.modelo.Pedido;
import co.edu.poli.PolisongStock.RegistroPedidos.repository.PedidoRepository;

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
	
	@Transactional(transactionManager = "pedidosTransactionManager", readOnly = true)
	public PedidoDto getExperienciaPedido(Long id) {
		Optional<Pedido> p = pedidoRepository.findById(id);
		PedidoDto exp = new PedidoDto();
		exp.setId(p.get().getIdPedido());
		exp.setCalificacion(p.get().getExperiencia().getCalificacion());
		exp.setDescripcion(p.get().getExperiencia().getDescripcion());
		
		return exp;
	}
	
	@Transactional(readOnly = true, transactionManager = "pedidosTransactionManager" )
	public List<Optional<Pedido>> getPedidosByComprador(String nombre){
		return pedidoRepository.findByComprador(nombre);
	}
}
