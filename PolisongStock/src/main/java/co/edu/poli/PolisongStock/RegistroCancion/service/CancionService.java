package co.edu.poli.PolisongStock.RegistroCancion.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.poli.PolisongStock.RegistroCancion.dto.CancionDetailDto;
import co.edu.poli.PolisongStock.RegistroCancion.modelo.Cancion;
import co.edu.poli.PolisongStock.RegistroCancion.repository.CancionRepository;



@Service
public class CancionService {
	@Autowired
	private CancionRepository cancionRepository;
	
	@Transactional(transactionManager = "cancionTransactionManager")
	public Cancion createCancion(Cancion cancion) {
		return cancionRepository.save(cancion); // This triggers JPA to insert into DB
	}
	@Transactional(transactionManager = "cancionTransactionManager", readOnly = true)
	public List<Cancion> getAllCancion() {
		return cancionRepository.findAll();
	}
	public boolean deleteCancion(Long id) {
	    if (cancionRepository.existsById(id)) {
	        cancionRepository.deleteById(id);
	        return true;
	    }
	    return false;  // Returns false if ID not found
	}
	
	@Transactional(transactionManager = "cancionTransactionManager", readOnly = true)
	public Optional<Cancion> getCancionById(Long id) {
	    return cancionRepository.findById(id);
	}
	
	@Transactional(transactionManager = "cancionTransactionManager",readOnly = true)
	public List <Cancion>getByFormatoNombre(String nombre){
		return cancionRepository.findAllByFormatoNombre(nombre);
	}
	
	@Transactional(transactionManager = "cancionTransactionManager")
    public List<CancionDetailDto> getCancionDetailsByIds(List<Long> ids) {
        List<Cancion> canciones = cancionRepository.findAllById(ids);  // JPA method for multiple IDs
        return canciones.stream().map(this::mapToDetailDto).collect(Collectors.toList());
    }
	
    @Transactional(readOnly = true, transactionManager = "cancionTransactionManager")
    public List<Cancion> getCancionesByIdsRaw(List<Long> ids) {
        return cancionRepository.findAllById(ids);  // Directly return entities
    }
	
	//metodo que mapea canciones a detalles mediante dto
    private CancionDetailDto mapToDetailDto(Cancion cancion) {
        CancionDetailDto dto = new CancionDetailDto();
        dto.setId(cancion.getIdCancion());
        dto.setNombre(cancion.getNombre());
        dto.setArtista(cancion.getArtista());
        dto.setFormato(cancion.getFormato());
        return dto;
    }
    
    @Transactional(readOnly = true, transactionManager = "cancionTransactionManager")
    public List<Optional<Cancion>> getCancionesByProveedor(String nombre){
    	return cancionRepository.findByProveedor(nombre);
    }
	

}
