package co.edu.poli.PolisongStock.RegistroCancion.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.poli.PolisongStock.RegistroCancion.modelo.Cancion;
import co.edu.poli.PolisongStock.RegistroCancion.repository.CancionRepository;



@Service
public class CancionService {
	@Autowired
	private CancionRepository cancionRepository;
	
	public Cancion createPedido(Cancion cancion) {
		return cancionRepository.save(cancion); // This triggers JPA to insert into DB
	}

	public List getAllPedidos() {
		return cancionRepository.findAll();
	}
	public boolean deletePedido(Long id) {
	    if (cancionRepository.existsById(id)) {
	        cancionRepository.deleteById(id);
	        return true;
	    }
	    return false;  // Returns false if ID not found
	}

	public Optional<Cancion> getPedidoById(Long id) {
	    return cancionRepository.findById(id);
	}


}
