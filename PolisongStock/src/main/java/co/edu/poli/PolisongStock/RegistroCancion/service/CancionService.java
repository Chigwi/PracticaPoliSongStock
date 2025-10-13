package co.edu.poli.PolisongStock.RegistroCancion.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.poli.PolisongStock.RegistroCancion.modelo.Cancion;
import co.edu.poli.PolisongStock.RegistroCancion.repository.CancionRepository;



@Service
public class CancionService {
	@Autowired
	private CancionRepository cancionRepository;
	
	public Cancion createCancion(Cancion cancion) {
		return cancionRepository.save(cancion); // This triggers JPA to insert into DB
	}

	public List getAllCancion() {
		return cancionRepository.findAll();
	}
	public boolean deleteCancion(Long id) {
	    if (cancionRepository.existsById(id)) {
	        cancionRepository.deleteById(id);
	        return true;
	    }
	    return false;  // Returns false if ID not found
	}

	public Optional<Cancion> getCancionById(Long id) {
	    return cancionRepository.findById(id);
	}


}
